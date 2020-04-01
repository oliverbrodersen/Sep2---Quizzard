package shared.transferobjects;

import java.util.ArrayList;
import java.util.List;

public class Host implements UserClass {

  private String email, username, password;
  private List<Quiz> quizzes;
  private UserID userID;


  public Host(String email, String username, String password, ArrayList<Quiz> quizzesList) {
    this.email = email;
    this.username = username;
    this.password = password;
    quizzes = quizzesList;
    userID = UserID.HOST;
  }

  // getters
  @Override public String getEmail() {
    return email;
  }

  @Override public String getUsername() {
    return username;
  }

  @Override public String getPassword() {
    return password;
  }

  @Override public UserID getUserID() {
    return userID;
  }

  public List<Quiz> getQuizzes() {
    return quizzes;
  }

  public Quiz getQuiz(int i) {
    return quizzes.get(i);
  }

  // Setters
  @Override public void setEmail(String email) {
    this.email = email;
  }

  @Override public void setUsername(String username) {
    this.username = username;
  }

  @Override public void setPassword(String password) {
    this.password = password;
  }

  public void setQuiz(Quiz quiz, int i) {
    quizzes.set(i, quiz);
  }



}
