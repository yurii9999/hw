package com.group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsertionSorterTest {
    @Test
    public void sort() {
        Integer[] array = {1, 3, 4, 2, 0};
        Integer[] sorted = {0, 1, 2, 3, 4};

        Sorter<Integer> sorter = new InsertionSorter<>();
        sorter.sort(array);

        assertArrayEquals(array, sorted);
    }

    @Test
    public void oneDifferent() {
        Integer[] array = {1, 5, 4, 9, 11};
        Integer[] sorted = {1, 4, 5, 9, 11};

        Sorter<Integer> sorter = new InsertionSorter<>();
        sorter.sort(array);

        assertArrayEquals(array, sorted);
    }
}