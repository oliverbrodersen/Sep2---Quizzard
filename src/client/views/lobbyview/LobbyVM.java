package client.views.lobbyview;

import client.model.QuizConverter;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.UserID;

public class LobbyVM {
    private QuizConverter quizConverter;
    private StringProperty userTypeLabel;

    public LobbyVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
        userTypeLabel = new SimpleStringProperty();
    }

    public String getPin()
    {
        return quizConverter.getPin() + "";
    }

    public void startQuiz()
    {
        quizConverter.startQuiz();
    }

    public UserID getUserClass()
    {
        if (quizConverter.getUser() == null)
            return null;
        else
            return quizConverter.getUser().getUserID();
    }

    public void setUserClass(UserID userClass)
    {
        if (userClass == UserID.HOST)
            userTypeLabel.set("User class: Host");
        else if (userClass == UserID.MODERATOR)
            userTypeLabel.set("User class: Moderator");
        else
            userTypeLabel.set("User class: Participant");
    }

    public StringProperty userTypeLabelProperty()
    {
        return userTypeLabel;
    }
}
