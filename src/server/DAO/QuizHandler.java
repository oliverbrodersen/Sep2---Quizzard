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
    public void storeQuiz(Quiz quiz) {

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
}
