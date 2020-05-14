package client.views.hostmain;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import shared.transferobjects.Quiz;

import java.rmi.RemoteException;

public class HostMainVC implements ViewController {

    private HostMainVM vm;
    @FXML private Text emailText;
    @FXML private Text nameText;
    @FXML private Text userTypeText;
    @FXML private Button createButton, editButton, hostButton;

    @FXML private TableView<Quiz> hostQuizTable;
    @FXML private TableColumn<String, Quiz> quizTitleColumn;
    @FXML private TableColumn<String, Quiz> quizSubjectColumn;
    @FXML private TableColumn<String, Quiz> quizLengthColumn;


    @Override
    public void init() {
        this.vm = (HostMainVM) ViewModelFactory.getInstance().getVM("hostmain");

        createButton.visibleProperty().bindBidirectional(vm.createButtonProperty());
        editButton.visibleProperty().bindBidirectional(vm.editButtonProperty());
        hostButton.visibleProperty().bindBidirectional(vm.hostButtonProperty());
        emailText.textProperty().bindBidirectional(vm.emailTextProperty());
        nameText.textProperty().bindBidirectional(vm.nameTextProperty());
        userTypeText.textProperty().bindBidirectional(vm.userClassTextProperty());
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
        ViewHandler.getInstance().openView("crudquiz");
    }

    public void onEditPressed(ActionEvent actionEvent) {

    }

    public void onDeletePressed(ActionEvent actionEvent) {
        Quiz quizSelected = hostQuizTable.getSelectionModel().getSelectedItem();
        vm.deleteQuiz(quizSelected);
        vm.getQuizzes().remove(quizSelected);
        hostQuizTable.setItems(vm.getQuizzes());
    }

    public void onHostPressed(ActionEvent actionEvent) throws RemoteException
    {
        vm.host(hostQuizTable.getSelectionModel().getSelectedItem());
        ViewHandler.getInstance().openView("lobbyview");
    }

    public void onExitPressed(ActionEvent actionEvent) {
        vm.exit();
        Platform.exit();
    }
}
