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
        String pin = vm.getPin();
        String pinDisplay = pin.charAt(0) + pin.charAt(1) + " " + pin.charAt(2) + pin.charAt(3) + " " +pin.charAt(4) + pin.charAt(5);
        pinLabel.setText("Pin: " + pin);
        if (vm.getUserClass() == null)
            startButton.setVisible(false);
        vm.setUserClass(vm.getUserClass());

    }

    @Override
    public void reset() {

    }

    public void StartQuizButton(ActionEvent actionEvent){
        vm.startQuiz();
    }
}
