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

  public QuizConverterManager(Client client) {
    this.client = client;
    support = new PropertyChangeSupport(this);
    // client.startClient();
    // client.addListener("OnJoin", this::onJoin);
  }


  private void onJoin(PropertyChangeEvent evt) {
    support.firePropertyChange(evt);
  }



  @Override public Lobby getLobby() {
    return client.getLobby();
  }

  @Override
  public List<Quiz> getQuizzes() {
    return client.getQuizzes();
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

  @Override public void setUsername(String username) {

  }

  @Override public void setPassword(String password) {

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
