package server.DAO;

import shared.transferobjects.Question;
import shared.transferobjects.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizHandler implements QuizData{
    private DatabaseConnection DBConn;
    private QuestionData questionData;

    public QuizHandler(DatabaseConnection DBConn) {
        this.DBConn = DBConn;
        questionData = new QuestionHandler(this.DBConn);
    }

    @Override
    public void storeQuiz(Quiz quiz, String difficulty, String email) {

        DBConn.updateData("INSERT into \"Quizzard_Database\".Quiz(QuizID, QuizName, Subject, Level, Email) VALUES ('" + quiz.getQuizId()
        + "', '" + quiz.getTitle() + "', '" + quiz.getSubject() + "', '" + difficulty.toUpperCase()
        + "', '" + email + "');");

        ArrayList<Question> questions = (ArrayList<Question>) quiz.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            String sql = "";
            String questionID = questions.get(i).getAnswer(0).getAnswerID().substring(1);
            for (int j = 0; j < 4; j++) {
                char place;
                if (questions.get(i).getAnswers().size() > j) {
                    if (questions.get(i).getAnswer(j) != null) {
                        place = questions.get(i).getAnswer(j).getAnswerID().charAt(0);
                        sql = sql + " '" + place + "',";
                    }
                } else {
                    sql = sql + " " + null + ",";
                }
            }

            DBConn.updateData("INSERT into \"Quizzard_Database\".Question(QuestionID, QuizID, Question, Answer1, Answer2, Answer3, Answer4, Time) " +
                    "values ('" + questionID + "', '" + quiz.getQuizId() + "', '"
                    + questions.get(i).getQuestion() + "',"
                    + sql + " '" + quiz.getQuestion(i).getTime() + "');");

            for (int j = 0; j < questions.get(i).getAnswers().size(); j++) {
                String answerNo = String.valueOf(questions.get(i).getAnswer(j).getAnswerID().charAt(0));
                String answer = questions.get(i).getAnswer(j).getAnswer();
                boolean correctAnswer = questions.get(i).getAnswer(j).getCorrect();
                String correctAnswerString = "";
                if (correctAnswer)
                    correctAnswerString = "1";
                else
                    correctAnswerString = "0";

                DBConn.updateData("INSERT into \"Quizzard_Database\".Answer(AnswerNo, QuestionID, Answer, CorrectAnswer) VALUES "
                        + "('" + answerNo + "', '" + questionID + "', '" + answer + "', '"
                        + correctAnswerString + "');");
            }
        }
    }

    @Override
    public Quiz readQuiz(int quizID, String email) throws SQLException {
        Quiz quiz = null;
        ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Quizzard_Database\".Quiz WHERE QuizID = "
                + quizID + " AND LOWER(Email) = LOWER('" + email + "');");

        while ( rs.next() ) {
            String quizName = rs.getString("QuizName");
            String quizSubject = rs.getString("Subject");
            quiz = new Quiz(quizName, quizSubject, questionData.retrieveQuestion(quizID), quizID);
        }
        DBConn.closeStatement();
        rs.close();
        return quiz;
    }

    @Override
    public List<Quiz> readQuizzes(String email) throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        Quiz quiz = null;
        ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Quizzard_Database\".Quiz WHERE LOWER(Email) = LOWER('" + email + "');");

        while ( rs.next() ) {
            String quizName = rs.getString("QuizName");
            String quizSubject = rs.getString("Subject");
            int quizID = Integer.parseInt(rs.getString("QuizID"));
            quiz = new Quiz(quizName, quizSubject, questionData.retrieveQuestion(quizID), quizID);
            quizzes.add(quiz);
        }
        return quizzes;
    }

    @Override
    public int getNextQuizID() throws SQLException {
        int quizID = -1;
        ResultSet rs = DBConn.retrieveData("SELECT MAX(QuizID) FROM \"Quizzard_Database\".Quiz");
        while (rs.next()) {
            quizID = Integer.parseInt(rs.getString("max"));
        }
        return quizID;
    }

    @Override
    public void deleteQuiz(Quiz quiz) throws SQLException {

        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            String questionID = quiz.getQuestion(i).getAnswer(0).getAnswerID().substring(1);
            DBConn.updateData("DELETE FROM \"Quizzard_Database\".Answer WHERE QuestionID='" + questionID + "';");
        }
        DBConn.updateData("DELETE FROM \"Quizzard_Database\".Question WHERE QuizID='" + quiz.getQuizId() + "';");
        DBConn.updateData("DELETE FROM \"Quizzard_Database\".Quiz WHERE QuizID='" + quiz.getQuizId() + "';");
    }


}
