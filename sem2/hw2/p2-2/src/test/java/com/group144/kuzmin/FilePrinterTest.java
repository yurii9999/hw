package com.group144.kuzmin;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class FilePrinterTest {
    @Test
    public void printArrayTest() {
        int[][] array = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[] right = {5, 2, 3, 6, 9, 8, 7, 4, 1};

        Printer printer = new FilePrinter();

        printer.printArray(array);

        final int length = array.length * array[0].length;
        int[] result = new int[length];

        try (FileReader reader = new FileReader("output.txt"))
        {
            for (int i = 0; i < length; i++) {
                result[i] = reader.read() - '0';
                reader.read(); // ' '
            }
        }
        catch (IOException ex) {
            fail();
        }

            assertArrayEquals(right, result);
    }
}