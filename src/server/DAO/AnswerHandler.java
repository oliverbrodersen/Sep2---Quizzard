package server.DAO;

import shared.transferobjects.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerHandler implements AnswerData{
    private DatabaseConnection DBConn;

    public AnswerHandler(DatabaseConnection DBConn) {
        this.DBConn = DBConn;
    }

    @Override
    public void storeAnswer(Answer answer) {
        char answerNo = answer.getAnswerID().charAt(0);
        String questionID = answer.getAnswerID().substring(1);
        String answerString = answer.getAnswer();
        char correctAnswer = 0;
        if (answer.getCorrect())
            correctAnswer = 1;
        DBConn.addData("INSERT INTO Answer VALUES ('" + answerNo + "', '"
                + questionID + "', '" + answerString + "', '" + correctAnswer + "');");

    }

    @Override
    public ArrayList<Answer> readAnswer(int questionID) throws SQLException {
        ArrayList<Answer> answers = new ArrayList<>();
        ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Quizzard_Database\".Answer WHERE QuestionID = "
                + questionID + ";");

        while ( rs.next() ) {
            String answerString = rs.getString("answer");
            boolean isCorrect = false;
            if (rs.getInt("CorrectAnswer") == 1)
                isCorrect = true;
            String answerID = rs.getString("AnswerNo") + rs.getString("QuestionID");
            Answer answer = new Answer(answerString, isCorrect, answerID);
            answers.add(answer);
        }
        DBConn.closeStatement();
        rs.close();
        return answers;
    }
}
