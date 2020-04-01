package server.model;

import shared.util.Subject;

import java.beans.PropertyChangeListener;

public interface QuizManager extends Subject
{
  void addListener(String eventName, PropertyChangeListener listener);

  void removeListener(String eventName, PropertyChangeListener listener);
}
