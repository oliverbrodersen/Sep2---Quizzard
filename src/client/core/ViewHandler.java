package client.core;

import client.model.QuizConverter;
import client.views.ViewController;
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
  private QuizConverter quizConverter;
  private MainVC mainVC;

  public ViewHandler(ViewModelFactory vmf) {
    this.vmf = vmf;
  }

  public void start() {
    this.stage = new Stage();
    this.currentScene = new Scene(new Region());
    openView("login");
  }


  public void openView(String id) {

    Parent root = null;
    switch (id) {
      case "login":
        root = loadFXML("../views/mainview/mainview.fxml");
        break;
      case "hostMain":
        root = loadFXML("../views/hostmain/hostmain.fxml");
    }
    currentScene.setRoot(root);

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
    ctrl.init(this, vmf);
    return root;
  }
}
