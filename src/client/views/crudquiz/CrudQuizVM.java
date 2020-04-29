package client.views.crudquiz;

import client.model.QuizConverter;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Participant;
import shared.transferobjects.Question;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class CrudQuizVM {
    private QuizConverter quizConverter;
    private StringProperty nameField, subjectField;
    private ObjectProperty<String> difficulty;
    private ObservableList<Question> questionObservableList;
    private ArrayList<Question> questionArrayList;

    public CrudQuizVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
        nameField = new SimpleStringProperty();
        subjectField = new SimpleStringProperty();
        difficulty = new SimpleObjectProperty<>();
        questionArrayList = new ArrayList<>();
    }

    public void createQuiz()
    {
       // quizConverter.createQuiz(nameField.get(), subjectField.get(), difficulty.get());
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

    public void addListener(String propertyChange, PropertyChangeListener propertyChange1)
    {
        quizConverter.addListener(propertyChange, propertyChange1);
    }

    public ObservableList<Question> getQuestions()
    {
        questionObservableList = FXCollections.observableArrayList(questionArrayList);
        return questionObservableList;
    }

    public ObservableList<Question> setQuestions(Question newValue)
    {
        if (!questionArrayList.contains(newValue))
            questionArrayList.add(newValue);
        return getQuestions();
    }
}
