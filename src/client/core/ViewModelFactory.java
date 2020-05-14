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

  private final ModelFactory mf;

  private MainVM mainVM;
  private HostMainVM hostMainVM;
  private CreateAccountVM createAccountVM;
  private FinalScreenVM finalScreenVM;
  private CrudQuizVM crudQuizVM;
  private LobbyVM lobbyVM;
  private QuestionVM questionVM;
  private ScoreboardVM scoreboardVM;
  private CrudQuestionVM crudQuestionVM;

  public ViewModelFactory(ModelFactory mf) {
    this.mf = mf;
  }

  public ViewModel getVM(String id) {

    switch (id) {
      case "main":
        if(mainVM == null) {
          mainVM = new MainVM(mf.getQuizConverter());
        }
        return mainVM;
      case "hostmain":
        if (hostMainVM == null) {
          hostMainVM = new HostMainVM(mf.getQuizConverter());
        }
        return hostMainVM;
      case "createaccount":
        if (createAccountVM == null) {
          createAccountVM = new CreateAccountVM(mf.getQuizConverter());
        }
        return createAccountVM;
      case "finalscreen":
        if (finalScreenVM == null) {
          finalScreenVM = new FinalScreenVM(mf.getQuizConverter());
        }
        return finalScreenVM;
      case "crudquiz":
        if (crudQuizVM == null) {
          crudQuizVM = new CrudQuizVM(mf.getQuizConverter());
        }
        return crudQuizVM;
      case "lobby":
        if (lobbyVM == null) {
          lobbyVM = new LobbyVM(mf.getQuizConverter());
        }
        return lobbyVM;
      case "question":
        if (questionVM == null) {
          questionVM = new QuestionVM(mf.getQuizConverter());
        }
        return questionVM;
      case "scoreboard":
        if (scoreboardVM == null) {
          scoreboardVM = new ScoreboardVM(mf.getQuizConverter());
        }
        return scoreboardVM;
      case "crudquestion":
        if (crudQuestionVM == null) {
          crudQuestionVM = new CrudQuestionVM(mf.getQuizConverter());
        }
        return crudQuestionVM;
    }
    return null;
  }

// //Done
// public MainVM getMainVM() {
//   if(mainVM == null) {
//     mainVM = new MainVM(mf.getQuizConverter());
//   }
//   return mainVM;
// }

// //Done
// public HostMainVM getHostMainVM() {
//   if (hostMainVM == null) {
//     hostMainVM = new HostMainVM(mf.getQuizConverter());
//   }
//   return hostMainVM;
// }
//
// //Done
// public CreateAccountVM getCreateAccountVM() {
//   if (createAccountVM == null) {
//     createAccountVM = new CreateAccountVM(mf.getQuizConverter());
//   }
//   return createAccountVM;
// }

// //Done
// public FinalScreenVM getFinalScreenVM() {
//   if (finalScreenVM == null) {
//     finalScreenVM = new FinalScreenVM(mf.getQuizConverter());
//   }
//   return finalScreenVM;
// }

// //Done
// public CrudQuizVM getCrudQuizVM() {
//   if (crudQuizVM == null) {
//     crudQuizVM = new CrudQuizVM(mf.getQuizConverter());
//   }
//   return crudQuizVM;
// }

// //Done
// public LobbyVM getLobbyVM() {
//   if (lobbyVM == null) {
//     lobbyVM = new LobbyVM(mf.getQuizConverter());
//   }
//   return lobbyVM;
// }

// //Done
// public QuestionVM getQuestionVM() {
//   if (questionVM == null) {
//     questionVM = new QuestionVM(mf.getQuizConverter());
//   }
//   return questionVM;
// }

// //Done
// public ScoreboardVM getScoreboardVM() {
//   if (scoreboardVM == null) {
//     scoreboardVM = new ScoreboardVM(mf.getQuizConverter());
//   }
//   return scoreboardVM;
// }

// //DOne
// public CrudQuestionVM getCrudQuestionVM() {
//   if (crudQuestionVM == null) {
//     crudQuestionVM = new CrudQuestionVM(mf.getQuizConverter());
//   }
//   return crudQuestionVM;
// }
}
