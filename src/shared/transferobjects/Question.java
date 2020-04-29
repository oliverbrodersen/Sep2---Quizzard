package shared.transferobjects;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable
{

  private String question;
  private List<Answer> answers;
  private int time, pointValue;
  private Integer noAnswers;


  public Question(String question, List<Answer> answersList, int time, int pointValue) {
    this.question = question;
    answers = new ArrayList<>();
    answers = answersList;
    this.time = time;
    this.pointValue = pointValue;
  }

  // Getters
  public String getQuestion() {
    return question;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public Integer getNoAnswers() {
    noAnswers = answers.size();
    return noAnswers;
  }

  public int getTime() {
    return time;
  }

  public double getPointValue() {
    return pointValue;
  }

  public Answer getAnswer(int i) {
    return answers.get(i);
  }


  // Setters
  public void setQuestion(String question) {
    this.question = question;
  }

  public void setAnswer(Answer answer, int i) {
    answers.set(i, answer);
  }

  public void setTime(int time) {
    this.time = time;
  }

  public void setPointValue(int pointValue) {
    this.pointValue = pointValue;
  }

  @Override public String toString()
  {
    String a = "";
    for (int i = 0; i < answers.size(); i++)
    {
      a += answers.get(i) + " ";
    }
    return " > " + question + "(" + time + "s)" + "\n" + a;
  }
}
