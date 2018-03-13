package com.group144.kuzmin;

public class BubbleSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public void sort(T[] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = i; j < array.length; j++)
                if (array[i].compareTo(array[j]) > 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
    }
}
