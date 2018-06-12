package com.group144.kuzmin;

public interface Sorter<T extends Comparable> {
    void sort(T[] array);
}
