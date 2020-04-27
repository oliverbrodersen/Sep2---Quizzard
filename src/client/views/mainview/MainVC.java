package client.views.mainview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainVC implements ViewController
{

  @FXML private TextField quizIDField, usernameField, nickField;
  @FXML private PasswordField passwordField;
  @FXML private Label errorJoinLabel, errorLoginLabel;

  private MainVM vm;
  private ViewHandler vh;

  public MainVC()
  {
  }

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.vm = vmf.getMainVM();
    //try{
    //  this.vm = vmf.getMainVM();
    //} catch (RemoteException | NotBoundException e) {
    //  System.out.println("Caught from view");
    //}

    usernameField.textProperty().bindBidirectional(vm.usernameProperty());
    passwordField.textProperty().bindBidirectional(vm.passwordProperty());
    quizIDField.textProperty().bindBidirectional(vm.quizIDProperty());
    nickField.textProperty().bindBidirectional(vm.nickProperty());

    errorJoinLabel.textProperty().bindBidirectional(vm.joinErrorProperty());
    errorLoginLabel.textProperty().bindBidirectional(vm.loginErrorProperty());

  }

  @Override public void reset()
  {
  }

  @FXML public void onJoinPressed()
  {
    //vh.openView("participantLobby");
    if (vm.checkPin())
    {
      vm.addParticipant();
      vh.openView("lobbyview");
    }
    else
      errorJoinLabel.setText("Could not connect to pin");
  }

  @FXML public void onLoginPressed()
  {
    if (vm.CheckLogin())
    {
      vm.setUser();
      vh.openView("hostmain");
    }
    else
      errorLoginLabel.setText("Not a valid user");
  }

  @FXML public void onCreatePressed()
  {
    //vh.openView("create");
    System.out.println("CreatePressed");
  }

}
