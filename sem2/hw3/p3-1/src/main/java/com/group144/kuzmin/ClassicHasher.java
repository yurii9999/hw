package com.group144.kuzmin;

public class ClassicHasher implements Hasher {
    /**
     * Hasher: hash equals sum of all string's char % arraySize
     *
     * @param string method return hash of this string
     * @param arraySize amount fields in hash table
     * @return hash
     */
    @Override
    public int getHash(String string, int arraySize) {
        int result = 0;
        for (int i = 0; i < string.length(); i++)
            result += string.charAt(i) % arraySize;

        return result % arraySize;
    }
}
