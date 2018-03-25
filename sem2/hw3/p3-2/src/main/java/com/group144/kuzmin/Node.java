package com.group144.kuzmin;

public abstract class Node {
    protected Node right;
    protected Node left;
    protected String data;

    /**
     * Method calculate node of tree
     *
     * @return result of calculate
     */
    public abstract int calculate();
}
