package client.networking;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.List;

public interface Client extends Subject
{
  Quiz getQuiz();
  Question getNextQuestion();
  UserID getUserClass();

  Lobby getLobby();
  List<Participant> getParticipants();
  String getUsername();
  int getUserID();
  String getPassword();

  void sendAnswer(Answer answer);

  void startClient();


}
