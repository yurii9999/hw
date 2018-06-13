package group144.kuzmin;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MultithreadSorterTest {
    @Test
    public void sortTest() {
        String[] array = {"cat", "fish", "dg", "zebra"};
        Sorter.sort(array, Comparator.comparingInt(String::length));
        assertArrayEquals(new String[]{"dg", "cat", "fish", "zebra"}, array);
    }
}