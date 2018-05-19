package group144.kuzmin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    Label expression;

    ExpressionReader expressionReader = new ExpressionReader();

    public void clicked_1(MouseEvent mouseEvent) {
        expressionReader.readNext('1');
        updateLabel();
    }

    public void clicked_2(MouseEvent mouseEvent) {
        expressionReader.readNext('2');
        updateLabel();
    }

    public void clicked_3(MouseEvent mouseEvent) {
        expressionReader.readNext('3');
        updateLabel();
    }

    public void clicked_4(MouseEvent mouseEvent) {
        expressionReader.readNext('4');
        updateLabel();
    }

    public void clicked_5(MouseEvent mouseEvent) {
        expressionReader.readNext('5');
        updateLabel();
    }

    public void clicked_6(MouseEvent mouseEvent) {
        expressionReader.readNext('6');
        updateLabel();
    }

    public void clicked_7(MouseEvent mouseEvent) {
        expressionReader.readNext('7');
        updateLabel();
    }

    public void clicked_8(MouseEvent mouseEvent) {
        expressionReader.readNext('8');
        updateLabel();
    }

    public void clicked_9(MouseEvent mouseEvent) {
        expressionReader.readNext('9');
        updateLabel();
    }

    public void clicked_0(MouseEvent mouseEvent) {
        expressionReader.readNext('0');
        updateLabel();
    }

    public void clicked_plus(MouseEvent mouseEvent) {
        expressionReader.readNext('+');
        updateLabel();
    }

    public void clicked_minus(MouseEvent mouseEvent) {
        expressionReader.readNext('-');
        updateLabel();
    }

    public void clicked_multiply(MouseEvent mouseEvent) {
        expressionReader.readNext('*');
        updateLabel();
    }

    public void clicked_divide(MouseEvent mouseEvent) {
        expressionReader.readNext('/');
        updateLabel();
    }

    public void clicked_c(MouseEvent mouseEvent) {
        expressionReader.backspase();
        updateLabel();
    }

    public void clicked_equals(MouseEvent mouseEvent) {
        String currentExpression = expressionReader.getExpression();
        expression.setText(currentExpression + " = " + Calculator.calculate(currentExpression));
    }

    private void updateLabel() {
        expression.setText(expressionReader.getExpression());
    }
}
