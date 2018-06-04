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

    /**
     * Method adds elements in hash table
     * @param element element which you want to add
     */
    public void add(Element element) {
        int hash = hasher.getHash(element.string(), arraySize);
        data[hash].add(element, 0);
    }

    /**
     * Method deletes elements from HT by key
     *
     * @param key given key
     * @throws NotFoundException throws when HT is not included elements with this key
     */
    public void delete(String key) throws NotFoundException {
        int hash = hasher.getHash(key, arraySize);
        data[hash].deleteByKey(key);
    }

    /**
     * Method rebuilds hash table: change hasher and amount filed
     *
     * @param hasher new hasher
     * @param newSize new size
     */
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

    /**
     * Method checks is element included in HT by key
     *
     * @param key given key
     * @return true if HT includes and false otherwise
     */
    public boolean isIncluded(String key) {
        int hash = hasher.getHash(key, arraySize);
        return data[hash].isIncluded(key);
    }

    /**
     * Method give element from HT by key
     *
     * @param key given key
     * @return element that's key is equal given
     * @throws NotFoundException throws when HT is not included element with this key
     */
    Element getElement(String key) throws NotFoundException {
        int hash = hasher.getHash(key, arraySize);
        return data[hash].getByKey(key);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("");

        for (int i = 0; i < arraySize; i++)
            result.append(i + ": " + data[i] + '\n');

        return result.toString();
    }

    /**
     * Method gives amount field int HT
     * @return amount fields
     */
    public int arraySize() {
        return arraySize;
    }

    /**
     * Method gives max HT's fields' length
     * @return max length of field
     */
    public int maxLength() {
        int result = data[0].size();
        for (int i = 1; i < arraySize; i++)
            if (data[i].size() > result)
                result = data[i].size();

        return result;
    }

    /**
     * Method gives amount fields that includes more than one element
     * @return amount fields
     */
    public int amountConflicts() {
        int result = 0;
        for (int i = 0; i < arraySize; i++)
            if (data[i].size() > 1)
                result++;

        return result;
    }

    /**
     * Methods gives amount elements in HT in all fields
     * @return amount elements
     */
    public int size() {
        int result = 0;

        for (int i = 0; i < arraySize; i++)
            result += data[i].size();

        return result;
    }

    /**
     * Method calculates load factor
     * @return load factor
     */
    public double loadFactor(){
        return (double) size() / arraySize;
    }
}
