package group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStackTest {
    @Test
    public void pushAndPopTest() {
        Stack<String> a = new ArrayStack();
        a.push("I");
        a.push("gaf");
        assertEquals("gaf", a.pop());
        assertEquals("I", a.pop());
    }

    @Test
    public void isEmptyTest() {
        Stack<String> a = new ArrayStack();
        assertTrue(a.isEmpty());
        a.push("3333");
        assertFalse(a.isEmpty());
        a.pop();
        assertTrue(a.isEmpty());
    }
}