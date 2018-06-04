package com.group144.kuzmin;

public class SimpleHasher implements Hasher {
    /**
     * Hasher: hash equals first char of string
     *
     * @param string method return hash of this string
     * @param arraySize amount fields in hash table
     * @return hash
     */
    @Override
    public int getHash(String string, int arraySize) {
        return string.charAt(0) % arraySize;
    }
}