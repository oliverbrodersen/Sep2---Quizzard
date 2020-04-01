package shared.networking;

import shared.transferobjects.Quiz;
import shared.transferobjects.Question;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote
{
  Quiz getQuiz() throws RemoteException;
  Question getNextQuestion() throws RemoteException;

  void registerClient(ClientCallback client) throws RemoteException;
  void removeClient(ClientCallback client) throws RemoteException;

  void startServer() throws RemoteException, AlreadyBoundException;
}
