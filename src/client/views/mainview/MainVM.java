package client.views.mainview;

import client.model.QuizConverter;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import shared.transferobjects.Participant;
import shared.transferobjects.UserClass;
import shared.transferobjects.UserID;

import java.beans.PropertyChangeEvent;

public class MainVM {
  private ObservableList<Participant> participants;
  private QuizConverter quizConverter;
  private StringProperty username, password, quizID, loginError, joinError, nick;
  //quizID returns as string, may cause problems since lobby needs int


  public MainVM(QuizConverter quizConverter) {
    this.quizConverter = quizConverter;

    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    quizID = new SimpleStringProperty();
    nick = new SimpleStringProperty();
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
  public String getNick() {
    return nick.get();
  }
  public String getPassword() {
    return password.get();
  }

  public String getQuizID() {
    String quizString = quizID.toString();
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

  StringProperty nickProperty() { return nick; }

  public void setUser(){
    quizConverter.setUser(username.get());
  }

  public boolean CheckLogin() {
    return quizConverter.verifyLogin(username.get());
  }

  public boolean checkPin()
  {
    //Input lock
    String quizIdTrim = quizID.get().trim();
    String nickTrim = nick.get().trim();
      if (quizIdTrim == null || quizIdTrim.equals("")){
        joinError.set("Please enter pin");
        return false;
      }
      else if (nickTrim == null ||nickTrim.equals("")){
        joinError.set("Please nickname");
        return false;
      }
      return quizConverter.verifyPin(quizIdTrim);
  }

  public void addParticipant()
  {
    quizConverter.setPin(Integer.parseInt(quizID.get()));
    Participant participant = new Participant(nick.get());
    quizConverter.addParticipant(quizID.get(), participant);
  }
}
