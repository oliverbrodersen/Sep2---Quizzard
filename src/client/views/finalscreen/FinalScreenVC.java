package client.views.finalscreen;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;

public class FinalScreenVC implements ViewController {
    private FinalScreenVM vm;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getFinalScreenVM();
    }

    @Override
    public void reset() {

    }
}
