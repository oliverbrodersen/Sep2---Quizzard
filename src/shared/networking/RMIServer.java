package shared.networking;

import shared.transferobjects.Lobby;
import shared.transferobjects.Participant;
import shared.transferobjects.Quiz;
import shared.transferobjects.Question;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIServer extends Remote
{
  Quiz getQuiz() throws RemoteException;
  Question getNextQuestion() throws RemoteException;

  void registerClient(ClientCallback client) throws RemoteException;
  void removeClient(ClientCallback client) throws RemoteException;

  void startServer() throws RemoteException, AlreadyBoundException;
  ArrayList<Participant> getParticipants()throws RemoteException;
  void newParticipant(Participant participant)throws RemoteException;
  void setLobby(Lobby lobby) throws RemoteException;
  Lobby getLobby() throws RemoteException;
}
