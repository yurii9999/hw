package group144.kuzmin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;

public class Controller {
    @FXML
    Label result;

    Calculator calculator = new Calculator();
    public void clickedEquals(MouseEvent mouseEvent) {
        result.setText(calculator.calculate());
    }

    public void clickedSign(MouseEvent mouseEvent) {
        Button clicked = (Button) mouseEvent.getSource();
        result.setText(calculator.readOperator(clicked.getText()));
    }

    public void clickedDigit(MouseEvent mouseEvent) {
        Button clicked = (Button) mouseEvent.getSource();
        result.setText(calculator.readNextDigit(clicked.getText()));
    }

    public void clickedClear(MouseEvent mouseEvent) {
        result.setText(calculator.clear());
    }
}
