package client.views.scoreboardview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.hostmain.HostMainVM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ScoreboardVC implements ViewController {
  private ScoreboardVM vm;
  @FXML private Button NextQuestionButton;
  @FXML private Rectangle a1Rec, a2Rec, a3Rec, a4Rec;
  @FXML private Text a1Text, a2Text, a3Text, a4Text, questionText, a1True, a2True, a3True, a4True;
  @FXML private VBox vBox1, vBox2, vBox3, vBox4;

  @Override
  public void init() {
    this.vm = (ScoreboardVM) ViewModelFactory.getInstance().getVM("scoreboard");
    a1Text.textProperty().bindBidirectional(vm.a1Property());
    a2Text.textProperty().bindBidirectional(vm.a2Property());
    a3Text.textProperty().bindBidirectional(vm.a3Property());
    a4Text.textProperty().bindBidirectional(vm.a4Property());
    questionText.textProperty().bindBidirectional(vm.questionProperty());

    vm.setup(a1Rec, a2Rec, a3Rec, a4Rec, a1True, a2True, a3True, a4True, vBox1, vBox2, vBox3, vBox4);

    if (vm.lastQuestion()){
      NextQuestionButton.setText("End quiz");
    }
  }

  @FXML public void nextQuestion(){
    vm.nextQuestion();
    if (!NextQuestionButton.getText().equals("End quiz"))
    {
      ViewHandler.getInstance().openView("questionview");
    }
    else{
      questionText.setText(vm.getWinner());
    }
  }

  @Override
  public void reset() {

  }
}
