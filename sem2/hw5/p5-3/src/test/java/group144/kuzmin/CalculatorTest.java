package group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void calculateTest() {
        assertEquals("5.0", Calculator.calculate("3+4/2"));
    }

    @Test
    public void soManyOnesTest() {
        assertEquals("1.0", Calculator.calculate("1/1/1/1/1/1/1/1*1*1*1*1/1/1"));
    }
}