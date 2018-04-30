package group144.kuzmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.InputEvent;

import java.util.EventObject;

public class Controller {
    private SpinnerValueFactory<Integer> operandA = new SpinnerValueFactory.IntegerSpinnerValueFactory(-100, 100, 0);
    private SpinnerValueFactory<Integer> operandB = new SpinnerValueFactory.IntegerSpinnerValueFactory(-100, 100, 0);
    private ObservableList<Character> operators = FXCollections.observableArrayList('+', '-', '*', '/');

    @FXML
    private ChoiceBox<Character> operatorsBox;
    @FXML
    private Label resultLabel;
    @FXML
    private Spinner<Integer> operandASpinner;
    @FXML
    private Spinner<Integer> operandBSpinner;

    @FXML
    private void initialize() {
        operatorsBox.setItems(operators);
        operatorsBox.setValue('+');
        operandASpinner.setValueFactory(operandA);
        operandBSpinner.setValueFactory(operandB);
        resultLabel.setText("0.0");
    }

    public void update(InputEvent event) {
        resultLabel
                .setText(String.valueOf(Calculator
                        .calculate(operandASpinner.getValue(),
                                operandBSpinner.getValue(),
                                operatorsBox.getValue())));
    }

    public void printEvent(InputEvent event) {
        System.out.println(event.getEventType());
    }
}
