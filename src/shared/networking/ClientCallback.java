package shared.networking;

import shared.transferobjects.Lobby;
import shared.transferobjects.Participant;
import shared.transferobjects.Question;
import shared.transferobjects.Quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientCallback extends Remote
{
  void update(Lobby lobby) throws RemoteException;
  void connected() throws RemoteException;
  ArrayList<Participant> getParticipants(int pin) throws RemoteException;
  void newParticipant(int pin, Participant participant)throws RemoteException;
  void addLobby(Lobby lobby, ClientCallback client)throws RemoteException;
  Lobby getLobby()throws RemoteException;
  Quiz getQuiz(Quiz quiz) throws RemoteException;
  void getNextQuestion(int pin) throws RemoteException;
  void returnNextQuestion(int pin, int num) throws RemoteException;
  void updatePin(int pin) throws RemoteException;
}
