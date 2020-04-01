package client;

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
    Scanner input = new Scanner(System.in);
    System.out.println("Enter name: ");
    String name = input.nextLine();

    RMIClient client = new RMIClient();
    client.startClient();

    // QUIZ
    Answer answer1 = new Answer("Blue", true);
    Answer answer2 = new Answer("Red", false);
    Answer answer3 = new Answer("Green", false);
    Answer answer4 = new Answer("Orange", false);

    List<Answer> answers = new ArrayList<>();
    Question question = new Question("What is the colour of the sky?", answers, 20, 100);

    Answer answer5 = new Answer("Blue", true);
    Answer answer6 = new Answer("Purple", false);
    Answer answer7 = new Answer("Magenta", false);
    Answer answer8 = new Answer("Orange", false);

    List<Answer> answers2 = new ArrayList<>();
    Question question2 = new Question("What is boy colours?", answers2, 20, 150);

    List<Question> questions = new ArrayList<>();

    Quiz quiz = new Quiz("Awesome Quiz!", "CoolBeans", questions);
    //


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
        ArrayList<Quiz> quizzes = new ArrayList<>();
        quizzes.add(quiz);
        Host host = new Host("fakemail@mail.com",name,"Strongpassword",quizzes);
        lobby = new Lobby(1,quiz,host,client.getParticipants());
      }
    }

    else if (userclass.equalsIgnoreCase("P"))
    {
      lobby = null;
      System.out.println("You're now a player");
      Participant participant = new Participant(name)
      client.newParticipant(participant);
      lobby.addParticipant(participant);
    }


  }
}
