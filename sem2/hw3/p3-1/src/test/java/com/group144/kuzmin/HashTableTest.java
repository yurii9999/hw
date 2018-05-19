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

    @Test
    public void collisionTest() {
        Hasher hasher = new SimpleHasher();
        HashTable hashTable = new HashTable(hasher, 10);
        hashTable.add(new Element("cFirst key", 3));
        hashTable.add(new Element("ca", 3));
        hashTable.add(new Element("m == c (mod 10)", 3));
        hashTable.add(new Element("mmmmmm", 3));

        assertEquals(1, hashTable.amountConflicts());
    }

    @Test
    public void amountConflictsTest() {
        Hasher hasher = new SimpleHasher();
        HashTable hashTable = new HashTable(hasher, 3);
        hashTable.add(new Element("aFirst key", 3));
        hashTable.add(new Element("aSecond key", 3));
        hashTable.add(new Element("bFirst key", 3));
        hashTable.add(new Element("bSecond key", 3));
        hashTable.add(new Element("cFirst key", 3));
        hashTable.add(new Element("cSecond key", 3));

        assertEquals(3, hashTable.amountConflicts());
    }

    @Test
    public void loadFactorTest() {
        HashTable hashTable = prepareTable();
        assertEquals(1.0, hashTable.loadFactor(), 0.1);
    }

    private HashTable prepareTable() {
        Hasher hasher = new ClassicHasher();
        HashTable table = new HashTable(hasher, 10);

        for (int i = 0; i < 10; i++)
            table.add(new Element("element" + i, i));

        return table;
    }
}