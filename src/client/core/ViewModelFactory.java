package client.core;

import client.views.createaccount.CreateAccountVM;
import client.views.finalscreen.FinalScreenVM;
import client.views.hostmain.HostMainVM;
import client.views.hostmain.createquiz.CreateQuizVM;
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
  private CreateQuizVM createQuizVM;
  private LobbyVM lobbyVM;
  private QuestionVM questionVM;
  private ScoreboardVM scoreboardVM;

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

  public CreateAccountVM getCreateAccountVM() {
    if (createAccountVM == null) {
      createAccountVM = new CreateAccountVM(mf.getQuizConverter());
    }
    return createAccountVM;
  }

  public FinalScreenVM getFinalScreenVM() {
    if (finalScreenVM == null) {
      finalScreenVM = new FinalScreenVM(mf.getQuizConverter());
    }
    return finalScreenVM;
  }

  public CreateQuizVM getCreateQuizVM() {
    if (createQuizVM == null) {
      createQuizVM = new CreateQuizVM(mf.getQuizConverter());
    }
    return createQuizVM;
  }

  public LobbyVM getLobbyVM() {
    if (lobbyVM == null) {
      lobbyVM = new LobbyVM(mf.getQuizConverter());
    }
    return lobbyVM;
  }

  public QuestionVM getQuestionVM() {
    if (questionVM == null) {
      questionVM = new QuestionVM(mf.getQuizConverter());
    }
    return questionVM;
  }

  public ScoreboardVM getScoreboardVM() {
    if (scoreboardVM == null) {
      scoreboardVM = new ScoreboardVM(mf.getQuizConverter());
    }
    return scoreboardVM;
  }
}
