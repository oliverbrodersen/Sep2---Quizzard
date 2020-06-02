package server.DAO;

import shared.transferobjects.Quiz;

import java.sql.SQLException;
import java.util.List;

public interface QuizData {
    void storeQuiz(Quiz quiz, String difficulty, String email, int questionID);
    Quiz readQuiz(int quizID, String email) throws SQLException;
    List<Quiz> readQuizzes(String email) throws SQLException;
    int getNextQuizID() throws SQLException;

    void deleteQuiz(Quiz quiz) throws SQLException;
}
