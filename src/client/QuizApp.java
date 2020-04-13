package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.networking.RMIClient;
import javafx.application.Application;
import javafx.stage.Stage;
import shared.transferobjects.*;

import java.util.ArrayList;
import java.util.List;
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
    client.startClient();

    String userclass;
    System.out.println("Are you host(H) or player(P):");
    userclass = input.nextLine();
    System.out.println("\n");
    Lobby lobby;

    if (userclass.equalsIgnoreCase("H"))
    {
      String hostInput;
      System.out.println("You're now the host");
      System.out.println("Are you ready to create the lobby(Y|N)?:");
      hostInput = input.nextLine();
      if (hostInput.equalsIgnoreCase("Y"))
      {
        Host host = new Host("fakemail@mail.com",name,"Strongpassword",null);
        lobby = new Lobby(1, client.getQuiz() ,host,client.getParticipants());
        client.setLobby(lobby);

        System.out.println("Type Y when you want to start the quiz");
        hostInput = input.nextLine();
        if (hostInput.equalsIgnoreCase("y")){
          client.startQuiz();
        }
      }
    }

    else if (userclass.equalsIgnoreCase("P"))
    {
      lobby = client.getLobby();
      System.out.println("You're now a player");
      Participant participant = new Participant(name);
      client.newParticipant(participant);
      lobby.addParticipant(participant);
      for (int i = 0; i < client.getParticipants().size(); i++)
      {
        System.out.println(client.getParticipants().get(i).getName());
      }
    }

  }

}
