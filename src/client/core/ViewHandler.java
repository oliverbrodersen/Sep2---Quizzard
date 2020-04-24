package client.core;

import client.model.QuizConverter;
import client.views.ViewController;
import client.views.hostmain.HostMainVC;
import client.views.mainview.MainVC;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

  private Stage stage;
  private Scene currentScene;

  private ViewModelFactory vmf;


  public ViewHandler(ViewModelFactory vmf) {
    this.vmf = vmf;
  }

  public void start() {
    this.stage = new Stage();
    openView("login");
  }

  public void openView(String id) {

    Parent root = null;
    switch (id) {
      case "login":
        root = loadFXML("../views/mainview/mainview.fxml");
        break;
      case "hostmain":
        root = loadFXML("../views/hostmain/hostmain.fxml");
        break;
      case "createaccount":
        root = loadFXML("../views/createaccount/createaccount.fxml");
        break;
      case "finalscreen":
        root = loadFXML("../views/finalscreen/finalscreen.fxml");
        break;
      case "createquiz":
        root = loadFXML("../views/hostmain/createquiz/createquiz.fxml");
        break;
      case "lobbyview":
        root = loadFXML("../views/lobbyview/lobbyview.fxml");
        break;
      case "questionview":
        root = loadFXML("../views/questionview/questionview.fxml");
        break;
      case "scoreboardview":
        root = loadFXML("../views/scoreboardview/scoreboardview.fxml");
        break;
    }
    currentScene = new Scene(root);

    String title = "";
    if(root.getUserData() != null) {
      title += root.getUserData();
    }

    stage.setTitle(title);
    stage.setScene(currentScene);
    stage.show();
  }

  private Parent loadFXML(String path)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(path));
    Parent root = null;
    try {
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ViewController ctrl = loader.getController();
    System.out.println(ctrl);
    ctrl.init(this, vmf);
    return root;
  }

}
