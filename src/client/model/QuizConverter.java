package client.model;

import shared.transferobjects.Lobby;
import shared.transferobjects.Participant;
import shared.transferobjects.Question;
import shared.util.Subject;

import java.util.List;

public interface QuizConverter extends Subject
{

  Lobby getLobby();

  List<Participant> getParticipants();
  Question getNextQuestion();

  String getUsername();
  int getUserID();
  String getPassword();

  void setUsername(String username);
  void setPassword(String password);

  void addNewParticipant(Participant participant);

  void sendAnswer();
  void exit();


}
