package group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void calculatorTest() {
        Calculator calculator = new Calculator();
        calculator.readNextDigit("3");
        calculator.readNextDigit("2");

        calculator.readOperator("+");

        calculator.readNextDigit("6");
        calculator.readNextDigit("8");

        final Double result = Double.valueOf(100);
        assertEquals(String.valueOf(result), calculator.calculate());
    }

    @Test
    public void twoOperatorsTest() {
        Calculator calculator = new Calculator();
        calculator.readNextDigit("4");
        calculator.readOperator("+");
        calculator.readNextDigit("5");
        calculator.readOperator("-");
        calculator.readNextDigit("9");

        final Double result = Double.valueOf(0);
        assertEquals(String.valueOf(result), calculator.calculate());
    }
}