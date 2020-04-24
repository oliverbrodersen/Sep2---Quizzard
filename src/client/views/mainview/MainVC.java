package client.views.mainview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class MainVC implements ViewController {

  @FXML private TextField quizIDField, usernameField;
  @FXML private PasswordField passwordField;
  @FXML private Label errorJoinLabel, errorLoginLabel;

  private MainVM vm;
  private ViewHandler vh;


  public MainVC() { }



  @Override public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    this.vm = vmf.getMainVM();

    usernameField.textProperty().bindBidirectional(vm.usernameProperty());
//    passwordField.textProperty().bindBidirectional(vm.passwordProperty());
//    quizIDField.textProperty().bindBidirectional(vm.quizIDProperty());
//
//    errorJoinLabel.textProperty().bindBidirectional(vm.joinErrorProperty());
//    errorLoginLabel.textProperty().bindBidirectional(vm.loginErrorProperty());

  }


  @Override public void reset() { }


  @FXML public void onJoinPressed() {
    //vh.openView("participantLobby");
    System.out.println("join pressed");
  }

  @FXML public void onLoginPressed() {
    boolean loginCheck = vm.CheckLogin();
    if (loginCheck){
      vm.setUser();
      vh.openView("hostmain");
    }
  }

  @FXML public void onCreatePressed() {
    //vh.openView("create");
    System.out.println("CreatePressed");
  }

  @FXML public void onExitPressed() {
    vm.exit();
    Platform.exit();
  }











}
