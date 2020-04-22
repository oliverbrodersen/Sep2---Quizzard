package server.networking;

import server.DAO.DatabaseConnection;
import server.DAO.QuizData;
import server.DAO.QuizHandler;
import server.model.QuizManager;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer
{
  private QuizManager quizManager;
  private List<Participant> participantList;
  private List<ClientCallback> clientList;
  private QuizData quizData;
  private Lobby lobby;
  private DatabaseConnection DBConn;

  public RMIServerImpl(QuizManager quizManager)
  {
    this.quizManager = quizManager;
    participantList = new ArrayList<>();
    clientList = new ArrayList<>();
    lobby = null;
    DBConn = new DatabaseConnection();
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("QuizServer", this);
    DBConn.startDB();
    System.out.println("Server started.");
  }

  @Override public ArrayList<Participant> getParticipants()
  {
    return (ArrayList<Participant>)participantList;
  }

  @Override public void newParticipant(Participant participant)
  {
    participantList.add(participant);
  }

  @Override public void setLobby(Lobby lobby) throws RemoteException
  {
   this.lobby = lobby;
  }

  @Override public Lobby getLobby() throws RemoteException
  {
    return this.lobby;
  }

  @Override public Quiz getQuiz(int quizID, String email)
  {
    Quiz quiz = null;
    if (quizData == null)
    {
      quizData = new QuizHandler(DBConn);
    }
    try {
      quiz = quizData.readQuiz(quizID, email);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return quiz;
  }

  @Override public void startQuiz(int quizID, String email) throws RemoteException
  {
    System.out.println("Connected clients: " + clientList.size());
    for (int i = 0; i < clientList.size(); i++)
    {
      clientList.get(i).getQuiz(getQuiz(quizID, email));
    }
  }

  @Override public void getNextQuestion(int quizID, String email) throws RemoteException
  {
    int num = getQuiz(quizID, email).nextQuestion();
    System.out.println("Question number:" + num);
    for (int i = 0; i < clientList.size(); i++)
    {
      clientList.get(i).returnNextQuestion(num);
    }
  }

  @Override public UserID getUserID() {
    return null;
  }

  @Override public void registerClient(ClientCallback client)
  {
    PropertyChangeListener listener = null;
    PropertyChangeListener finalListener = listener;

    listener = evt -> {
      try {
        client.update((Lobby) evt.getNewValue());
      } catch (RemoteException e) {
        e.printStackTrace();
        quizManager.removeListener("Lobby", finalListener);
      }
    };
    quizManager.addListener("Lobby", listener);
    clientList.add(client);
    System.out.println("Client successfully connected.");
    try
    {
      client.connected();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void removeClient(ClientCallback client)
  {

  }
}
