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
  private List<Lobby> lobbyList;
  private QuizData quizData;
  private UserData userData;
  private QuestionData questionData;
  private DatabaseConnection DBConn;
  private List<Quiz> quizzes;
//  private List<Question> questionsCreated = new ArrayList<>();

  public RMIServerImpl(QuizManager quizManager)
  {
    this.quizManager = quizManager;
    lobbyList = new ArrayList<>();
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

  private Lobby getLobbyByPin(int pin)
  {
    for (int i = 0; i < lobbyList.size(); i++)
    {
      if (lobbyList.get(i).getPin() == pin)
        return lobbyList.get(i);
    }
    return null;
  }

  @Override public ArrayList<Participant> getParticipants(int pin)
  {
    return (ArrayList<Participant>) getLobbyByPin(pin).getParticipants();
  }

  @Override public void newParticipant(int pin, Participant participant)
  {
    getLobbyByPin(pin).addParticipant(participant);
  }

  public void newClientCallBack(int pin, ClientCallback clientCallback)
      throws RemoteException
  {
    getLobbyByPin(pin).addClientCallback(clientCallback);
  }

  @Override public void addLobby(Lobby lobby, ClientCallback client)
      throws RemoteException
  {
    boolean check = false;
    int pin = -1;
    while (!check)
    {
      check = true;
      pin = (int) Math.floor(Math.random() * 899999) + 100000;
      if (lobbyList.size() > 0)
      {
        for (Lobby value : lobbyList)
        {
          if (value.getPin() == pin)
          {
            check = false;
            break;
          }
        }
      }
    }
    System.out.println("Pin for quiz: " + pin);
    client.updatePin(pin);
    lobby.setPin(pin);
    lobbyList.add(lobby);
  }

  @Override public Lobby getLobby(int pin) throws RemoteException
  {
    return getLobbyByPin(pin);
  }

  @Override public void submitAnswer(int pin, int answer) throws RemoteException
  {
    int questionNumber = getLobbyByPin(pin).getQuiz().getQuestionNumber();
    ArrayList<Integer> answers = (ArrayList<Integer>) getLobbyByPin(pin)
        .getAnswersForQuestion(questionNumber);

    int number = answers.get(answer);
    number++;

    //Vi har fjernet den der satte værdien fordi den gerne skulle opdatere automatisk
    //answers.get(quiz.getQuestionNumber()).set(answer, number);

    System.out.println(answers);
  }

  @Override public boolean verifyLogin(String username)
  {
    if (userData == null)
    {
      userData = new UserHandler(DBConn);
    }
    try
    {
      UserClass user = userData.retrieveUser(username);
      if (user.equals(null))
        return false;
    }
    catch (SQLException | NullPointerException throwables)
    {
      return false;
    }
    return true;
  }

  @Override public boolean verifyPin(String pin) throws RemoteException
  {
    return !(getLobbyByPin(Integer.parseInt(pin)) == null);
  }

  @Override
  public int getNextQuestionID() {
    int questionID = -1;
    if (questionData == null)
    {
      questionData = new QuestionHandler(DBConn);
    }
    try {
      questionID = questionData.getNextQuestionID();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return questionID;
  }

//  @Override
//  public void questionCreated(Question question) {
//    questionsCreated.add(question);
//
//  }

  @Override public Quiz getQuiz(int quizID, String email)
  {
    Quiz quiz = null;
    if (quizData == null)
    {
      quizData = new QuizHandler(DBConn);
    }
    try
    {
      quiz = quizData.readQuiz(quizID, email);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return quiz;
  }

  @Override public List<Quiz> getQuizzes(String email)
  {
    quizzes = new ArrayList<>();
    if (quizData == null)
    {
      quizData = new QuizHandler(DBConn);
    }
    try
    {
      quizzes = quizData.readQuizzes(email);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return quizzes;
  }

  @Override public UserClass getUser(String email) throws RemoteException
  {
    if (userData == null)
    {
      userData = new UserHandler(DBConn);
    }
    try
    {
      return userData.retrieveUser(email);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public void startQuiz(int pin, int quizID, String email)
      throws RemoteException
  {
    ArrayList<ClientCallback> clientList = (ArrayList<ClientCallback>) getLobbyByPin(
        pin).getClientList();
    System.out.println("Connected clients: " + clientList.size());
    getLobbyByPin(pin).getHostCallBack().getParticipants(pin);
    Quiz quiz = getQuiz(quizID, email);
    for (int i = 0; i < clientList.size(); i++)
    {
      clientList.get(i).getQuiz(quiz);
    }
  }

  @Override public void getNextQuestion(int pin) throws RemoteException
  {
    ArrayList<ClientCallback> clientList = (ArrayList<ClientCallback>) getLobbyByPin(
        pin).getClientList();
    int num = getLobbyByPin(pin).getQuiz().nextQuestion();
    System.out.println("Question number:" + num);
    for (int i = 0; i < clientList.size(); i++)
    {
      clientList.get(i).returnNextQuestion(pin, num);
    }
  }

  @Override public UserID getUserID()
  {
    return null;
  }

  @Override public void registerClient(int pin, ClientCallback client,
      UserID userID) throws RemoteException
  {
    PropertyChangeListener listener = null;
    PropertyChangeListener finalListener = listener;

    listener = evt -> {
      try
      {
        client.update((Lobby) evt.getNewValue());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
        quizManager.removeListener("Lobby", finalListener);
      }
    };
    quizManager.addListener("Lobby", listener);
    if (userID == UserID.PARTICIPANT){
      ArrayList<ClientCallback> clientList = (ArrayList<ClientCallback>) getLobbyByPin(
          pin).getClientList();
      getLobbyByPin(pin).getHostCallBack().getParticipants(pin);
      for (int i = 0; i < clientList.size(); i++)
      {
        clientList.get(i).getParticipants(pin);
      }
      getLobbyByPin(pin).addClientCallback(client);
    }
    else if (userID == UserID.HOST)
      getLobbyByPin(pin).setHostCallBack(client);
    System.out.println("Client successfully connected.");
  }

  @Override public void removeClient(ClientCallback client)
  {
  }

}
