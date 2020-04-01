package shared.transferobjects;

public class Participant {


  private String name;
  private int score, userID;

  public Participant(String name) {
    this.name = name;
    this.score = 0;
    this.userID = (int)Math.floor(Math.random()*9999);
  }


  // Getters
  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public int getUserID() {
    return userID;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }



}
