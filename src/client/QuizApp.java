package client;

import client.networking.RMIClient;
import javafx.application.Application;
import javafx.stage.Stage;
import shared.transferobjects.Answer;
import shared.transferobjects.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    RMIClient client = new RMIClient();
    client.startClient();

    // QUIZ
    Answer answer1 = new Answer("Blue", true);
    Answer answer2 = new Answer("Red", false);
    Answer answer3 = new Answer("Green", false);
    Answer answer4 = new Answer("Orange", false);

    List<Answer> answers = new ArrayList<>();
    Question question = new Question("What is the colour of the sky?", answers, 20, 100);
     //
    String userclass;
    System.out.println("Are you host(H) or player(P):");
    Scanner input = new Scanner(System.in);
    userclass = input.nextLine();
    System.out.println("\n");


    if (userclass.equalsIgnoreCase("H"))
    {
      System.out.println("You're now the host");

    }
    else if (userclass.equalsIgnoreCase("P"))
    {
      System.out.println("You're now a player");
    }


  }
}
