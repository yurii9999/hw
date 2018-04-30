package group144.kuzmin;

import java.util.Date;
import java.util.Random;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        final int SIZE = 100000;
        final int MAX = 20000;
        Random random = new Random();

        Integer[] sortBySingleThread = Stream
                .generate(() -> random.nextInt(MAX))
                .limit(SIZE)
                .toArray(Integer[]::new);
        Integer[] sortByMultiThread = copy(sortBySingleThread);

        Date beginST = new Date();
        Sorter.sort(sortBySingleThread, Integer::compareTo);
        Date endST = new Date();

        Date beginMT = new Date();
        MultithreadSorter.sort(sortByMultiThread, Integer::compareTo);
        Date endMT = new Date();

        long sTTime = endST.getTime() - beginST.getTime();
        long mTTime = endMT.getTime() - beginMT.getTime();
        System.out.println("SingleThread time: " + sTTime + "\nMultiThread time: " + mTTime);
    }

    private static Integer[] copy(Integer[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i< array.length; i++)
            result[i] = array[i];

        return result;
    }
}
