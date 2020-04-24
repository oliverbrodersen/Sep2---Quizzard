package client.model;

import shared.transferobjects.Lobby;
import shared.transferobjects.Participant;
import shared.transferobjects.Question;
import shared.transferobjects.Quiz;
import shared.util.Subject;

import java.util.List;

public interface QuizConverter extends Subject
{

  Lobby getLobby();

  List<Quiz> getQuizzes();
  List<Participant> getParticipants();
  Question getNextQuestion();

  String getUsername();
  int getUserID();
  String getPassword();

  void setUsername(String username);
  void setPassword(String password);

  void addNewParticipant(Participant participant);
  Quiz setQuiz(int quizID, String email);
  void sendAnswer();
  void exit();


    boolean verifyLogin(String username);
}
