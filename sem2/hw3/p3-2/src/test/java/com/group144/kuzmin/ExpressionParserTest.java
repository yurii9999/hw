package com.group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionParserTest {

    @Test
    public void nextTokenTest() {
        String string = "( -12  3123 133";
        ExpressionParser parser = new ExpressionParser(string);
        String[] right = {"(", "-12", "3123", "133"};
        String[] result = new String[4];

        for (int i = 0; i < 4; i++)
            result[i] = parser.nextToken();

        assertArrayEquals(right, result);

    }
}