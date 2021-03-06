package client.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.ArrayList;
import java.util.List;

public interface QuizConverter extends Subject
{

  Lobby getLobby(int pin);

  List<Quiz> getQuizzes();
  List<Participant> getParticipants();

  String getUsername();
  int getUserID();
  String getPassword();
  UserClass getUser();
  int getPin();
  Quiz getQuiz();
  ArrayList<Integer> getAnswers(int question);
  Participant getParticipant();
  Participant getWinner();

  void setUser(String email, String password);
  void setPin(int pin);

  Quiz setQuiz(int quizID, String email);
  void sendAnswer(int i);
  void exit();

  void nextQuestion();
  boolean verifyLogin(String username, String password);
  boolean verifyPin(String pin);
  void addParticipant(String pin, Participant participant);
  void startQuiz();
  void endQuestion();

    int getNextQuestionID();

    void questionCreated(Question question);

    void createQuiz(String name, String subject, String difficulty, ArrayList<Question> questions);

    void deleteQuiz(Quiz quiz);
  void kickPlayer(Participant participant);
  void updateScore(int score);
}
