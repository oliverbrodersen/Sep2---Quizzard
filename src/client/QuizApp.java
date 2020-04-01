package client;

import client.networking.RMIClient;
import javafx.application.Application;
import javafx.stage.Stage;

public class QuizApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    RMIClient client = new RMIClient();
    client.startClient();
  }
}
