package group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionReaderTest {
    @Test
    public void readSignTwice() {
        ExpressionReader er = new ExpressionReader();
        er.readNext('1');
        er.readNext('+');
        er.readNext('*');
        assertEquals("1+", er.getExpression());
    }

    @Test
    public void backspaceTest() {
        ExpressionReader er = new ExpressionReader();
        er.readNext('1');
        er.readNext('+');
        er.readNext('6');
        er.readNext('1');
        er.readNext('+');
        er.backspase();

        assertEquals("1+61", er.getExpression());
    }

    @Test
    public void incorrectCharReadTest() {
        ExpressionReader er = new ExpressionReader();
        er.readNext('1');
        er.readNext('+');
        er.readNext('6');
        er.readNext('!');
        assertEquals("1+6", er.getExpression());
    }
}