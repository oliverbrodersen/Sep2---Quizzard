package client.views.crudquiz.crudquestion;

import client.model.QuizConverter;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import shared.transferobjects.Answer;
import shared.transferobjects.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrudQuestionVM {
    private QuizConverter quizConverter;
    private StringProperty questionArea, answerArea;
    private BooleanProperty correctCheckBox;
    private StringProperty timeField;
    private int correctQuestionID;
    private int answerNumber;

    public CrudQuestionVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
        questionArea = new SimpleStringProperty();
        answerArea = new SimpleStringProperty();
        correctCheckBox = new SimpleBooleanProperty();
        timeField = new SimpleStringProperty();
        answerNumber = 1;
        correctQuestionID = quizConverter.getNextQuestionID() + 1;
    }

    public Property<String> questionAreaProperty() {
        return questionArea;
    }

    public Property<String> answerAreaProperty() {
        return answerArea;
    }

    public Property<Boolean> correctCheckBoxProperty() {
        return correctCheckBox;
    }

    public Property<String> timeFieldProperty() {
        return timeField;
    }

    public Answer submitAnswer() {
        if (answerNumber <= 4) {
            String answerID = "";
            answerID = answerNumber + Integer.toString(correctQuestionID);

            Answer answerToSubmit = new Answer(answerArea.get(), correctCheckBox.get(), answerID);
            answerNumber++;
            return answerToSubmit;
        }
        else {
            System.out.println("too many answers");
        }
        return null;
    }

    public void backPressed() {
        answerNumber = 1;
    }

    public void submitQuestion(ObservableList<Answer> answers) {
        List<Answer> questionAnswers = new ArrayList<>(answers);
        while(correctQuestionID <= quizConverter.getNextQuestionID() + 1)
            correctQuestionID++;
        int time = -1;
        if (timeField.get() != null && timeField.get().matches("^[0-9]*$"))
            time = Integer.parseInt(timeField.get());

        if (time > 0 && !questionAnswers.isEmpty() && questionArea.get() != null) {
            Question question = new Question(questionArea.get(), questionAnswers, time, 1000);
            quizConverter.questionCreated(question);
            answerNumber = 1;
        }
    }
}
