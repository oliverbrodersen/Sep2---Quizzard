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
import java.util.List;
import java.util.Scanner;

public class RMIClient implements Client, ClientCallback
{
  private RMIServer server;
  private PropertyChangeSupport support;
  private Quiz quiz;
  private int pinFromServer;
  private List<Quiz> quizzes = new ArrayList<>();

  public RMIClient() {
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient(int pin, UserID userID)
  {
    try {
      //UserID userIDUI = UserID.HOST;
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("QuizServer");
      if (userID == UserID.PARTICIPANT){
        server.registerClient(pin, this, userID);
        System.out.println("Client registered.");
      }
    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean verifyLogin(String username) {
    try {
      return server.verifyLogin(username);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return false;
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

  @Override public void getNextQuestion(int pin)
  {
    try
    {
      server.getNextQuestion(pin);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not get question");
    }
  }

  @Override public void returnNextQuestion(int pin, int num)
  {
    if (num != -1) {
      System.out.println(quiz.getQuestion(num));

      System.out.println("Please enter answer: ");
      Scanner scanner = new Scanner(System.in);
      int answer = scanner.nextInt();
      scanner.nextLine();
      sendAnswer(pin, answer);
    }
    else
      System.out.println("End of quiz, you lost");
  }

  @Override public void updatePin(int pin) throws RemoteException
  {
    pinFromServer = pin;
  }

  @Override public int getPin() throws RemoteException
  {
    return pinFromServer;
  }

  @Override public UserID getUserClass() {
    return null;
  }

  @Override
  public List<Quiz> getQuizzes() {
    try {
      return server.getQuizzes();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return quizzes;
  }

  @Override public int getUserID() {
    return 0;
  }

  @Override public String getPassword() {
    return null;
  }

  @Override public void sendAnswer(int pin, int answer)
  {
    try {
      server.submitAnswer(pin, answer);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @Override public void startQuiz(int pin, int quizID, String email)
  {
    try
    {
      server.startQuiz(pin, quizID, email);
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


  @Override public ArrayList<Participant> getParticipants(int pin)
  {
    try
    {
      return server.getParticipants(pin);
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

  @Override public void newParticipant(int pin, Participant participant)
  {
    try
    {
      server.newParticipant(pin, participant);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addLobby(Lobby lobby, ClientCallback client)
  {
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

  @Override public List<Participant> getParticipants()
  {
    return null;
  }

  @Override public Lobby getLobby(int pin)
  {
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

  @Override public Quiz getQuiz(Quiz quiz) throws RemoteException
  {
    //System.out.println(quiz);
    this.quiz = quiz;

    System.out.println("Quiz recieved");
    return null;
  }
}
