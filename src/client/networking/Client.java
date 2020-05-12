package client.networking;

import shared.transferobjects.*;
import shared.util.Subject;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface Client extends Subject
{
  void startClient();
  Quiz getQuiz(int quizID, String email);
  UserID getUserClass();
  Lobby getLobby(int pin);
  List<Participant> getParticipants(int pin);
  List<Quiz> getQuizzes(String email);
  Quiz getQuiz();
  UserClass getUser(String email);
  Lobby getLobby() ;
  List<Participant> getParticipants();
  String getUsername();
  int getUserID();
  String getPassword();
  int getPin();
  void getNextQuestion();
  Participant getParticipant();
  ArrayList<Integer> getAnswers(int question);
  Participant getWinner();

  void setPin(int pin);
  void newParticipant(int pin, Participant participant);
  void removeParticipant(int pin, Participant participant);
  void sendAnswer(int answer);
  void startQuiz(int pin, int quizID, String email);
  void endQuestion();

  boolean verifyLogin(String username);
  boolean verifyPin(String pin);

    int getNextQuestionID();

    void questionCreated(Question question);
  void kickPlayer(Participant participant);

    void createQuiz(String name, String subject, String difficulty, ArrayList<Question> questions, String email);

    void deleteQuiz(Quiz quiz);
  void updateScore(int score);
}
