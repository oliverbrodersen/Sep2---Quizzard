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
  private boolean isOver = false;

  public Lobby(Quiz quiz, UserClass host) {
    this.quiz = quiz;
    this.hostData = (Host) host;
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

  public boolean isOver()
  {
    return isOver;
  }

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

  public Participant getParticipant(String name){
    for (int i = 0; i < participants.size(); i++)
    {
      if (participants.get(i).getName().equals(name))
        return participants.get(i);
    }
    return null;
  }

  public Question getNextQuestion(int i) {
    return quiz.getQuestion(i);
  }

  public List<Integer> getAnswersForQuestion(int i){
    return answers.get(i);
  }
  public void setAnswers(int i, ArrayList<Integer> a){
    answers.set(i, a);
  }

  public List<ClientCallback> getClientList()
  {
    return clientList;
  }

  public ClientCallback getHostCallBack()
  {
    return hostCallBack;
  }

  // Setters

  public void setOver(boolean over)
  {
    isOver = over;
  }

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
    for (int i = 0; i < participants.size() ; i++)
    {
      if(participants.get(i).getName().equals(participant.getName())){
        participants.remove(i);
        break;
      }
    }
  }

  public void submitAnswer(int answer){
    int currentQ = quiz.getQuestionNumber();
    answers.get(currentQ).set(answer, answers.get(currentQ).get(answer) + 1);
  }

  public void removeClient(ClientCallback rmiClient)
  {
    clientList.remove(rmiClient);
  }
}
