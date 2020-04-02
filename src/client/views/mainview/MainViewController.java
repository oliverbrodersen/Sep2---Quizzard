package client.views.mainview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.QuizConverter;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Region;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.util.converter.IntegerStringConverter;

import java.awt.*;
import java.util.regex.Pattern;

public class MainViewController implements ViewController {

  @FXML private TextField quizIDField, usernameField;
  @FXML private PasswordField passwordField;
  @FXML private Label errorJoinLabel, errorLoginLabel;

  private MainViewModel vm;
  private ViewHandler vh;
  private Region root;


  public MainViewController() { }



  @Override public void init(ViewHandler vh, ViewModelFactory vmf, Region root) {
    this.vh = vh;
    this.vm = vmf.getMainViewModel();
    this.root = root;

    usernameField.textProperty().bindBidirectional(vm.usernameProperty());
    passwordField.textProperty().bindBidirectional(vm.passwordProperty());
    quizIDField.textProperty().bindBidirectional(vm.quizIDProperty());

    errorJoinLabel.textProperty().bindBidirectional(vm.joinErrorProperty());
    errorLoginLabel.textProperty().bindBidirectional(vm.loginErrorProperty());

  }


  @Override public Region getRoot() {
    return root;
  }

  @Override public void reset() { }


  @FXML public void onJoinPressed() {
    //vh.openView("participantLobby");
    System.out.println("join pressed");
  }

  @FXML public void onLoginPressed() {
    vh.openView("host");
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
