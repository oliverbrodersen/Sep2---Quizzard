package client.core;

import client.model.QuizConverter;
import client.views.ViewController;
import client.views.mainview.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

  private Scene currentScene;
  private Stage primaryStage;

  private ViewModelFactory vmf;
  private QuizConverter quizConverter;
  private MainViewController mainViewController;

  public ViewHandler(ViewModelFactory vmf) {
    this.vmf = vmf;
  }

  public void start() {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("login");
  }


  public void openView(String id) {

    Region root = null;
    switch (id) {
      case "login":
        root = loadView("../views/mainview/mainview.fxml", mainViewController);
        break;
    }
    currentScene.setRoot(root);

    String title = "";
    if(root.getUserData() != null) {

      title += root.getUserData();
    }

    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.show();
  }


  private Region loadView(String fxmlFile, ViewController controller) {

    if (controller == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        controller = loader.getController();
        controller.init(this, vmf, root);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    else {
      controller.reset();
    }
    return controller.getRoot();
  }


}
