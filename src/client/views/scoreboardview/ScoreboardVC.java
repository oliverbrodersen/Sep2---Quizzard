package client.views.scoreboardview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.hostmain.HostMainVM;
import javafx.fxml.FXML;

public class ScoreboardVC implements ViewController {
  private ScoreboardVM vm;
  private ViewHandler vh;

  @Override
  public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    this.vm = vmf.getScoreboardVM();
  }

  @FXML public void nextQuestion(){
    vm.nextQuestion();
    vh.openView("questionview");
  }

  @Override
  public void reset() {

  }
}
