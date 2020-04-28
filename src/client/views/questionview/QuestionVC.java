package client.views.questionview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class QuestionVC implements ViewController {
  private QuestionVM vm;
  private ViewHandler vh;
  @FXML Text numberOfQuestionsText, quizNameText;

  @Override
  public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    this.vm = vmf.getQuestionVM();
    numberOfQuestionsText.textProperty().bindBidirectional(vm.numberOfQuestionsProperty());
    quizNameText.textProperty().bindBidirectional(vm.quizNameProperty());
    vm.setup();
  }

  @Override
  public void reset() {

  }
}

