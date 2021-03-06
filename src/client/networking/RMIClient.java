package client.networking;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIClient implements Client, ClientCallback
{
  private RMIServer server;
  private PropertyChangeSupport support;
  private Quiz quiz;
  private Participant participant;
  private int pinFromServer = -1;
  private List<Quiz> quizzes = new ArrayList<>();

  public RMIClient()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("QuizServer");
    }
    catch (RemoteException | NotBoundException e)
    {
      System.out.println("Could not connect to server");
      e.printStackTrace();
    }
  }

  @Override public boolean verifyLogin(String username, String password)
  {
    try
    {
      return server.verifyLogin(username, password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean verifyPin(String pin)
  {
    try
    {
      return server.verifyPin(pin);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public int getNextQuestionID()
  {
    try
    {
      return server.getNextQuestionID();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return -1;
  }

  @Override public void questionCreated(Question question)
  {
    // server.questionCreated(question);
    support.firePropertyChange("onQuestionCreated", null, question);
  }

  @Override public void kickPlayer(Participant participant)
  {
    try
    {
      server.kickPlayer(participant, pinFromServer);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void createQuiz(String name, String subject, String difficulty, ArrayList<Question> questions, String email) {
    try {
      server.createQuiz(name, subject, difficulty, questions, email);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteQuiz(Quiz quiz) {
    try {
      server.deleteQuiz(quiz);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @Override public void updateScore(int score)
  {
    participant.setScore(score);

    try {
      server.updateScore(pinFromServer, participant);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @Override public Quiz getQuiz(int quizID, String email)
  {
    try
    {
      return server.getQuiz(quizID, email);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not get quiz");
    }
  }

  @Override public void returnNextQuestion()
  {
    quiz.nextQuestion();
    support.firePropertyChange("onNextQuestion", null, null);
  }

  @Override public void updatePin(int pin)
  {
    pinFromServer = pin;
  }

  @Override public int getPin()
  {
    return pinFromServer;
  }

  @Override public void getNextQuestion()
  {
    try
    {
      server.getNextQuestion(pinFromServer);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not get question");
    }
  }

  @Override public Participant getParticipant()
  {
    return participant;
  }

  @Override public ArrayList<Integer> getAnswers(int question)
  {
    try
    {
      return server.getAnswers(pinFromServer, question);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not get answers for question: " + question);
    }
  }

  @Override public Participant getWinner()
  {
    try
    {
      return server.getWinner(pinFromServer);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public UserID getUserClass()
  {
    return null;
  }

  @Override public List<Quiz> getQuizzes(String email)
  {
    try
    {
      return server.getQuizzes(email);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return quizzes;
  }

  @Override public UserClass getUser(String email, String password)
  {
    try
    {
      return server.getUser(email, password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public int getUserID()
  {
    return 0;
  }

  @Override public String getPassword()
  {
    return null;
  }

  @Override public void sendAnswer(int answer)
  {
    try
    {
      server.submitAnswer(pinFromServer, answer);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void startQuiz()
  {
    try
    {
      server.startQuiz(pinFromServer);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not start quiz");
    }
  }

  @Override public void endQuestion()
  {
    try
    {
      server.endQuestion(pinFromServer);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void endQuestionCall()
  {
    support.firePropertyChange("endQuestion", null, null);
  }

  @Override public void endQuiz() throws RemoteException
  {
    System.out.println("quiz over from client");
    support.firePropertyChange("endQuiz", null, null);
  }

  @Override public void kickPlayer()
  {
    support.firePropertyChange("onKick", null,null);
  }

  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {

    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }

  @Override public void update(Lobby lobby)
  {

  }


  @Override public ArrayList<Participant> getParticipants(int pin)
  {
    try
    {
      ArrayList<Participant> players = server.getParticipants(pin);
      support.firePropertyChange("onNewConnected", null, players);
      return players;
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public String getUsername()
  {
    return null;
  }

  @Override public void setPin(int pin)
  {
    pinFromServer = pin;
  }

  @Override public void newParticipant(int pin, Participant participant)
  {
    try
    {
      this.participant = participant;
      participant.setClientCallback(this);
      server.newParticipant(pin, participant);
      server.registerClient(pin, this, UserID.PARTICIPANT);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void removeParticipant(int pin, Participant participant)
  {

  }

  @Override public void addLobby(Lobby lobby, ClientCallback client){
    try
    {
      server.addLobby(lobby, client);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public Lobby getLobby()
  {
    return null;
  }

  @Override public List<Participant> getParticipants(){
    return getParticipants(pinFromServer);
  }

  @Override public Lobby getLobby(int pin){
    try
    {
      return server.getLobby(pin);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Quiz getQuiz()
  {
    return quiz;
  }

  @Override public Quiz getQuiz(Quiz quiz)
  {
    this.quiz = quiz;
    support.firePropertyChange("onQuizStarted", null, quiz);
    return quiz;
  }
}
