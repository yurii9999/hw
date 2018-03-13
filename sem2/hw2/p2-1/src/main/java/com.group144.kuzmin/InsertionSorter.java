package com.group144.kuzmin;

public class InsertionSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j].compareTo(array[j - 1]) < 0) {
                T temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;

                j--;
            }
        }
    }
}
