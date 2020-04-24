package client.views.hostmain;

import client.model.QuizConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Quiz;

import java.util.List;

public class HostMainVM {

    private ObservableList<Quiz> quizzes;
    private QuizConverter quizConverter;

    public HostMainVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
    }

    public void exit() {
        quizConverter.exit();
    }

    public void loadQuizzes() {
        List<Quiz> quizList = quizConverter.getQuizzes();
        quizzes = FXCollections.observableArrayList(quizList);
    }

    public ObservableList<Quiz> getQuizzes() {
        return quizzes;
    }
}

