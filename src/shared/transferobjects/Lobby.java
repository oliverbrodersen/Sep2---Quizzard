package shared.transferobjects;

import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

  private String id;
  private Quiz quiz;
  private Host host;
  private String title;
  private List<Participant> participants;

  public Lobby(String id, Quiz quiz, Host host, ArrayList<Participant> participantsList) {
    this.id = id;
    this.quiz = quiz;
    this.host = host;
    title = quiz.getTitle();
    participants = participantsList;
  }


  // getters
  public String getId() {
    return id;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public Host getHost() {
    return host;
  }

  public String getTitle() {
    return title;
  }

  public List<Participant> getParticipants() {
    return participants;
  }

  public Participant getParticipant(int i) {
    return participants.get(i);
  }


  // Setters
  public void setId(String id) {
    this.id = id;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public void setHost(Host host) {
    this.host = host;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setParticipant(Participant participant, int i) {
    participants.set(i, participant);
  }


  // Logic
  public void removeParticipant(int i) {
    participants.remove(i);
  }


}
