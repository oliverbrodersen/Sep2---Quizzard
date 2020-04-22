package server.DAO;

import shared.transferobjects.Quiz;

import java.sql.SQLException;

public interface QuizData {
    void storeQuiz(Quiz quiz);
    Quiz readQuiz(int quizID) throws SQLException;
}
