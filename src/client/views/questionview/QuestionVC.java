package client.views.questionview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.beans.PropertyChangeEvent;

public class QuestionVC implements ViewController {
  private QuestionVM vm;
  @FXML Text numberOfQuestionsText, quizNameText, timeLeftText, questionText, answer1Text, answer2Text, answer3Text, answer4Text, scoreText;
  @FXML Button answer1Button, answer2Button, answer3Button, answer4Button, endQuestionPressed;

  @Override
  public void init() {
    this.vm = (QuestionVM) ViewModelFactory.getInstance().getVM("question");
    vm.addListener("endQuestion", this::removeAnswers);
    vm.addListener("endQuiz", this::endQuiz);
    vm.addListener("onNextQuestion", this::onNextQuestion);
    numberOfQuestionsText.textProperty().bindBidirectional(vm.numberOfQuestionsProperty());
    quizNameText.textProperty().bindBidirectional(vm.quizNameProperty());
    questionText.textProperty().bindBidirectional(vm.questionProperty());
    timeLeftText.textProperty().bindBidirectional(vm.timeLeftProperty());
    scoreText.textProperty().bindBidirectional(vm.scoreProperty());
    vm.setup(answer1Button, answer2Button, answer3Button, answer4Button, endQuestionPressed, answer1Text, answer2Text, answer3Text, answer4Text);
  }

  private void endQuiz(PropertyChangeEvent evt)
  {
    vm.endQuiz();
  }

  public void removeAnswers(PropertyChangeEvent evt){
    vm.removeAnswers(true);
  }

  public void onNextQuestion(PropertyChangeEvent evt){
    if (!vm.isQuizOver())
    {
      reset();
      vm.setup(answer1Button, answer2Button, answer3Button, answer4Button,
          endQuestionPressed, answer1Text, answer2Text, answer3Text, answer4Text);
    }
    else{
      vm.removeListener("endQuestion", this::removeAnswers);
      vm.removeListener("endQuiz", this::endQuiz);
      vm.removeListener("onNextQuestion", this::onNextQuestion);

      Platform.runLater(new Runnable(){
        @Override public void run()
        {
          if (vm.getUser() == null)
            ViewHandler.getInstance().openView("login");
          else
            ViewHandler.getInstance().openView("hostmain");
        }
      });
    }
  }
  @Override
  public void reset() {
    answer1Button.setVisible(true);
    answer2Button.setVisible(true);
    answer3Button.setVisible(true);
    answer4Button.setVisible(true);
  }
  @FXML public void answer1Pressed(){
    vm.submitAnswer(0);
  }
  @FXML public void answer2Pressed(){
    vm.submitAnswer(1);
  }
  @FXML public void answer3Pressed(){
    vm.submitAnswer(2);
  }
  @FXML public void answer4Pressed(){
    vm.submitAnswer(3);
  }
  @FXML public void endQuestionPressed(){
    vm.endQuestion();
    ViewHandler.getInstance().openView("scoreboardview");
  }
}

