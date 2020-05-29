package client.views.createaccount;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;

public class CreateAccountVC implements ViewController {

    private CreateAccountVM vm;

    @Override
    public void init() {
        this.vm = (CreateAccountVM) ViewModelFactory.getInstance().getVM("createaccount");
    }

    @Override
    public void reset() {

    }
}
