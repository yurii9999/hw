package group144.kuzmin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sorter {
    /**
     * Method sorts array using comparator
     *
     * @param array array that you want to sort
     * @param comparator rule that objects comparing
     * @param <T> object's type
     */
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++)
            for (int j = i; j < array.length; j++)
                if (comparator.compare(array[i], array[j]) > 0)
                    swap(array, i, j);
    }

    /**
     * Method returns sorted list using comparator
     *
     * @param list list that you want to sort
     * @param comparator rule that object comparing
     * @param <T> object's type
     * @return sorted list
     */
    public static <T> List<T> sort(List<? extends T> list, Comparator<T> comparator) {
        T[] array = (T[]) list.toArray();
        sort(array, comparator);
        return Arrays.asList(array);
    }

    /**
     * Method swaps two array's elements
     *
     * @param array array that you want to change
     * @param i first's position
     * @param j second's position
     * @param <T> array's type
     */
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


