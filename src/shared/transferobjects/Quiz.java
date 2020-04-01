package shared.transferobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable
{

  private String title, subject;
  private List<Question> questions;

  public Quiz(String title, String subject, List<Question> questionsList) {
    this.title = title;
    this.subject = subject;
    questions = new ArrayList<>();
    questions = questionsList;
  }


  // Getters
  public String getTitle() {
    return title;
  }

  public String getSubject() {
    return subject;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public Question getQuestion (int i) {
    return questions.get(i);
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
    String a = "";
    for (int i = 0; i < questions.size(); i++)
    {
      a += "Question " + i + 1 + "\n" + questions.get(i);
    }
    return a;
  }
}
