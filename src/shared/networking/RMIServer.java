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
  void startQuiz(int pin, int quizID, String email) throws RemoteException;
  void getNextQuestion(int pin) throws RemoteException;
  UserID getUserID() throws RemoteException;

  void registerClient(int pin, ClientCallback client, UserID userID) throws RemoteException;
  void removeClient(ClientCallback client) throws RemoteException;

  List<Quiz> getQuizzes(String email)  throws RemoteException;
  UserClass getUser(String email)  throws RemoteException;;

  void startServer() throws RemoteException, AlreadyBoundException;
  ArrayList<Participant> getParticipants(int pin)throws RemoteException;
  void newParticipant(int pin, Participant participant)throws RemoteException;
  void newClientCallBack(int pin, ClientCallback clientCallback)throws RemoteException;
  void addLobby(Lobby lobby, ClientCallback client) throws RemoteException;
  Lobby getLobby(int pin) throws RemoteException;
  void endQuestion(int pin) throws RemoteException;

  void submitAnswer(int pin, int answer) throws RemoteException;

  boolean verifyLogin(String username) throws RemoteException;
  boolean verifyPin(String pin) throws RemoteException;

    int getNextQuestionID() throws RemoteException;

    // void questionCreated(Question );
}
