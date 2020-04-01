package client.networking;

import shared.transferobjects.Quiz;
import shared.transferobjects.Question;
import shared.transferobjects.Answer;
import shared.util.Subject;

public interface Client extends Subject
{
  Quiz getQuiz();
  Question getNextQuestion();
  void sendAnswer(Answer answer);

  void startClient();
}
