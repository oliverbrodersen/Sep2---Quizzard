package client.core;

import client.views.hostmain.HostMainVM;
import client.views.mainview.MainVM;

public class ViewModelFactory {

  private final ModelFactory mf;

  private MainVM mainVM;
  private HostMainVM hostMainVM;

  public ViewModelFactory(ModelFactory mf) {
    this.mf = mf;
  }

  public MainVM getMainVM() {
    if(mainVM == null) {
      mainVM = new MainVM(mf.getQuizConverter());
    }
    return mainVM;
  }

  public HostMainVM getHostMainVM() {
    if (hostMainVM == null) {
      hostMainVM = new HostMainVM(mf.getQuizConverter());
    }
    return hostMainVM;
  }




}
