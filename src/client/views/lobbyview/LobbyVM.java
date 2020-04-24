package client.views.lobbyview;

import client.model.QuizConverter;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LobbyVM {
    private QuizConverter quizConverter;

    public LobbyVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
    }

    public String getPin()
    {
        return quizConverter.getPin() + "";
    }
}
