package client.views.scoreboardview;

import client.model.QuizConverter;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import shared.transferobjects.Answer;
import shared.transferobjects.Quiz;

import java.util.ArrayList;

public class ScoreboardVM {

  private QuizConverter quizConverter;
  private SimpleStringProperty a1Text, a2Text, a3Text, a4Text, question;

  public ScoreboardVM(QuizConverter quizConverter) {
    this.quizConverter = quizConverter;
    a1Text = new SimpleStringProperty();
    a2Text = new SimpleStringProperty();
    a3Text = new SimpleStringProperty();
    a4Text = new SimpleStringProperty();
    question = new SimpleStringProperty();
  }

  public void setup(Rectangle a1Rec, Rectangle a2Rec, Rectangle a3Rec, Rectangle a4Rec, Text a1True, Text a2True,
      Text a3True, Text a4True, VBox vBox1, VBox vBox2, VBox vBox3, VBox vBox4)
  {
    Quiz quiz = quizConverter.getQuiz();
    int questionNum = quiz.getQuestionNumber(), totalAnswers = 0;
    ArrayList<Integer> answers = quizConverter.getAnswers(questionNum);
    ArrayList<Answer> answersList = (ArrayList<Answer>) quiz.getQuestion(questionNum).getAnswers();

    question.set(quiz.getQuestion(questionNum).getQuestion());
    for (Integer answer : answers)
    {
      totalAnswers += answer;
    }

    switch (answers.size()){
      case 4: vBox4.setMaxWidth(100); vBox4.setMinWidth(100); vBox4.setVisible(true);
              a4Text.set(answers.get(3) + "");
              a4Rec.setHeight((((double)answers.get(3) / (double)totalAnswers)  * 200) + 5);
              if (answersList.get(3).getCorrect())
                a4True.setVisible(true);
      case 3: vBox3.setMaxWidth(100); vBox3.setMinWidth(100); vBox3.setVisible(true);
              a3Text.set(answers.get(2) + "");
              a3Rec.setHeight((((double)answers.get(2) / (double)totalAnswers)  * 200) + 5);
              if (answersList.get(2).getCorrect())
                a3True.setVisible(true);
      case 2: vBox2.setMaxWidth(100); vBox2.setMinWidth(100); vBox2.setVisible(true);
              a2Text.set(answers.get(1) + "");
              a2Rec.setHeight((((double)answers.get(1) / (double)totalAnswers)  * 200) + 5);
              if (answersList.get(1).getCorrect())
                a2True.setVisible(true);
      case 1: vBox1.setMaxWidth(100); vBox1.setMinWidth(100); vBox1.setVisible(true);
              a1Text.set(answers.get(0) + "");
              a1Rec.setHeight((((double)answers.get(0) / (double)totalAnswers)  * 200) + 5);
              if (answersList.get(0).getCorrect())
                a1True.setVisible(true);
    }
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

  public SimpleStringProperty a1Property()
  {
    return a1Text;
  }

  public SimpleStringProperty a2Property()
  {
    return a2Text;
  }

  public SimpleStringProperty a3Property()
  {
    return a3Text;
  }

  public SimpleStringProperty a4Property()
  {
    return a4Text;
  }

  public SimpleStringProperty questionProperty()
  {
    return question;
  }
}
