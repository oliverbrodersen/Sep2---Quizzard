package client.views.crudquiz.crudquestion;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Answer;



public class CrudQuestionVC implements ViewController {
    private CrudQuestionVM vm;
    private ViewHandler vh;

    @FXML private TextArea questionArea, answerArea;
    @FXML private TextField timeField;
    @FXML private CheckBox correctCheckBox;

    @FXML
    private TableView<Answer> answersTable;
    @FXML private TableColumn<String, Answer> answerColumn;
    @FXML private TableColumn<Boolean, Answer> correctColumn;

    private ObservableList<Answer> observableAnswers = FXCollections.observableArrayList();

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getCrudQuestionVM();

        questionArea.textProperty().bindBidirectional(vm.questionAreaProperty());
        answerArea.textProperty().bindBidirectional(vm.answerAreaProperty());
        correctCheckBox.selectedProperty().bindBidirectional(vm.correctCheckBoxProperty());
        timeField.textProperty().bindBidirectional(vm.timeFieldProperty());

        answersTable.setItems(observableAnswers);
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        correctColumn.setCellValueFactory(new PropertyValueFactory<>("correct"));
    }

    @Override
    public void reset() {

    }

    public void onSubmitAnswerPressed(ActionEvent actionEvent) {
        Answer answer = vm.submitAnswer();
        if (answer != null)
        {
            observableAnswers.add(answer);
        }
    }

    public void onBackPressed(ActionEvent actionEvent) {
        vm.backPressed();
        questionArea.setText(null);
        answerArea.setText(null);
        correctCheckBox.setSelected(false);

        vh.openView("crudquiz");

    }

    public void OnDeleteAnswerPressed(ActionEvent actionEvent) {
    }

    public void onSubmitQuestionPressed(ActionEvent actionEvent) {
        vm.submitQuestion(observableAnswers);

        questionArea.setText(null);
        answerArea.setText(null);
        correctCheckBox.setSelected(false);

        vh.openView("crudquiz");
    }
}
