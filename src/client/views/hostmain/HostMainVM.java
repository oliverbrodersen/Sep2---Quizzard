package client.views.hostmain;

import client.model.QuizConverter;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.networking.ClientCallback;
import shared.transferobjects.Lobby;
import shared.transferobjects.Quiz;
import shared.transferobjects.UserClass;
import shared.transferobjects.UserID;

import java.rmi.RemoteException;
import java.util.List;

public class HostMainVM {

    private ObservableList<Quiz> quizzes;
    private QuizConverter quizConverter;
    private StringProperty nameText, emailText, userClassText;
    private BooleanProperty createButton, editButton, hostButton;
    private boolean isModerator;

    public HostMainVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
        nameText = new SimpleStringProperty();
        emailText = new SimpleStringProperty();
        userClassText = new SimpleStringProperty();
        createButton = new SimpleBooleanProperty();
        editButton = new SimpleBooleanProperty();
        hostButton = new SimpleBooleanProperty();
        if (quizConverter.getUser().getUserID() == UserID.MODERATOR)
            isModerator = true;
        else if (quizConverter.getUser().getUserID() == UserID.HOST)
            isModerator = false;

        if (!isModerator)
        {
            createButton.setValue(true);
            editButton.setValue(true);
            hostButton.setValue(true);
        }
    }

    public void exit() {
        quizConverter.exit();
    }

    public void loadQuizzes() {
        UserClass host = quizConverter.getUser();
        emailText.set(host.getEmail());
        nameText.set(host.getUsername());
        if (host.getUserID() == UserID.MODERATOR)
            userClassText.set("Moderator");
        else
            userClassText.set("Host");

        List<Quiz> quizList = quizConverter.getQuizzes();
        quizzes = FXCollections.observableArrayList(quizList);
    }

    public ObservableList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void host(Quiz quiz) throws RemoteException
    {
        UserClass user = quizConverter.getUser();
        Lobby lobby = new Lobby(quiz , user);
        ClientCallback client = (ClientCallback) user.getClient();
        lobby.setHostCallBack(client);
        client.addLobby(lobby,client);
    }

    public StringProperty emailTextProperty()
    {
        return emailText;
    }

    public StringProperty nameTextProperty()
    {
        return nameText;
    }

    public StringProperty userClassTextProperty()
    {
        return userClassText;
    }


    public void deleteQuiz(Quiz quiz) {
        quizConverter.deleteQuiz(quiz);
    }

    public Property<Boolean> createButtonProperty() {
        return createButton;
    }

    public Property<Boolean> editButtonProperty() {
        return editButton;
    }

    public Property<Boolean> hostButtonProperty() {
        return hostButton;
    }
}

