package com.group144.kuzmin;

public class ConsolePrinter implements Printer {
    @Override
    public void printArray(int[][] array) {
        final int length = array.length;
        int i = length / 2;
        int j = length / 2;

        int spiraleEdge = 3;

        System.out.print(array[i][j] + " ");
        while (j != 0) {

            int steps = spiraleEdge - 1;

            j--;
            for (int k = 0; k < steps - 1; k++) {
                System.out.print(array[i][j] + " ");
                i++;
            }

            for (int k = 0; k < steps; k++) {
                System.out.print(array[i][j] + " ");
                j++;
            }

            for (int k = 0; k < steps; k++) {
                System.out.print(array[i][j] + " ");
                i--;
            }

            for (int k = 0; k < steps; k++) {
                System.out.print(array[i][j] + " ");
                j--;
            }

            System.out.print(array[i][j] + " ");
            spiraleEdge += 2;
        }
    }
}
