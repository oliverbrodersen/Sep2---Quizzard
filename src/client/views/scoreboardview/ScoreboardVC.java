package client.views.scoreboardview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.hostmain.HostMainVM;

public class ScoreboardVC implements ViewController {
  private ScoreboardVM vm;
  private ViewHandler vh;

  @Override
  public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    this.vm = vmf.getScoreboardVM();
  }

  @Override
  public void reset() {

  }
}