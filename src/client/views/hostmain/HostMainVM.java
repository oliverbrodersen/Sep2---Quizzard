package client.views.hostmain;

import client.model.QuizConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.networking.ClientCallback;
import shared.transferobjects.Lobby;
import shared.transferobjects.Quiz;
import shared.transferobjects.UserClass;

import java.rmi.RemoteException;
import java.util.List;

public class HostMainVM {

    private ObservableList<Quiz> quizzes;
    private QuizConverter quizConverter;

    public HostMainVM(QuizConverter quizConverter) {
        this.quizConverter = quizConverter;
    }

    public void exit() {
        quizConverter.exit();
    }

    public void loadQuizzes() {
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
        ClientCallback client = user.getClient();
        lobby.setHostCallBack(client);
        //Adds lobby to server
        client.addLobby(lobby,client);
    }
}

