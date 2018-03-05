package com.group144.kuzmin;

public class ClassicHasher implements Hasher {
    @Override
    public int getHash(String string, int arraySize) {
        int result = 0;
        for (int i = 0; i < string.length(); i++)
            result += string.charAt(i) % arraySize;

        return result % arraySize;
    }
}
