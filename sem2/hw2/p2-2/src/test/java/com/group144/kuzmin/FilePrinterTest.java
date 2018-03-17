package com.group144.kuzmin;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class FilePrinterTest {
    @Test
    public void filePrinterTest() {
        String spuralOrder = "5 2 3 6 9 8 7 4 1";

        Printer printer = new FilePrinter();
        printer.print(spuralOrder);

        int[] array = {5, 2, 3, 6, 9, 8, 7, 4, 1};
        final int length = array.length;
        int[] result = new int[length];

        try (FileReader reader = new FileReader("output.txt"))
        {
            for (int i = 0; i < length; i++) {
                result[i] = reader.read() - '0';
                reader.read(); // ' '
            }
        }
        catch (IOException ex) {
        }

        assertArrayEquals(array, result);
    }

    @Test
    public void convertArrayTest() {
        int[][] array = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        String correct = "5 2 3 6 9 8 7 4 1";
        String actual = ArrayConvertor.toSpural(array);

        assertEquals(correct, actual);
    }
}