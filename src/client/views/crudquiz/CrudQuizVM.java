package client.views.crudquiz;

import client.model.QuizConverter;
import javafx.beans.property.*;

public class CrudQuizVM {
    private QuizConverter quizConverter;
    private StringProperty nameField, subjectField;
    private ObjectProperty<String> difficulty;

    public CrudQuizVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
        nameField = new SimpleStringProperty();
        subjectField = new SimpleStringProperty();
        difficulty = new SimpleObjectProperty<>();
    }

    public void createQuiz()
    {
       // quizConverter.createQuiz(nameField.get(), subjectField.get(), difficulty.get);
    }

    public StringProperty nameFieldProperty()
    {
        return nameField;
    }

    public StringProperty subjectFieldProperty()
    {
        return subjectField;
    }

    public Property difficultyProperty() {
        return difficulty;
    }
}
