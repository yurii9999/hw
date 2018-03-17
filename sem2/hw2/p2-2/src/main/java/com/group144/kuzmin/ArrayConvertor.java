package com.group144.kuzmin;

public class ArrayConvertor {
    public static String toSpural(int[][] array) {
        final int length = array.length;
        int i = length / 2;
        int j = length / 2;

        int spiraleEdge = 3;

        String result = array[i][j] + " ";
        while (j != 0) {

            int steps = spiraleEdge - 1;

            j--;
            for (int k = 0; k < steps - 1; k++) {
                result += array[i][j] + " ";
                i++;
            }

            for (int k = 0; k < steps; k++) {
                result += array[i][j] + " ";
                j++;
            }

            for (int k = 0; k < steps; k++) {
                result += array[i][j] + " ";
                i--;
            }

            for (int k = 0; k < steps; k++) {
                result += array[i][j] + " ";
                j--;
            }

            result += array[i][j] + " ";
            spiraleEdge += 2;
        }

        return result.substring(0, result.length() - 1);
    }
}
