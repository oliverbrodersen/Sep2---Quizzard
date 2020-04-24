package client.views.mainview;

import client.model.QuizConverter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import shared.transferobjects.Participant;
import shared.transferobjects.UserClass;
import shared.transferobjects.UserID;

import java.beans.PropertyChangeEvent;

public class MainVM {

  private ObservableList<Participant> participants;
  private QuizConverter quizConverter;
  private StringProperty username, password, quizID, loginError, joinError;
  //quizID returns as string, may cause problems since lobby needs int


  public MainVM(QuizConverter quizConverter) {
    this.quizConverter = quizConverter;

    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    quizID = new SimpleStringProperty();
    loginError = new SimpleStringProperty();
    joinError = new SimpleStringProperty();
    quizConverter.addListener("OnJoin", this::onJoin);
  }

  private void onJoin(PropertyChangeEvent evt) {
    participants.add((Participant) evt.getNewValue());
  }



  public String getUsername() {
    return username.get();
  }

  public String getPassword() {
    return password.get();
  }

  public String getQuizID() {
    String quizString = quizID.toString();
    int quizIDint = Integer.parseInt(quizString);
    return quizID.get();
  }

  public String getLoginError() {
    return loginError.get();
  }

  public String getJoinError() {
    return joinError.get();
  }


  StringProperty usernameProperty() {
    return username;
  }

  StringProperty passwordProperty() {
    return password;
  }

  StringProperty quizIDProperty() {
    return quizID;
  }

  StringProperty loginErrorProperty() {
    return loginError;
  }

  StringProperty joinErrorProperty() {
    return joinError;
  }

  public void exit() {
    quizConverter.exit();
  }

  public void setUser(){
    quizConverter.setUser(username.get());
  }

  public boolean CheckLogin() {
    boolean loginSuccessful = quizConverter.verifyLogin(username.get());
    return loginSuccessful;
  }
}
