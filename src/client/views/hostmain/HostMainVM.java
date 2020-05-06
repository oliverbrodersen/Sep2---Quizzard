package client.views.hostmain;

import client.model.QuizConverter;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    public HostMainVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
        nameText = new SimpleStringProperty();
        emailText = new SimpleStringProperty();
        userClassText = new SimpleStringProperty();
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
}

