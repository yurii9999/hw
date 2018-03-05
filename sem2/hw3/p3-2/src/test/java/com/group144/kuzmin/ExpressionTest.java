package com.group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTest {

    @Test
    public void calculate() {
        Expression expression = new Expression("/ ( - ( * 2 2) 2) ( + 1 0)");
        assertEquals(2, expression.calculate());
    }
}