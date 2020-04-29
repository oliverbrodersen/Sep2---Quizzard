package client.views.questionview;

import client.model.QuizConverter;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import shared.transferobjects.Answer;
import shared.transferobjects.Question;
import shared.transferobjects.Quiz;
import shared.transferobjects.UserID;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionVM
{
  private QuizConverter quizConverter;
  private SimpleStringProperty numberOfQuestions, quizName, timeLeft, question;
  private Button answer1, answer2, answer3, answer4;
  private Timer timer;
  private int interval;

  public QuestionVM(QuizConverter quizConverter)
  {
    this.quizConverter = quizConverter;
    numberOfQuestions = new SimpleStringProperty();
    quizName = new SimpleStringProperty();
    timeLeft = new SimpleStringProperty();
    question = new SimpleStringProperty();
  }

  public SimpleStringProperty quizNameProperty()
  {
    return quizName;
  }

  public SimpleStringProperty numberOfQuestionsProperty()
  {
    return numberOfQuestions;
  }

  public void setup(Button answer1Button, Button answer2Button, Button answer3Button, Button answer4Button, Button endQuestionPressed, Text answer1Text,
      Text answer2Text, Text answer3Text, Text answer4Text)
  {
    if (answer1 == null)
    {
      answer1 = answer1Button;
      answer2 = answer2Button;
      answer3 = answer3Button;
      answer4 = answer4Button;
    }

    Quiz quiz = quizConverter.getQuiz();
    Question quizQuestion = quiz.getQuestion(quiz.getQuestionNumber());
    List<Answer> answersList = quizQuestion.getAnswers();
    int answers = answersList.size();

    numberOfQuestions.set(
        "(" + (quiz.getQuestionNumber() + 1) + "/" + quiz.getLength() + ")");
    quizName.set(quiz.getTitle());
    interval = quizQuestion.getTime();
    timeLeft.set(interval + "");
    question.set(quizQuestion.getQuestion());

    switch (answers)
    {
      case 1:
        answer2Button.setVisible(false);
      case 2:
        answer3Button.setVisible(false);
      case 3:
        answer4Button.setVisible(false);
    }

    if (quizConverter.getUser() != null)
    {
      switch (answers)
      {
        case 4:
          answer4Text.setText(answersList.get(3).getAnswer());
          answer4Text.setStyle(
              "-fx-font-weight: bold;-fx-font-size:15;");
        case 3:
          answer3Text.setText(answersList.get(2).getAnswer());
          answer3Text.setStyle(
              "-fx-font-weight: bold;-fx-font-size:15;");
        case 2:
          answer2Text.setText(answersList.get(1).getAnswer());
          answer2Text.setStyle(
              "-fx-font-weight: bold;-fx-font-size:15;");
        case 1:
          answer1Text.setText(answersList.get(0).getAnswer());
          answer1Text.setStyle(
              "-fx-font-weight: bold;-fx-font-size:15;");
      }
    }
    else
    {
      endQuestionPressed.setVisible(false);
    }
    if (timer != null)
      timer.cancel();

    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask()
    {
      public void run()
      {
        timeLeft.set(setInterval() + "");
      }
    }, 1000, 1000);
  }

  public void submitAnswer(int i)
  {
    if (quizConverter.getUser() == null){
      quizConverter.sendAnswer(i);
      removeAnswers(false);
    }
  }

  public void removeAnswers(boolean t)
  {
    answer1.setVisible(false);
    answer2.setVisible(false);
    answer3.setVisible(false);
    answer4.setVisible(false);
    question.set("Waiting for next question");
    if(t)
      timer.cancel();
  }

  private final int setInterval()
  {
    if (interval == 1)
    {
      endQuestion();
      timer.cancel();
    }
    return --interval;
  }

  public SimpleStringProperty questionProperty()
  {
    return question;
  }

  public SimpleStringProperty timeLeftProperty()
  {
    return timeLeft;
  }

  public void endQuestion()
  {
    quizConverter.endQuestion();
  }

  public void addListener(String propertyChange, PropertyChangeListener propertyChange1)
  {
    quizConverter.addListener(propertyChange, propertyChange1);
  }
}
