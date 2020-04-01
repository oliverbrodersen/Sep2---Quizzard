package shared.transferobjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Answer {

  private String answer;
  private Boolean correct;

  public Answer(String answer, Boolean correct) {
    this.answer = answer;
    this.correct = correct;
  }



  // Getters
  public String getAnswer() {
    return answer;
  }

  public Boolean getCorrect() {
    return correct;
  }

  // Setters
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public void setCorrect(Boolean correct) {
    this.correct = correct;
  }




}