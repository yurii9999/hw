package group144.kuzmin;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class SorterTest {
    @Test
    public void stringSortTest() {
        String[] animals = {"Cat", "Horse", "Fish"};
        String[] sorted = {"Cat", "Fish", "Horse"};
        Sorter.sort(animals, Comparator.comparingInt(String::length));
        assertArrayEquals(sorted, animals);
    }

    @Test
    public void doubleSortTest() {
        Double[] array = {1313.1, 1413.0, 3123.3333, 3123.3331, 4.9};
        Double[] sorted = {4.9, 1313.1, 1413.0, 3123.3331, 3123.3333};
        Sorter.sort(array, Comparator.comparingDouble(Double::doubleValue));
        assertArrayEquals(sorted, array);
    }

    @Test
    public void integerSortTest() {
        Integer[] array = {1, 6, 2, 9, 2, 10, 5, 8};
        Integer[] sorted = {1, 2, 2, 5, 6, 8, 9, 10};
        Sorter.sort(array, Comparator.comparingInt(Integer::intValue));
        assertArrayEquals(sorted, array);
    }

    @Test
    public void allOneTest() {
        Integer[] allOne = {1, 1, 1, 1, 1, 1, 1};
        Integer[] sorted = {1, 1, 1, 1, 1, 1, 1};
        Sorter.sort(allOne, Comparator.comparingInt(Integer::intValue));
        assertArrayEquals(sorted, allOne);
    }

    @Test
    public void reversedIntegersTest() {
        Integer[] reversed = {1, 2, 5, 7, 8, 8, 9, 14, 17};
        Integer[] sorted = {17, 14, 9, 8, 8, 7, 5, 2, 1};
        Sorter.sort(reversed, Comparator.comparingInt(Integer::intValue).reversed());
        assertArrayEquals(sorted, reversed);
    }

    @Test
    public void integerListSortTest() {
        List<Integer> list = Arrays.asList(new Integer[] {9, 1, 4, 5, 8, 3, 5, 1});
        List<Integer> sorted = Arrays.asList(new Integer[] {1, 1, 3, 4, 5, 5, 8, 9});
        list = Sorter.sort(list, Comparator.comparingInt(Integer::intValue));
        assertEquals(sorted, list);
    }

    @Test
    public void stringListSortTest() {
        List<String> list = Arrays.asList(new String[] {"Zebra", "Cat", "Horse", "Fish"});
        List<String> sorted = Arrays.asList(new String[] {"Cat", "Fish", "Horse", "Zebra"});
        list = Sorter.sort(list, Comparator.comparingInt(str -> (str + "1").charAt(0)));
        assertEquals(sorted, list);
    }
}