package client.networking;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.List;

public interface Client extends Subject
{
  Quiz getQuiz(int quizID, String email);
  UserID getUserClass();

  List<Quiz> getQuizzes();
  Lobby getLobby();
  List<Participant> getParticipants();
  String getUsername();
  int getUserID();
  String getPassword();

  void sendAnswer(int answer);
  void startQuiz(int quizID, String email);
  void startClient();


    boolean verifyLogin(String username);
}
