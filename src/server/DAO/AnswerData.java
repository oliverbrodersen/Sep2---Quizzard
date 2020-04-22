package server.DAO;

import shared.transferobjects.Answer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnswerData {
    void storeAnswer(Answer answer);
    ArrayList<Answer> readAnswer(int questionID) throws SQLException;
}
