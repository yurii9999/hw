package com.group144.kuzmin;

public class Main {

    public static void main(String[] args) {
        Integer[] array = {1, 2, 5};
        Sorter<Integer> sorter = new BubbleSorter<>();

        sorter.sort(array);

        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);
    }
}