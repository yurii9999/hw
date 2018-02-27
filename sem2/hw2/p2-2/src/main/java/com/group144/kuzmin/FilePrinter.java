package com.group144.kuzmin;

import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Printer {
    @Override
    public void printArray(int[][] array) {

        try (FileWriter writer = new FileWriter("output.txt", false))
        {
            final int length = array.length;
            int i = length / 2;
            int j = length / 2;

            int spiraleEdge = 3;

            writer.append(array[i][j] + " ");
            while (j != 0) {

                int steps = spiraleEdge - 1;

                j--;
                for (int k = 0; k < steps - 1; k++) {
                    writer.append(array[i][j] + " ");
                    i++;
                }

                for (int k = 0; k < steps; k++) {
                    writer.append(array[i][j] + " ");
                    j++;
                }

                for (int k = 0; k < steps; k++) {
                    writer.append(array[i][j] + " ");
                    i--;
                }

                for (int k = 0; k < steps; k++) {
                    writer.append(array[i][j] + " ");
                    j--;
                }

                writer.append(array[i][j] + " ");
                spiraleEdge += 2;
            }

            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage() + "no");
        }
    }
}
