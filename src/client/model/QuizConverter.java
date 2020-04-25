package client.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.List;

public interface QuizConverter extends Subject
{

  Lobby getLobby(int pin);

  List<Quiz> getQuizzes();
  List<Participant> getParticipants();
  Question getNextQuestion();

  String getUsername();
  int getUserID();
  String getPassword();
  UserClass getUser();
  int getPin();

  void setUsername(String username);
  void setPassword(String password);
  void setUser(String email);
  void setPin(int pin);

  void addNewParticipant(Participant participant);
  Quiz setQuiz(int quizID, String email);
  void sendAnswer();
  void exit();


    boolean verifyLogin(String username);
  boolean verifyPin(String pin);
  void addParticipant(String pin, Participant participant);
}
