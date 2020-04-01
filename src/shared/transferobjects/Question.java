package shared.transferobjects;


import java.util.ArrayList;
import java.util.List;

public class Question {

  private String question;
  private List<Answer> answers;
  private int time, pointValue;


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



}
