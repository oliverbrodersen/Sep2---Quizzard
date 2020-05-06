package client.views.crudquiz;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Participant;
import shared.transferobjects.Question;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class CrudQuizVC implements ViewController {
    private CrudQuizVM vm;
    private ViewHandler vh;

    @FXML private TextField nameField;
    @FXML private TextField subjectField;
    @FXML private ChoiceBox difficultyChoice;

    @FXML private TableView<Question> questionsTable;
    @FXML private TableColumn<String, Question> questionColumn;
    @FXML private TableColumn<Integer, Question> noAnswersColumn;
    @FXML private TableColumn<Integer, Question> timeColumn;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getCrudQuizVM();
        vm.addListener("onQuestionCreated", this::updateQuestionsList);
        nameField.textProperty().bindBidirectional(vm.nameFieldProperty());
        subjectField.textProperty().bindBidirectional(vm.subjectFieldProperty());
        difficultyChoice.valueProperty().bindBidirectional(vm.difficultyProperty());
        questionsTable.setItems(vm.getQuestions());
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        noAnswersColumn.setCellValueFactory(new PropertyValueFactory<>("noAnswers"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void updateQuestionsList(PropertyChangeEvent evt) {
        questionsTable.setItems(vm.setQuestions((Question) evt.getNewValue()));
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
        vh.openView("crudquestion");
    }

    public void onBackPressed(ActionEvent actionEvent) {
        nameField.setText(null);
        subjectField.setText(null);
        vh.openView("hostmain");
    }
}
