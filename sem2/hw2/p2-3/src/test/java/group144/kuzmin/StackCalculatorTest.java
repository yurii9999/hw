package group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackCalculatorTest {
    @Test
    public void singleDigitTest() {
        StackCalculator a = new StackCalculator("3");
        assertEquals(3, a.calculate());
    }

    @Test public void calculateTest() {
        StackCalculator a = new StackCalculator("3 3 + 4 *");
        assertEquals(24, a.calculate());
    }
}