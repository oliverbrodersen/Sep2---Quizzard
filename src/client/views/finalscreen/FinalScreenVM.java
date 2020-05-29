package client.views.finalscreen;

import client.model.QuizConverter;
import client.views.ViewModel;

public class FinalScreenVM implements ViewModel {
    private QuizConverter quizConverter;

    public FinalScreenVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
    }
}
