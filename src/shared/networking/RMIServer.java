package shared.networking;

import shared.transferobjects.*;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface RMIServer extends Remote
{
  Quiz getQuiz(int quizID, String email) throws RemoteException;
  void startQuiz(int quizID, String email) throws RemoteException;
  void getNextQuestion() throws RemoteException;
  UserID getUserID() throws RemoteException;

  void registerClient(ClientCallback client, UserID userID) throws RemoteException;
  void removeClient(ClientCallback client) throws RemoteException;

  List<Quiz> getQuizzes()  throws RemoteException;

  void startServer() throws RemoteException, AlreadyBoundException;
  ArrayList<Participant> getParticipants()throws RemoteException;
  void newParticipant(Participant participant)throws RemoteException;
  void setLobby(Lobby lobby) throws RemoteException;
  Lobby getLobby() throws RemoteException;

  void submitAnswer(int answer) throws RemoteException;

  boolean verifyLogin(String username) throws RemoteException;
}
