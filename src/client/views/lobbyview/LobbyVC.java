package client.views.lobbyview;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;

public class LobbyVC implements ViewController {
    private LobbyVM vm;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getLobbyVM();
    }

    @Override
    public void reset() {

    }
}
