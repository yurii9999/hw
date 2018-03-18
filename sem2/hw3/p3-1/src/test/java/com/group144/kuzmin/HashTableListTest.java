package com.group144.kuzmin;

import org.junit.Test;
import static org.junit.Assert.*;

public class HashTableListTest {

    @Test
    public void deleteTest() {
        HashTableList list = prepareList();

        try {
            for (int i = 0; i < 10; i++)
                list.deleteByKey("element" + i);
        } catch (NotFoundException e) {
            fail();
        }

        assertTrue(list.isEmpty());
    }

    @Test
    public void isIncludedFalseTest() {
        HashTableList list = prepareList();
        assertFalse(list.isIncluded("element100"));
    }

    @Test
    public void isIncludedTrueTest() {
        HashTableList list = prepareList();
        assertFalse(list.isIncluded("element6"));
    }

    @Test
    public void getByKeyTest() {
        HashTableList list = prepareList();
        Element element = null;
        try {
            element = list.getByKey("element5");
        } catch (NotFoundException e) {
            fail();
        }

        assertEquals(element.value(), 5);
    }

    @Test(expected = NotFoundException.class)
    public void NotFoundExceptionTest() {
        HashTableList list = prepareList();
//        list.deleteByKey("element30");
        }

    private HashTableList prepareList() {
        HashTableList list = new HashTableList();
        for (int i = 0; i < 10; i ++)
            list.add(new Element("element" + i, i), 0);

        return list;
    }

}