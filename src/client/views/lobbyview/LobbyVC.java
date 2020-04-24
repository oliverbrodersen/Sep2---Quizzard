package client.views.lobbyview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LobbyVC implements ViewController {
    private LobbyVM vm;
    private ViewHandler vh;
    @FXML private Label pinLabel;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getLobbyVM();
        pinLabel.setText("Pin: " + vm.getPin());
    }

    @Override
    public void reset() {

    }
}
