package client.views.crudquiz;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import shared.transferobjects.Difficulty;
import shared.transferobjects.Question;


public class CrudQuizVC implements ViewController {
    private CrudQuizVM vm;
    private ViewHandler vh;

    @FXML private TextField nameField;
    @FXML private TextField subjectField;
    @FXML private ChoiceBox difficultyChoice;

    @FXML
    private TableView<Question> questionsTable;
    @FXML private TableColumn<String, Question> questionsColumn;
    @FXML private TableColumn<String, Question> noAnswersColumn;
    @FXML private TableColumn<String, Question> timeColumn;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getCrudQuizVM();
        nameField.textProperty().bindBidirectional(vm.nameFieldProperty());
        subjectField.textProperty().bindBidirectional(vm.subjectFieldProperty());
        difficultyChoice.valueProperty().bindBidirectional(vm.difficultyProperty());
    }

    @Override
    public void reset() {

    }

    public void onSubmitPressed(ActionEvent actionEvent) {
        vm.createQuiz();
    }

    public void onDeleteQuestionPressed(ActionEvent actionEvent) {
    }

    public void onEditQuestionPressed(ActionEvent actionEvent) {
    }

    public void onAddQuestionPressed(ActionEvent actionEvent) {
    }

    public void onBackPressed(ActionEvent actionEvent) {
        vh.openView("hostmain");
    }
}
