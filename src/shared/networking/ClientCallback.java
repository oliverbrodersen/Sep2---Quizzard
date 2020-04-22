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
  ArrayList<Participant> getParticipants()throws RemoteException;
  void newParticipant(Participant participant)throws RemoteException;
  void setLobby(Lobby lobby)throws RemoteException;
  Lobby getLobby()throws RemoteException;
  Quiz getQuiz(Quiz quiz) throws RemoteException;
  void getNextQuestion(int quizID, String email) throws RemoteException;
  void returnNextQuestion(int num) throws RemoteException;
}
