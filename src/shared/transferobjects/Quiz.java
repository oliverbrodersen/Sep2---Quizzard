package shared.transferobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable
{

  private String title, subject;
  private int questionNumber;
  private List<Question> questions;
  private int length, quizId;

  public Quiz(String title, String subject, List<Question> questionsList, int quizId) {
    this.title = title;
    this.subject = subject;
    questions = questionsList;
    questionNumber = -1;
    this.quizId = quizId;
  }

  // Getters
  public String getTitle() {
    return title;
  }

  public int getQuizId()
  {
    return quizId;
  }

  public String getSubject() {
    return subject;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public int getLength() {
    return questions.size();
  }

  public Question getQuestion (int i) {
    return questions.get(i);
  }

  public int getQuestionNumber()
  {
    return questionNumber;
  }

  public int nextQuestion(){
    if (questionNumber < questions.size() - 1)
      questionNumber++;
    else
      questionNumber = -1;
    return questionNumber;
  }

  // Setters
  public void setTitle(String title) {
    this.title = title;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setQuestion(Question question, int i) {
    questions.set(i, question);
  }

  @Override public String toString()
  {
    String a = "Quiz: " + title;
    for (int i = 0; i < questions.size(); i++)
    {
      a += "\n" + "Question " + (i + 1) + "\n" + questions.get(i);
    }
    return a;
  }
}
