package client.views.lobbyview;

import client.model.QuizConverter;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import shared.transferobjects.UserID;

public class LobbyVM {
    private QuizConverter quizConverter;
    private StringProperty userTypeLabel, pinLabel;

    public LobbyVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
        userTypeLabel = new SimpleStringProperty();
        pinLabel = new SimpleStringProperty();
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
    public StringProperty pinLabelProperty()
    {
        return pinLabel;
    }

    public void setup(Button startButton)
    {
        String pin = getPin();
        String[] pinArray = pin.split("");
        String pinDisplay = pinArray[0] + pinArray[1] + " " + pinArray[2] + pinArray[3] + " " + pinArray[4] + pinArray[5];
        pinLabel.set("Pin: " + pinDisplay);
        if (getUserClass() == null)
            startButton.setVisible(false);
        setUserClass(getUserClass());
    }
}
