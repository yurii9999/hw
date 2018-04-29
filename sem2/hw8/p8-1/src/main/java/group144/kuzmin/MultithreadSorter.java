package group144.kuzmin;

import java.util.Comparator;

public class MultithreadSorter {
    /**
     * Method gets array and sorts it
     *
     * @param array array need to sort
     * @param comparator how to compare T's
     * @param <T> type of array's elements
     */
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        sortPart(array, comparator, 0, array.length - 1);
    }

    private static <T> void sortPart(T[] array, Comparator<T> comparator, int from, int to) {
        if (to - from < 1)
            return;

        T pivot = array[from];
        int i = from;
        int j = to;

        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0)
                i++;

            while (comparator.compare(array[j], pivot) > 0)
                j--;

            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        final int mark = i;

        Runnable firstPart = () -> sortPart(array, comparator, from, mark - 1);
        Runnable secondPart = () -> sortPart(array, comparator, mark, to);

        Thread leftSide = new Thread(firstPart);
        Thread rightSide = new Thread(secondPart);

        leftSide.start();
        rightSide.start();
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
