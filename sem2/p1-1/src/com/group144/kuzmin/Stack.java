package com.group144.kuzmin;

public class Stack {
    private List list = new List();

    public void push(int value) {
        int size = list.size();
        list.add(value, size);
    }

    public int peek() {
        if (list.isEmpty())
            return -1;

        return list.peek(0);
    }

    public int pop() {
        if (list.isEmpty())
            return -1;

        int result = list.peek(0);
        list.delete(0);
        return result;
    }

    public void print() {
        list.print();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
