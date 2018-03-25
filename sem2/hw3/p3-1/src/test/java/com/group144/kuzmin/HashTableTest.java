package com.group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    @Test
    public void sizeTest() {
        HashTable table = prepareTable();
        assertEquals(table.size(), 10);
    }

    @Test
    public void isIncludedTest() {
        HashTable table = prepareTable();
        assertTrue(table.isIncluded("element2"));
    }

    @Test
    public void getElementTest() throws NotFoundException {
        HashTable table = prepareTable();
        assertEquals(table.getElement("element3").value(), 3);
    }

    private HashTable prepareTable() {
        Hasher hasher = new ClassicHasher();
        HashTable table = new HashTable(hasher, 10);

        for (int i = 0; i < 10; i++)
            table.add(new Element("element" + i, i));

        return table;
    }
}