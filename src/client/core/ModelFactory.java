package client.core;

import client.model.QuizConverter;
import client.model.QuizConverterManager;

public class ModelFactory
{

  private QuizConverter quizConverter;
  private static ModelFactory modelFactory;

  private ModelFactory() {
  }

  public QuizConverter getQuizConverter() {
    if(quizConverter == null) {
      quizConverter = new QuizConverterManager(ClientFactory.getInstance().getClient());
    }
    return quizConverter;
  }

  public static ModelFactory getInstance() {
    if (modelFactory == null) {
      modelFactory = new ModelFactory();
    }
    return modelFactory;
  }



}
