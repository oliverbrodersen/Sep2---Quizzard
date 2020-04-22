package client.networking;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClient implements Client, ClientCallback
{
  private RMIServer server;
  private PropertyChangeSupport support;
  private Quiz quiz;

  public RMIClient() {
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("QuizServer");
      server.registerClient(this);
      System.out.println("Client registered.");
    } catch (RemoteException | NotBoundException e) {
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

  @Override public void getNextQuestion(int quizID, String email)
  {
    try
    {
      server.getNextQuestion(quizID, email);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not get question");
    }
  }

  @Override public void returnNextQuestion(int num)
  {
    if (num != -1)
      System.out.println(quiz.getQuestion(num));
    else
      System.out.println("End of quiz, you lost");
  }

  @Override public UserID getUserClass() {
    return null;
  }

  @Override public int getUserID() {
    return 0;
  }

  @Override public String getPassword() {
    return null;
  }

  @Override public void sendAnswer(Answer answer)
  {

  }

  @Override public void startQuiz(int quizID, String email)
  {
    try
    {
      server.startQuiz(quizID, email);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not start quiz");
    }
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
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

  public void connected()
  {
    System.out.println("New connection.");
  }

  @Override public ArrayList<Participant> getParticipants()
  {
    try
    {
      return server.getParticipants();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public String getUsername() {
    return null;
  }

  @Override public void newParticipant(Participant participant)
  {
    try
    {
      server.newParticipant(participant);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void setLobby(Lobby lobby)
  {
    try
    {
      server.setLobby(lobby);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public Lobby getLobby()
  {
    try
    {
      return server.getLobby();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Quiz getQuiz(Quiz quiz) throws RemoteException
  {
    //System.out.println(quiz);
    this.quiz = quiz;
    System.out.println("Quiz recieved");
    return null;
  }
}
