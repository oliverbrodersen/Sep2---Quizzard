package client.networking;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.List;

public interface Client extends Subject
{
  void startClient(int pin, UserID userID);
  Quiz getQuiz(int quizID, String email);
  UserID getUserClass();

  Lobby getLobby(int pin);
  List<Participant> getParticipants(int pin);
  List<Quiz> getQuizzes();
  Lobby getLobby() ;
  List<Participant> getParticipants();
  String getUsername();
  int getUserID();
  String getPassword();

  void sendAnswer(int pin, int answer);
  void startQuiz(int pin, int quizID, String email);


    boolean verifyLogin(String username);
}
