package client.views.hostmain;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Quiz;

import java.rmi.RemoteException;

public class HostMainVC implements ViewController {

    private HostMainVM vm;
    private ViewHandler vh;

    @FXML private TableView<Quiz> hostQuizTable;
    @FXML private TableColumn<String, Quiz> quizTitleColumn;
    @FXML private TableColumn<String, Quiz> quizSubjectColumn;
    @FXML private TableColumn<String, Quiz> quizLengthColumn;


    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getHostMainVM();

        vm.loadQuizzes();
        hostQuizTable.setItems(vm.getQuizzes());
        quizTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        quizSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        quizLengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
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

    public void onHostPressed(ActionEvent actionEvent) throws RemoteException
    {
        vm.host(hostQuizTable.getSelectionModel().getSelectedItem());
        vh.openView("lobbyview");
    }

    public void onExitPressed(ActionEvent actionEvent) {
        vm.exit();
        Platform.exit();
    }
}