package client.views.scoreboardview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.hostmain.HostMainVM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ScoreboardVC implements ViewController {
  private ScoreboardVM vm;
  private ViewHandler vh;
  @FXML private Button NextQuestionButton;
  @Override
  public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    this.vm = vmf.getScoreboardVM();
    if (vm.lastQuestion()){
      NextQuestionButton.setText("End quiz");
    }
  }

  @FXML public void nextQuestion(){
    vm.nextQuestion();
    if (!NextQuestionButton.getText().equals("End quiz"))
    {
      vh.openView("questionview");
    }
  }

  @Override
  public void reset() {

  }
}
