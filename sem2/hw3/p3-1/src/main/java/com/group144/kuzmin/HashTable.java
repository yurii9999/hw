package com.group144.kuzmin;

public class HashTable {
    private HashTableList[] data;
    private int arraySize;

    private Hasher hasher;

    public HashTable(Hasher hasher, int arraySize) {
        this.arraySize = arraySize;
        data = new HashTableList[this.arraySize];
        for (int i = 0; i < this.arraySize; i++)
            data[i] = new HashTableList();

        this.hasher = hasher;
    }


    public void add(Element element) {
        int hash = hasher.getHash(element.string(), arraySize);
        data[hash].add(element, 0);
    }

    public void delete(String key) throws NotFoundExcaption{
        int hash = hasher.getHash(key, arraySize);
        try {
            data[hash].deleteByKey(key);
        } catch (NotFoundExcaption notFoundExcaption) {
            throw new NotFoundExcaption();
        }
    }

    public void rebuild(Hasher hasher, int newSize) {
        this.hasher = hasher;

        HashTableList[] oldData = data;
        int oldSize = arraySize;

        arraySize = newSize;

        data = new HashTableList[arraySize];
        for (int i = 0; i < arraySize; i++)
            data[i] = new HashTableList();

        for (int i = 0; i < oldSize; i++)
            while(!oldData[i].isEmpty()) {
                add(oldData[i].peek(0));
                oldData[i].delete(0);
            }
    }

    public boolean isIncluded(String key) {
        int hash = hasher.getHash(key, arraySize);
        return data[hash].isIncluded(key);
    }

    Element getElement(String key) throws NotFoundExcaption {
        int hash = hasher.getHash(key, arraySize);
        try {
            return data[hash].getByKey(key);
        }
        catch (NotFoundExcaption e) {
            throw e;
        }
    }

    public void print() {
        for (int i = 0; i < arraySize; i++) {
            System.out.println("========\nList " + i + " :");
            data[i].print();
        }
    }

    public int arraySize() {
        return arraySize;
    }

    public int maxLength() {
        int result = data[0].size();
        for (int i = 1; i < arraySize; i++)
            if (data[i].size() > result)
                result = data[i].size();

        return result;
    }

    public int amountConflicts() {
        int result = 0;
        for (int i = 0; i < arraySize; i++)
            if (data[i].size() > 1)
                result++;

        return result;
    }

    public int size() {
        int result = 0;

        for (int i = 0; i < arraySize; i++)
            result += data[i].size();

        return result;
    }

    public double loadFactor(){
        return (double) size() / arraySize;
    }
}
