package client.views.createaccount;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;

public class CreateAccountVC implements ViewController {

    private CreateAccountVM vm;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = (CreateAccountVM) vmf.getVM("createaccount");
    }

    @Override
    public void reset() {

    }
}
