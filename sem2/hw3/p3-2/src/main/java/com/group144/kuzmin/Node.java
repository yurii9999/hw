package com.group144.kuzmin;

public abstract class Node {
    protected Node right;
    protected Node left;
    protected String data;

    public abstract void print();
    public abstract int calculate();
}
