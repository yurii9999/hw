package com.group144.kuzmin;

public interface Hasher {
    /**
     * Method calculates hash of string
     *
     * @param string method return hash of this string
     * @param arraySize amount fields in hash table
     * @return hash
     */
    int getHash(String string, int arraySize);
}
