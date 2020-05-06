package client.model;

import client.networking.Client;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class QuizConverterManager implements QuizConverter
{

  private PropertyChangeSupport support;
  private Client client;
  private UserClass user;

  public QuizConverterManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    // client.startClient();
    // client.addListener("OnJoin", this::onJoin);
  }

  private void onJoin(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public Lobby getLobby(int pin)
  {
    return client.getLobby(pin);
  }

  @Override public List<Quiz> getQuizzes()
  {
    return client.getQuizzes(user.getEmail());
  }

  @Override public List<Participant> getParticipants()
  {
    return client.getParticipants();
  }

  @Override public String getUsername()
  {
    return client.getUsername();
  }

  @Override public int getUserID()
  {
    return client.getUserID();
  }

  @Override public String getPassword()
  {
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

  @Override public Quiz getQuiz()
  {
    return client.getQuiz();
  }

  @Override public Participant getParticipant()
  {
    return client.getParticipant();
  }

  @Override public ArrayList<Integer> getAnswers(int question)
  {
    return client.getAnswers(question);
  }

  @Override public void setUser(String email)
  {
    this.user = client.getUser(email);
    user.setClient(client);
  }

  @Override public void setPin(int pin)
  {
    client.setPin(pin);
  }

  @Override public Quiz setQuiz(int quizID, String email)
  {
    return client.getQuiz(quizID, email);
  }

  @Override public void sendAnswer(int i)
  {
    client.sendAnswer(i);
  }

  @Override public void exit()
  {

  }

  @Override public void nextQuestion()
  {
    client.getNextQuestion();
  }

  @Override public boolean verifyLogin(String username)
  {
    return client.verifyLogin(username);
  }

  @Override public boolean verifyPin(String pin)
  {
    return client.verifyPin(pin);
  }

  @Override public void addParticipant(String pin, Participant participant)
  {
    client.newParticipant(Integer.parseInt(pin), participant);
  }

  @Override public void startQuiz()
  {
    client.startQuiz(client.getPin(),
        client.getLobby(client.getPin()).getQuiz().getQuizId(),
        user.getEmail());
  }

  @Override public int getNextQuestionID()
  {
    return client.getNextQuestionID();
  }

  @Override public void questionCreated(Question question)
  {
    client.questionCreated(question);
  }

  @Override public void createQuiz(String name, String subject,
      String difficulty, ArrayList<Question> questions)
  {

  }

  @Override public void kickPlayer(Participant participant)
  {
    client.kickPlayer(participant);
  }

  @Override
  public void deleteQuiz(Quiz quiz) {
    client.deleteQuiz(quiz);
  }

  @Override public void endQuestion()
  {
    client.endQuestion();
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    client.addListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {

  }
}
