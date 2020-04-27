package client.views.lobbyview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import shared.transferobjects.UserID;

public class LobbyVC implements ViewController {
    private LobbyVM vm;
    private ViewHandler vh;
    @FXML private Label pinLabel, userTypeLabel;
    @FXML private Button startButton;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getLobbyVM();
        userTypeLabel.textProperty().bindBidirectional(vm.userTypeLabelProperty());
        pinLabel.textProperty().bindBidirectional(vm.pinLabelProperty());
        vm.setup(startButton);
    }

    @Override
    public void reset() {

    }

    public void StartQuizButton(ActionEvent actionEvent){
        vm.startQuiz();
    }
}
