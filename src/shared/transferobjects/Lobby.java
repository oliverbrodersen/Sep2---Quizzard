package shared.transferobjects;


import shared.networking.ClientCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lobby implements Serializable
{

  private int pin;
  private Quiz quiz;
  private Host hostData;
  private String title;
  private List<Participant> participants;
  private ArrayList<ArrayList<Integer>> answers;
  private List<ClientCallback> clientList;
  private ClientCallback hostCallBack;

  public Lobby(Quiz quiz, Host host) {
    this.quiz = quiz;
    this.hostData = host;
    title = quiz.getTitle();
    participants = new ArrayList<>();
    clientList = new ArrayList<>();

    answers = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < quiz.getQuestions().size(); i++) {
      ArrayList<Integer> answersInner = new ArrayList<>();
      for (int j = 0; j < quiz.getQuestion(i).getAnswers().size(); j++) {
        answersInner.add(0);
      }
      answers.add(answersInner);
    }
  }


  // getters
  public int getPin() {
    return pin;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public Host getHostData() {
    return hostData;
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

  public List<Integer> getAnswersForQuestion(int i){
    return answers.get(i);
  }

  public List<ClientCallback> getClientList()
  {
    return clientList;
  }


  // Setters
  public void setPin(int pin) {
    this.pin = pin;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public void setHostData(Host hostData) {
    this.hostData = hostData;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void addParticipant(Participant participant)
  {
    this.participants.add(participant);
  }

  public void addClientCallback(ClientCallback clientCallback){
    clientList.add(clientCallback);
  }

  public void setHostCallBack(ClientCallback hostCallBack)
  {
    this.hostCallBack = hostCallBack;
  }
  // Logic
  public void removeParticipant(Participant participant)
  {
    participants.remove(participant);
  }
}
