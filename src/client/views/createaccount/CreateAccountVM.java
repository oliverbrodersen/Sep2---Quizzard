package client.views.createaccount;

import client.model.QuizConverter;
import client.views.ViewModel;

public class CreateAccountVM implements ViewModel {

    private QuizConverter quizConverter;

    public CreateAccountVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
    }
}
