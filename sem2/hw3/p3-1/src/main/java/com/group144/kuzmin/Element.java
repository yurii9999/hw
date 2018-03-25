package com.group144.kuzmin;

public class Element implements Comparable<String> {
    private String string;
    private int value;

    public Element(String string, int value) {
        this.string = string;
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String string() {
        return string;
    }

    @Override
    public int compareTo(String o) {
        return string.compareTo(o);
    }

    @Override
    public String toString() {
        return string + ":" + value;
    }
}
