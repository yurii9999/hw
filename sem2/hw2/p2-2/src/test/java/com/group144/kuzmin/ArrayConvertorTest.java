package com.group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayConvertorTest {
    @Test
    public void convertArrayTest() {
        int[][] array = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        String correct = "5 2 3 6 9 8 7 4 1";
        String actual = ArrayConvertor.toSpural(array);

        assertEquals(correct, actual);
    }
}