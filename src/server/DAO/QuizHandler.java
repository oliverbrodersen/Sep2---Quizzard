package server.DAO;

import shared.transferobjects.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Quiz readQuiz(int quizID) throws SQLException {
        Quiz quiz = null;
        ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Quizzard_Database\".Quiz WHERE QuizID = " + quizID + ";");

        while ( rs.next() ) {
            String quizName = rs.getString("QuizName");
            String quizSubject = rs.getString("Subject");
            quiz = new Quiz(quizName, quizSubject, questionData.retrieveQuestion(quizID));
        }
        DBConn.closeStatement();
        rs.close();
        return quiz;
    }
}
