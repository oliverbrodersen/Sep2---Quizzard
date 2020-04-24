package client.views.hostmain;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;


public class HostMainVC implements ViewController {

    private HostMainVM vm;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getHostMainVM();
    }

    @Override
    public void reset() {

    }

    public void onCreatePressed(ActionEvent actionEvent) {
    }

    public void onEditPressed(ActionEvent actionEvent) {
    }

    public void onDeletePressed(ActionEvent actionEvent) {
    }

    public void onHostPressed(ActionEvent actionEvent) {
    }

    public void onExitPressed(ActionEvent actionEvent) {
    }
}
