package shared.transferobjects;

import java.util.ArrayList;
import java.util.List;

public class Host {

  private String email, username, password;
  private List<Quiz> quizzes;


  public Host(String email, String username, String password, ArrayList<Quiz> quizzesList) {
    this.email = email;
    this.username = username;
    this.password = password;
    quizzes = quizzesList;
  }

  // getters
  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public List<Quiz> getQuizzes() {
    return quizzes;
  }

  public Quiz getQuiz(int i) {
    return quizzes.get(i);
  }


  // Setters
  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setQuiz(Quiz quiz, int i) {
    quizzes.set(i, quiz);
  }



}
