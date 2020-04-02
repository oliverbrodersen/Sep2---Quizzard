package server.networking;

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
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer
{
  public QuizManager quizManager;
  public List<Participant> participantList;
  public Lobby lobby;

  public RMIServerImpl(QuizManager quizManager)
  {
    this.quizManager = quizManager;
    participantList = new ArrayList<>();
    lobby = null;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("QuizServer", this);
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

  @Override public Quiz getQuiz()
  {
    return null;
  }

  @Override public Question getNextQuestion()
  {
    return null;
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
