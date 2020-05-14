package client.core;

import client.views.ViewModel;
import client.views.createaccount.CreateAccountVM;
import client.views.crudquiz.crudquestion.CrudQuestionVM;
import client.views.finalscreen.FinalScreenVM;
import client.views.hostmain.HostMainVM;
import client.views.crudquiz.CrudQuizVM;
import client.views.lobbyview.LobbyVM;
import client.views.mainview.MainVM;
import client.views.questionview.QuestionVM;
import client.views.scoreboardview.ScoreboardVM;

public class ViewModelFactory {

  private static ViewModelFactory viewModelFactory;
  private MainVM mainVM;
  private HostMainVM hostMainVM;
  private CreateAccountVM createAccountVM;
  private FinalScreenVM finalScreenVM;
  private CrudQuizVM crudQuizVM;
  private LobbyVM lobbyVM;
  private QuestionVM questionVM;
  private ScoreboardVM scoreboardVM;
  private CrudQuestionVM crudQuestionVM;

  private ViewModelFactory() {
  }

  public ViewModel getVM(String id) {

    switch (id) {
      case "main":
        if(mainVM == null) {
          mainVM = new MainVM(ModelFactory.getInstance().getQuizConverter());
        }
        return mainVM;
      case "hostmain":
        if (hostMainVM == null) {
          hostMainVM = new HostMainVM(ModelFactory.getInstance().getQuizConverter());
        }
        return hostMainVM;
      case "createaccount":
        if (createAccountVM == null) {
          createAccountVM = new CreateAccountVM(ModelFactory.getInstance().getQuizConverter());
        }
        return createAccountVM;
      case "finalscreen":
        if (finalScreenVM == null) {
          finalScreenVM = new FinalScreenVM(ModelFactory.getInstance().getQuizConverter());
        }
        return finalScreenVM;
      case "crudquiz":
        if (crudQuizVM == null) {
          crudQuizVM = new CrudQuizVM(ModelFactory.getInstance().getQuizConverter());
        }
        return crudQuizVM;
      case "lobby":
        if (lobbyVM == null) {
          lobbyVM = new LobbyVM(ModelFactory.getInstance().getQuizConverter());
        }
        return lobbyVM;
      case "question":
        if (questionVM == null) {
          questionVM = new QuestionVM(ModelFactory.getInstance().getQuizConverter());
        }
        return questionVM;
      case "scoreboard":
        if (scoreboardVM == null) {
          scoreboardVM = new ScoreboardVM(ModelFactory.getInstance().getQuizConverter());
        }
        return scoreboardVM;
      case "crudquestion":
        if (crudQuestionVM == null) {
          crudQuestionVM = new CrudQuestionVM(ModelFactory.getInstance().getQuizConverter());
        }
        return crudQuestionVM;
    }
    return null;
  }

  public static ViewModelFactory getInstance() {
    if (viewModelFactory == null) {
      viewModelFactory = new ViewModelFactory();
    }
    return viewModelFactory;
  }

}
