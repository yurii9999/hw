package com.group144.kuzmin;

public class SimpleHasher implements Hasher {
    @Override
    public int getHash(String string, int arraySize) {
        return string.charAt(1) % arraySize;
    }
}
