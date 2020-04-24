package server.networking;

import server.DAO.*;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer
{
  private QuizManager quizManager;
  private List<Participant> participantList;
  private List<ClientCallback> clientList;
  private ClientCallback host;
  private QuizData quizData;
  private UserData userData;
  private Lobby lobby;
  private DatabaseConnection DBConn;
  private Quiz quiz;
  private List<Quiz> quizzes;
  private ArrayList<ArrayList<Integer>> answers;

  public RMIServerImpl(QuizManager quizManager)
  {
    this.quizManager = quizManager;
    participantList = new ArrayList<>();
    clientList = new ArrayList<>();
    lobby = null;
    DBConn = new DatabaseConnection();
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("QuizServer", this);
    DBConn.startDB();
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

  @Override public void setLobby(Lobby lobby)
  {
   this.lobby = lobby;
  }

  @Override public Lobby getLobby()
  {
    return this.lobby;
  }

  @Override
  public void submitAnswer(int answer) {
    int number = answers.get(quiz.getQuestionNumber()).get(answer);
    number++;
    answers.get(quiz.getQuestionNumber()).set(answer, number);
    System.out.println(answers.get(quiz.getQuestionNumber()));
  }

  @Override
  public boolean verifyLogin(String username){
    if (userData == null)
    {
      userData = new UserHandler(DBConn);
    }
    try {
      UserClass user = userData.retrieveUser(username);
      if (user.equals(null))
        return false;
    } catch (SQLException | NullPointerException throwables) {
      return false;
    }
    return true;
  }

  @Override public Quiz getQuiz(int quizID, String email)
  {
    if (quiz == null) {
      if (quizData == null) {
        quizData = new QuizHandler(DBConn);
      }
      try {
        quiz = quizData.readQuiz(quizID, email);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      answers = new ArrayList<ArrayList<Integer>>();
      for (int i = 0; i < quiz.getQuestions().size(); i++) {
        ArrayList<Integer> answersInner = new ArrayList<>();
        for (int j = 0; j < quiz.getQuestion(i).getAnswers().size(); j++) {
          answersInner.add(0);
        }
        answers.add(answersInner);
      }
    }
    return quiz;
  }

  @Override
  public List<Quiz> getQuizzes() {
    quizzes = new ArrayList<>();
    if (quizData == null)
    {
      quizData = new QuizHandler(DBConn);
    }
    try {
      quizzes = quizData.readQuizzes("Host@Host.com");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return quizzes;
  }

  @Override public void startQuiz(int quizID, String email)
  {
    System.out.println("Connected clients: " + clientList.size());
    for (int i = 0; i < clientList.size(); i++)
    {
      try {
        clientList.get(i).getQuiz(getQuiz(quizID, email));
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  @Override public void getNextQuestion()
  {
    int num = quiz.nextQuestion();
    System.out.println("Question number:" + num);
    for (int i = 0; i < clientList.size(); i++)
    {
      try {
        clientList.get(i).returnNextQuestion(num);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  @Override public UserID getUserID() {
    return null;
  }

  @Override public void registerClient(ClientCallback client, UserID userID)
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
    if (userID == UserID.PARTICIPANT)
      clientList.add(client);
    else if (userID == UserID.HOST)
      host = client;
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
