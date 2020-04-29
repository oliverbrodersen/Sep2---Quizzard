package client.views.questionview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.beans.PropertyChangeEvent;

public class QuestionVC implements ViewController {
  private QuestionVM vm;
  private ViewHandler vh;
  @FXML Text numberOfQuestionsText, quizNameText, timeLeftText, questionText, answer1Text, answer2Text, answer3Text, answer4Text;
  @FXML Button answer1Button, answer2Button, answer3Button, answer4Button, endQuestionPressed;

  @Override
  public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    this.vm = vmf.getQuestionVM();
    vm.addListener("endQuestion", this::removeAnswers);
    vm.addListener("onNextQuestion", this::onNextQuestion);
    numberOfQuestionsText.textProperty().bindBidirectional(vm.numberOfQuestionsProperty());
    quizNameText.textProperty().bindBidirectional(vm.quizNameProperty());
    questionText.textProperty().bindBidirectional(vm.questionProperty());
    timeLeftText.textProperty().bindBidirectional(vm.timeLeftProperty());
    //answer1Text.textProperty().bindBidirectional(vm.answer1TextProperty());
    //answer2Text.textProperty().bindBidirectional(vm.answer2TextProperty());
    //answer3Text.textProperty().bindBidirectional(vm.answer3TextProperty());
    //answer4Text.textProperty().bindBidirectional(vm.answer4TextProperty());
    vm.setup(answer1Button, answer2Button, answer3Button, answer4Button, endQuestionPressed, answer1Text, answer2Text, answer3Text, answer4Text);
  }
  public void removeAnswers(PropertyChangeEvent evt){
    vm.removeAnswers(true);
  }

  public void onNextQuestion(PropertyChangeEvent evt){
    reset();
    vm.setup(answer1Button, answer2Button, answer3Button, answer4Button, endQuestionPressed, answer1Text, answer2Text, answer3Text, answer4Text);
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
    vh.openView("scoreboardview");
  }
}

