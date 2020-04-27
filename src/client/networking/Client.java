package client.networking;

import shared.transferobjects.*;
import shared.util.Subject;

import java.rmi.RemoteException;
import java.util.List;

public interface Client extends Subject
{
  void startClient();
  Quiz getQuiz(int quizID, String email);

  Lobby getLobby(int pin);
  List<Participant> getParticipants(int pin);
  List<Quiz> getQuizzes(String email);
  UserClass getUser(String email);
  List<Participant> getParticipants();
  void setPin(int pin);
  int getPin();
  void newParticipant(int pin, Participant participant);
  void sendAnswer(int pin, int answer);
  void startQuiz(int pin, int quizID, String email);


    boolean verifyLogin(String username);
  boolean verifyPin(String pin);
}
