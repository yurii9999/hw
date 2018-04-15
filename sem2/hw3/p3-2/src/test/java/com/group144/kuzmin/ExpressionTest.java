package com.group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTest {

    @Test
    public void calculateTest() {
        Expression expression = new Expression("/ ( - ( * 2 2) 2) ( + 1 0)");
        assertEquals(2, expression.calculate());
    }

    @Test
    public void largeNumbersTest() {
        Expression expression = new Expression("/ 333333 111111");
        assertEquals(3, expression.calculate());
    }

    @Test
    public void longExpressionTest() {
        Expression expression = new Expression("+ 1 (+ 1 (+ 1 (+ 1 (+ 1 (+ 1 (+ 1 1))))))");
        assertEquals(8, expression.calculate());
    }

    @Test
    public void plusTest() {
        Expression expression = new Expression("+ 9 1");
        assertEquals(10, expression.calculate());
    }

    @Test
    public void minusTest() {
        Expression expression = new Expression("- 9 99");
        assertEquals(-90, expression.calculate());
    }

    @Test
    public void multiplyTest() {
        Expression expression = new Expression("* 9 7");
        assertEquals(63, expression.calculate());
    }

    @Test
    public void devideTest() {
        Expression expression = new Expression("/ 100 10");
        assertEquals(10, expression.calculate());
    }
}