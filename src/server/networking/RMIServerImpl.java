package server.networking;

import server.model.QuizManager;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer
{
  public QuizManager quizManager;
  public List<Participant> participantList;
  public List<ClientCallback> clientList;
  public Lobby lobby;

  public RMIServerImpl(QuizManager quizManager)
  {
    this.quizManager = quizManager;
    participantList = new ArrayList<>();
    clientList = new ArrayList<>();
    lobby = null;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("QuizServer", this);
    System.out.println("Server started.");
  }

  @Override public ArrayList<Participant> getParticipants()
  {
    return (ArrayList<Participant>)participantList;
  }

  @Override public void newParticipant(Participant participant)
  {
    participantList.add(participant);
  }

  @Override public void setLobby(Lobby lobby) throws RemoteException
  {
   this.lobby = lobby;
  }

  @Override public Lobby getLobby() throws RemoteException
  {
    return this.lobby;
  }

  @Override public Quiz getQuiz()
  {
    // QUIZ
    Answer answer1 = new Answer("Blue", true);
    Answer answer2 = new Answer("Red", false);
    Answer answer3 = new Answer("Green", false);
    Answer answer4 = new Answer("Orange", false);

    List<Answer> answers = new ArrayList<>();
    answers.add(answer1);
    answers.add(answer2);
    answers.add(answer3);
    answers.add(answer4);

    Question question = new Question("What is the colour of the sky?", answers, 20, 100);

    Answer answer5 = new Answer("Blue", true);
    Answer answer6 = new Answer("Purple", false);
    Answer answer7 = new Answer("Magenta", false);
    Answer answer8 = new Answer("Orange", false);

    List<Answer> answers2 = new ArrayList<>();
    answers2.add(answer5);
    answers2.add(answer6);
    answers2.add(answer7);
    answers2.add(answer8);

    Question question2 = new Question("What is boy colours?", answers2, 20, 150);

    List<Question> questions = new ArrayList<>();
    questions.add(question);
    questions.add(question2);

    Quiz quiz = new Quiz("Awesome Quiz!", "CoolBeans", questions);
    return quiz;
  }

  @Override public void startQuiz() throws RemoteException
  {
    System.out.println("Connected clients: " + clientList.size());
    for (int i = 0; i < clientList.size(); i++)
    {
      clientList.get(i).getQuiz(getQuiz());
    }
  }

  @Override public int getNextQuestion()
  {
    return -1;
  }

  @Override public UserID getUserID() {
    return null;
  }

  @Override public void registerClient(ClientCallback client)
  {
    PropertyChangeListener listener = null;
    PropertyChangeListener finalListener = listener;

    listener = evt -> {
      try {
        client.update((Lobby) evt.getNewValue());
      } catch (RemoteException e) {
        e.printStackTrace();
        quizManager.removeListener("Lobby", finalListener);
      }
    };
    quizManager.addListener("Lobby", listener);
    clientList.add(client);
    System.out.println("Client successfully connected.");
    try
    {
      client.connected();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void removeClient(ClientCallback client)
  {

  }
}
