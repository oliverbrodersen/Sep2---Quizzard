package client.views.hostmain.createquiz;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.mainview.MainVM;

public class CreateQuizVC implements ViewController {
    private CreateQuizVM vm;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getCreateQuizVM();
    }

    @Override
    public void reset() {

    }
}
