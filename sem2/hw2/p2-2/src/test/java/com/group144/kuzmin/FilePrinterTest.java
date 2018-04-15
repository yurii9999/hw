package com.group144.kuzmin;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class FilePrinterTest {
    @Test
    public void filePrinterTest() throws IOException {
        String spuralOrder = "5 2 3 6 9 8 7 4 1";

        Printer printer = new FilePrinter();
        printer.print(spuralOrder);

        int[] array = {5, 2, 3, 6, 9, 8, 7, 4, 1};
        final int length = array.length;
        int[] result = new int[length];

        FileReader reader = new FileReader("output.txt");

        for (int i = 0; i < length; i++) {
            result[i] = reader.read() - '0';
            reader.read(); // ' '
        }

        assertArrayEquals(array, result);
    }
}