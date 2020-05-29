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

  @Override public void endQuestion(int pin) throws RemoteException
  {
    ArrayList<ClientCallback> clients = (ArrayList<ClientCallback>) getLobbyByPin(
        pin).getClientList();
    for (int i = 0; i < clients.size(); i++)
    {
      clients.get(i).endQuestionCall();
    }
  }

  @Override public void submitAnswer(int pin, int answer) throws RemoteException
  {
    int questionNumber = getLobbyByPin(pin).getQuiz().getQuestionNumber();
    ArrayList<Integer> answers = (ArrayList<Integer>) getLobbyByPin(pin)
        .getAnswersForQuestion(questionNumber);

    int number = answers.get(answer);
    number++;
    answers.set(answer, answers.get(answer) + 1);
    getLobbyByPin(pin).setAnswers(questionNumber, answers);
    System.out.println(answers);
  }

  @Override public boolean verifyLogin(String username, String password)
  {
    if (userData == null)
    {
      userData = new UserHandler(DBConn);
    }
    try
    {
      UserClass user = userData.retrieveUser(username, password);
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

  @Override public int getNextQuestionID()
  {
    int questionID = -1;
    if (questionData == null)
    {
      questionData = new QuestionHandler(DBConn);
    }
    try
    {
      questionID = questionData.getNextQuestionID();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return questionID;
  }

  @Override public void kickPlayer(Participant participant, int pinFromServer) throws RemoteException
  {
    getLobbyByPin(pinFromServer).removeParticipant(participant);

    participant.getClientCallback().kickPlayer();

    getLobbyByPin(pinFromServer).removeClient(participant.getClientCallback());
    ArrayList<ClientCallback> clientList = (ArrayList<ClientCallback>) getLobbyByPin(
        pinFromServer).getClientList();

    getLobbyByPin(pinFromServer).getHostCallBack()
        .getParticipants(pinFromServer);

    for (int i = 0; i < clientList.size(); i++)
    {
      clientList.get(i).getParticipants(pinFromServer);
    }
  }

  //  @Override
  //  public void questionCreated(Question question) {
  //    questionsCreated.add(question);
  //
  //  }
  @Override public ArrayList<Integer> getAnswers(int pin, int question) throws RemoteException
  {
    return (ArrayList<Integer>)getLobbyByPin(pin).getAnswersForQuestion(question);
  }

  @Override public void createQuiz(String name, String subject,
      String difficulty, ArrayList<Question> questions, String email)
  {
    if (quizData == null)
    {
      quizData = new QuizHandler(DBConn);
    }
    Quiz quiz = null;
    try {
      quiz = new Quiz(name, subject, questions, quizData.getNextQuizID() + 1);
      quizData.storeQuiz(quiz, difficulty, email.toLowerCase());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteQuiz(Quiz quiz) {
    if(quizData == null)
    {
      quizData = new QuizHandler(DBConn);
    }
    try {
      quizData.deleteQuiz(quiz);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public void updateScore(int pin, Participant participant) throws RemoteException
  {
    getLobbyByPin(pin).getParticipant(participant.getName()).setScore(participant.getScore());
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

  @Override public UserClass getUser(String email, String password) throws RemoteException
  {
    if (userData == null)
    {
      userData = new UserHandler(DBConn);
    }
    try
    {
      return userData.retrieveUser(email, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public Participant getWinner(int pinFromServer)
      throws RemoteException
  {
    ArrayList<Participant> participants = (ArrayList<Participant>) getLobbyByPin(pinFromServer).getParticipants();
    Participant winner = participants.get(0);
    for (int i = 0; i < participants.size(); i++)
    {
      if (participants.get(i).getScore() > winner.getScore())
        winner = participants.get(i);
    }
    return winner;
  }

  @Override public void startQuiz(int pin)
      throws RemoteException
  {
    ArrayList<ClientCallback> clientList = (ArrayList<ClientCallback>) getLobbyByPin(
        pin).getClientList();
    System.out.println("Connected clients: " + clientList.size());
    ClientCallback host = getLobbyByPin(pin).getHostCallBack();
    host.getParticipants(pin);
    getLobbyByPin(pin).getQuiz().nextQuestion();
    Quiz quiz = getLobbyByPin(pin).getQuiz();
    host.getQuiz(quiz);
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
    if (num != -1)
    {
      getLobbyByPin(pin).getHostCallBack().returnNextQuestion();
      for (int i = 0; i < clientList.size(); i++)
      {
        clientList.get(i).returnNextQuestion();
      }

      if (getLobbyByPin(pin).isOver()){
        System.out.println("Quiz: " + getLobbyByPin(pin).getQuiz().getTitle() + " has now ended.");
        lobbyList.remove(getLobbyByPin(pin));
      }
    }
    else
    {
      getLobbyByPin(pin).setOver(true);
      getLobbyByPin(pin).getHostCallBack().endQuiz();
      for (int i = 0; i < clientList.size(); i++)
      {
        clientList.get(i).endQuiz();
      }
    }
  }

  @Override public UserID getUserID()
  {
    return null;
  }

  PropertyChangeListener listener = null;
  PropertyChangeListener finalListener = listener;

  @Override public void registerClient(int pin, ClientCallback client,
      UserID userID) throws RemoteException
  {

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
    if (userID == UserID.PARTICIPANT)
    {
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

}
