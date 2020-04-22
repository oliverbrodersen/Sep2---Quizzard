package server.DAO;

import shared.transferobjects.Question;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QuestionData {
    void storeQuestion(Question question);
    ArrayList<Question> retrieveQuestion(int quizID) throws SQLException;
}
