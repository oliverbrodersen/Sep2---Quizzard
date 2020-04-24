package client.model;

import client.networking.Client;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class QuizConverterManager implements QuizConverter
{

  private PropertyChangeSupport support;
  private Client client;
  private UserClass user;

  public QuizConverterManager(Client client) {
    this.client = client;
    support = new PropertyChangeSupport(this);
    // client.startClient();
    // client.addListener("OnJoin", this::onJoin);
  }


  private void onJoin(PropertyChangeEvent evt) {
    support.firePropertyChange(evt);
  }



  @Override public Lobby getLobby(int pin) {
    return client.getLobby(pin);
  }

  @Override
  public List<Quiz> getQuizzes() {
    return client.getQuizzes(user.getEmail());
  }


  @Override public List<Participant> getParticipants() {
    return null;
  }

  @Override public Question getNextQuestion() {
    return null;
  }

  @Override public String getUsername() {
    return client.getUsername();
  }

  @Override public int getUserID() {
    return client.getUserID();
  }

  @Override public String getPassword() {
    return client.getPassword();
  }

  @Override public UserClass getUser()
  {
    return user;
  }

  @Override public int getPin()
  {
    return client.getPin();
  }

  @Override public void setUsername(String username) {

  }

  @Override public void setPassword(String password) {

  }

  @Override public void setUser(String email)
  {
    this.user = client.getUser(email);
    System.out.println(user.getEmail());
    user.setClient(client);
  }

  @Override public void addNewParticipant(Participant participant) {

  }

  @Override public Quiz setQuiz(int quizID, String email)
  {
    return client.getQuiz(quizID, email);
  }

  @Override public void sendAnswer() {

  }

  @Override public void exit() {

  }

  @Override
  public boolean verifyLogin(String username) {
    return client.verifyLogin(username);
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener) {

  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener) {

  }
}
