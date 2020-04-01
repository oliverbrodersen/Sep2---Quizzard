package shared.transferobjects;


import java.util.ArrayList;
import java.util.List;

public class Lobby {

  private int id;
  private Quiz quiz;
  private Host host;
  private String title;
  private List<Participant> participants;

  public Lobby(int id, Quiz quiz, Host host, ArrayList<Participant> participantsList) {
    this.id = id;
    this.quiz = quiz;
    this.host = host;
    title = quiz.getTitle();
    participants = participantsList;
  }


  // getters
  public int getId() {
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


  public Question getNextQuestion(int i) {
    return quiz.getQuestion(i);
  }


  // Setters
  public void setId(int id) {
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

  public void addParticipant(Participant participant)
  {
    this.participants.add(participant);
  }


  // Logic
  public void removeParticipant(Participant participant) {
    participants.remove(participant);
  }


}
