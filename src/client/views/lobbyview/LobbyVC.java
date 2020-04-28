package client.views.lobbyview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Participant;
import shared.transferobjects.Quiz;
import shared.transferobjects.UserID;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class LobbyVC implements ViewController {
    private LobbyVM vm;
    private ViewHandler vh;
    @FXML private Label pinLabel, userTypeLabel, playersCountLabel;
    @FXML private Button startButton, kickButton;

    @FXML private TableView<Participant> participantsTableView;
    @FXML private TableColumn<String, Quiz> participantsColumn;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getLobbyVM();
        vm.addListener("onNewConnected", this::updateParticipantList);
        vm.addListener("onQuizStarted", this::startQuizListener);
        userTypeLabel.textProperty().bindBidirectional(vm.userTypeLabelProperty());
        pinLabel.textProperty().bindBidirectional(vm.pinLabelProperty());
        playersCountLabel.textProperty().bindBidirectional(vm.playersCountLabelProperty());
        participantsTableView.setItems(vm.getParticipants());
        participantsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        vm.setup(startButton, kickButton);
    }

    private void startQuizListener(PropertyChangeEvent evt)
    {
        Platform.runLater(new Runnable(){
            @Override public void run()
            {
                vh.openView("questionview");
            }
        });
    }

    @Override
    public void reset() {

    }

    public void updateParticipantList(PropertyChangeEvent evt)
    {
        participantsTableView.setItems(vm.setParticipants((List<Participant>)evt.getNewValue()));
    }

    public void StartQuizButton(ActionEvent actionEvent){
        vm.startQuiz();
    }

    public void onKickButtonPressed(ActionEvent actionEvent){
        System.out.println("Kick player: " + participantsTableView.getSelectionModel().getSelectedItem().getName());
    }
}
