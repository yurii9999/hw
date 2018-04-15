package com.group144.kuzmin;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class OperatorTest {

    @Test
    public void calculateTest() {
        ExpressionParser parser = new ExpressionParser("+ 3 1");
        Node operator = new Operator(parser);
        assertEquals(4, operator.calculate());
    }
}