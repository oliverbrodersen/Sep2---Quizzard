package client.core;

import client.model.QuizConverter;
import client.model.QuizConverterManager;

public class ModelFactory
{

  private final ClientFactory cf;
  private QuizConverter quizConverter;

  public ModelFactory(ClientFactory cf) {
    this.cf = cf;
  }

  public QuizConverter getQuizConverter() {
    if(quizConverter == null) {
      quizConverter = new QuizConverterManager(cf.getClient());
    }
    return quizConverter;
  }

}
