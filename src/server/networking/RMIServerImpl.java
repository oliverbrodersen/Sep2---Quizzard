package server.networking;

import server.model.QuizManager;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.Lobby;
import shared.transferobjects.Question;
import shared.transferobjects.Quiz;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements RMIServer
{
  public QuizManager quizManager;

  public RMIServerImpl(QuizManager quizManager)
  {
    this.quizManager = quizManager;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("QuizServer", this);
    System.out.println("Server started.");
  }

  @Override public Quiz getQuiz()
  {
    return null;
  }

  @Override public Question getNextQuestion()
  {
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
