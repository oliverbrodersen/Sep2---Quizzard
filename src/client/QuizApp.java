package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.networking.RMIClient;
import javafx.application.Application;
import javafx.stage.Stage;
import server.networking.RMIServerImpl;
import shared.transferobjects.*;

import java.util.Scanner;

public class QuizApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {

  //  ClientFactory cf = new ClientFactory();
  //  ModelFactory mf = new ModelFactory(cf);
  //  ViewModelFactory vmf = new ViewModelFactory(mf);
  //  ViewHandler vh = new ViewHandler(vmf);
  //  vh.start();


    Scanner input = new Scanner(System.in);
    System.out.println("Enter name: ");
    String name = input.nextLine();

    RMIClient client = new RMIClient();

    String userclass;
    System.out.println("Are you host(H) or player(P):");
    userclass = input.nextLine();
    System.out.println("\n");
    Lobby lobby;

    if (userclass.equalsIgnoreCase("H"))
    {
      //Pin til host er ligemeget
      client.startClient(-1, UserID.HOST);
      String hostInput;
      System.out.println("You're now the host");
      String email = null;
      Quiz quiz = null;
      int quizIDint = -1;
      while (quiz == null) {
        System.out.println("Please enter email:");
        email = input.nextLine();
        System.out.println("Please enter quizID:");
        quizIDint = input.nextInt();
        input.nextLine();
        quiz = client.getQuiz(quizIDint, email);
      }
      System.out.println("Are you ready to create the lobby(Y|N)?:");
      hostInput = input.nextLine();
      if (hostInput.equalsIgnoreCase("Y"))
      {
        Host host = new Host(email,name,"Strongpassword",null);
        lobby = new Lobby(quiz ,host);
        lobby.setHostCallBack(client);
        client.addLobby(lobby, client);

        System.out.println("Type Y when you want to start the quiz");
        hostInput = input.nextLine();
        if (hostInput.equalsIgnoreCase("y")){
          //Det er ikke sikkert at metoden under kan få fat i pin koden, da den først
          //bliver oprettet når lobbyen bliver uploaded til serveren
          System.out.println(client.getPin());
          client.startQuiz(client.getPin(), quizIDint, email);

          while(true){
            System.out.println("Type 'Y' for next question");
            hostInput = input.nextLine();
            if (hostInput.equalsIgnoreCase("y")){
              client.getNextQuestion(client.getPin());
            }
          }
        }
      }
    }

    else if (userclass.equalsIgnoreCase("P"))
    {
      System.out.println("You're now a player");
      System.out.println("Enter pin:");
      String pinFromUser = input.nextLine();
      int pin = Integer.parseInt(pinFromUser);

      client.startClient(pin, UserID.PARTICIPANT);
      lobby = client.getLobby(pin);
      Participant participant = new Participant(name);
      client.newParticipant(pin, participant);
      lobby.addParticipant(participant);
      for (int i = 0; i < client.getParticipants(pin).size(); i++)
      {
        System.out.println(client.getParticipants(pin).get(i).getName());
      }


    }

  }

}
