package client.views.mainview;

import client.model.QuizConverter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import shared.transferobjects.Participant;

import java.beans.PropertyChangeEvent;

public class MainViewModel {

  private ObservableList<Participant> participants;
  private QuizConverter quizConverter;
  private StringProperty username, password, quizID, loginError, joinError;
  //quizID returns as string, may cause problems since lobby needs int


  public MainViewModel(QuizConverter quizConverter) {
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


}
