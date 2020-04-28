package client.views.questionview;

import client.model.QuizConverter;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import shared.transferobjects.Quiz;

public class QuestionVM {

  private QuizConverter quizConverter;
  private SimpleStringProperty numberOfQuestions, quizName;

  public QuestionVM(QuizConverter quizConverter) {
    this.quizConverter = quizConverter;
    numberOfQuestions = new SimpleStringProperty();
    quizName = new SimpleStringProperty();
  }

  public SimpleStringProperty quizNameProperty()
  {
    return quizName;
  }

  public SimpleStringProperty numberOfQuestionsProperty()
  {
    return numberOfQuestions;
  }

  public void setup()
  {
    Quiz quiz = quizConverter.getQuiz();
    numberOfQuestions.set("Questions: " + quiz.getLength());
    quizName.set(quiz.getTitle());
  }
}
