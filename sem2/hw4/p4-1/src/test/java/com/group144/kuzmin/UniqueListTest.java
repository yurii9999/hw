package com.group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueListTest {

    @Test(expected = AlreadyIncludedException.class)
    public void AlreadyIncludedExceptionTest() {
        List<Integer> list = new UniqueList<>();
        list.add(3, 0);
        list.add(3, 0);
    }

    @Test(expected = NotFoundException.class)
    public void NotFoundExceptionTest() {
        List<Integer> emptyList = new UniqueList<>();
        emptyList.delete((Integer) 3);
    }

    @Test(expected = NotFoundException.class)
    public void deleteDeleteTest() {
        List<Integer> list = new UniqueList<>();
        list.add(3, 0);
        list.delete((Integer) 3);
        list.delete((Integer) 3);
    }

    @Test
    public void addTest() {
        List<Integer> list = new UniqueList<>();

        for (int i = 0; i < 10; i++)
            list.add(i, 0);

        int[] array = new int[10];
        for (int i = 0; i < 10; i++)
            array[i] = list.peek(i);

        assertArrayEquals(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, array);
    }
}