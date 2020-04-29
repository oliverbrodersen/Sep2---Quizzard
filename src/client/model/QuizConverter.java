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
  Quiz getQuiz();

  void setUsername(String username);
  void setPassword(String password);
  void setUser(String email);
  void setPin(int pin);

  Quiz setQuiz(int quizID, String email);
  void sendAnswer(int i);
  void exit();

  void nextQuestion();
  boolean verifyLogin(String username);
  boolean verifyPin(String pin);
  void addParticipant(String pin, Participant participant);
  void startQuiz();
  void endQuestion();

    int getNextQuestionID();

    void questionCreated(Question question);
}
