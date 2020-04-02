package client.core;

import client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

  private Scene loginScene, hostScene;
  private Stage stage;
  private ViewModelFactory vmf;

  public ViewHandler(ViewModelFactory vmf) {
    this.vmf = vmf;
  }

  public void start() {
    stage = new Stage();
    openLoginView();
  }


  public void openLoginView() {
    if(loginScene == null) {
      try {
        Parent root = loadFXML("../views/mainview/mainview.fxml");

        stage.setTitle("Login");
        loginScene = new Scene(root);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    stage.setScene(loginScene);
    stage.show();
  }

  
  public Parent loadFXML(String path) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(path));
    Parent root = loader.load();

    ViewController ctrl = loader.getController();
    ctrl.init(this, vmf);
    return root;
  }


}
