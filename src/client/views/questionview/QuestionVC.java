package client.views.questionview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;

public class QuestionVC implements ViewController {
  private QuestionVM vm;
  private ViewHandler vh;

  @Override
  public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    this.vm = vmf.getQuestionVM();
  }

  @Override
  public void reset() {

  }
}

