package com.group144.kuzmin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[][] array;
        int edgeSize;
        try {
            Scanner ifstream = new Scanner(new File("input.txt"));
            edgeSize = ifstream.nextInt();
            array = new int[edgeSize][edgeSize];
            for (int j = 0; j < edgeSize; j++)
                for (int i = 0; i < edgeSize; i++)
                    array[i][j] = ifstream.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
            return;
        }

        System.out.println("Enter 0 to print to console, 1 to file");
        Scanner in = new Scanner(System.in);
        Printer printer = null;
        printerChoice action = printerChoice.values()[in.nextInt()];
        switch (action) {
            case CONSOLE:
                printer = new ConsolePrinter();
                break;
            case FILE:
                printer = new FilePrinter();
                break;
        }
        printer.print(ArrayConvertor.toSpural(array));
    }

    public enum printerChoice {
        CONSOLE, FILE
    }
}