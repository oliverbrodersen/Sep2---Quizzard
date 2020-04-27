package client.views.lobbyview;

import client.model.QuizConverter;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import shared.transferobjects.Participant;
import shared.transferobjects.Quiz;
import shared.transferobjects.UserID;

import java.beans.PropertyChangeListener;
import java.util.List;

public class LobbyVM {
    private ObservableList<Participant> participants;
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

    public ObservableList<Participant> getParticipants()
    {
        List<Participant> participantsList = quizConverter.getParticipants();
        participants = FXCollections.observableArrayList(participantsList);
        return participants;
    }
    public ObservableList<Participant> setParticipants(List<Participant> participantsList){
        participants = FXCollections.observableArrayList(participantsList);
        return participants;
    }

    public void addListener(String propertyChange, PropertyChangeListener propertyChange1)
    {
        quizConverter.addListener(propertyChange, propertyChange1);
    }
}
