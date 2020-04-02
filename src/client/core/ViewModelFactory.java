package client.core;

import client.views.mainview.MainViewModel;

public class ViewModelFactory {

  private final ModelFactory mf;

  private MainViewModel mainViewModel;

  public ViewModelFactory(ModelFactory mf) {
    this.mf = mf;
  }

  public MainViewModel getMainViewModel() {
    if(mainViewModel == null) {
      mainViewModel = new MainViewModel(mf.getQuizConverter());
    }
    return mainViewModel;
  }




}
