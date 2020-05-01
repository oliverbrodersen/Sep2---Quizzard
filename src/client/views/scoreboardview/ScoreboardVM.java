package client.views.scoreboardview;

import client.model.QuizConverter;
import javafx.beans.property.SimpleStringProperty;

public class ScoreboardVM {

  private QuizConverter quizConverter;

  public ScoreboardVM(QuizConverter quizConverter) {
    this.quizConverter = quizConverter;
  }

  public void nextQuestion()
  {
    quizConverter.nextQuestion();
  }

  public boolean lastQuestion()
  {
    System.out.println("Length: " + quizConverter.getQuiz().getLength() + ", Q: " + quizConverter.getQuiz().getQuestionNumber() + 1);
    return quizConverter.getQuiz().getQuestionNumber() + 1 == quizConverter.getQuiz().getLength();
  }
}
