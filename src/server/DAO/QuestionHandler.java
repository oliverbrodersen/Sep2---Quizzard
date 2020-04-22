package server.DAO;

import shared.transferobjects.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionHandler implements QuestionData{
    DatabaseConnection DBConn;
    AnswerData answerData;

    public QuestionHandler(DatabaseConnection DBConn) {
        this.DBConn = DBConn;
        answerData = new AnswerHandler(this.DBConn);
    }

    @Override
    public void storeQuestion(Question question) {

    }

    @Override
    public ArrayList<Question> retrieveQuestion(int quizID) throws SQLException {
        ArrayList<Question> questions = new ArrayList<>();
        ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Quizzard_Database\".Question WHERE QuizID = "
                + quizID + ";");

        while ( rs.next() ) {
            String questionString = rs.getString("Question");
            int time = rs.getInt("Time");
            int questionID = rs.getInt("QuestionID");
            Question question = new Question(questionString, answerData.readAnswer(questionID), time, 1000);
            questions.add(question);
        }

        DBConn.closeStatement();
        rs.close();
        return questions;
    }
}
