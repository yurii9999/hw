package com.group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class OperandTest {

    @Test
    public void calculateTest() {
        Node operand = new Operand("71");
        assertEquals(71, operand.calculate());
    }
}