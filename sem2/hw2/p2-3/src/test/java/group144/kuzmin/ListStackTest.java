package group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListStackTest {
    @Test
    public void pushAndPopTest() {
        Stack<String> a = new ListStack();
        a.push("I");
        a.push("gaf");
        assertEquals("gaf", a.pop());
        assertEquals("I", a.pop());
    }

    @Test
    public void isEmptyTest() {
        Stack<String> a = new ListStack();
        assertTrue(a.isEmpty());
        a.push("3333");
        assertFalse(a.isEmpty());
        a.pop();
        assertTrue(a.isEmpty());
    }

}