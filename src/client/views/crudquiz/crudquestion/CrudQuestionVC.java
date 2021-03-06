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

    @FXML private TextArea questionArea, answerArea;
    @FXML private TextField timeField;
    @FXML private CheckBox correctCheckBox;
    @FXML private Label errorLabel;

    @FXML
    private TableView<Answer> answersTable;
    @FXML private TableColumn<String, Answer> answerColumn;
    @FXML private TableColumn<Boolean, Answer> correctColumn;

    private ObservableList<Answer> observableAnswers = FXCollections.observableArrayList();

    @Override
    public void init() {
        this.vm = (CrudQuestionVM) ViewModelFactory.getInstance().getVM("crudquestion");
        errorLabel.setVisible(false);


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
        questionArea.setText(null);
        answerArea.setText(null);
        correctCheckBox.setSelected(false);
        timeField.setText(null);
    }

    public void onSubmitAnswerPressed(ActionEvent actionEvent) {
        Answer answer = vm.submitAnswer();
        if (answer != null)
        {
            observableAnswers.add(answer);
            errorLabel.setVisible(false);
        }
        else {
            errorLabel.setVisible(true);
            errorLabel.setText("Cannot submit empty answer.");
        }
    }

    public void onBackPressed(ActionEvent actionEvent) {
        vm.backPressed();
        reset();
        ViewHandler.getInstance().openView("crudquiz");

    }

    public void OnDeleteAnswerPressed(ActionEvent actionEvent) {
        Answer selectedAnswer = answersTable.getSelectionModel().getSelectedItem();
        observableAnswers.remove(selectedAnswer);
    }

    public void onSubmitQuestionPressed(ActionEvent actionEvent) {
        if (questionArea.getText() != null && !answersTable.getItems().isEmpty() && timeField.getText() != null)
        {
            errorLabel.setVisible(false);
            vm.submitQuestion(observableAnswers);
            reset();
            ViewHandler.getInstance().openView("crudquiz");
        }
        else {
            errorLabel.setVisible(true);
            errorLabel.setText("Cannot submit empty question.");
        }
    }
}
