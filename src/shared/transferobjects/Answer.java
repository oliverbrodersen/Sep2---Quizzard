package shared.transferobjects;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Answer implements Serializable
{

  private String answer;
  private Boolean correct;
  private String answerID;

  public Answer(String answer, Boolean correct, String answerID) {
    this.answer = answer;
    this.correct = correct;
    this.answerID = answerID;
  }

  // Getters
  public String getAnswer() {
    return answer;
  }

  public Boolean getCorrect() {
    return correct;
  }

  public String getAnswerID() {
    return answerID;
  }

  // Setters
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public void setCorrect(Boolean correct) {
    this.correct = correct;
  }

  public String toString(){
    if (correct)
      return "\u001B[32m" + "[" + answer + "]" + "\u001B[0m" + "[" + answerID + "]";
    else
      return "[" + answer + "]" + "[" + answerID + "]";
  }
}
