package client.views.finalscreen;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;

public class FinalScreenVC implements ViewController {
    private FinalScreenVM vm;

    @Override
    public void init() {
        this.vm = (FinalScreenVM) ViewModelFactory.getInstance().getVM("finalscreen");
    }

    @Override
    public void reset() {

    }
}
