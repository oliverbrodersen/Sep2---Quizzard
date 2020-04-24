package client.views.hostmain;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;


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
}
