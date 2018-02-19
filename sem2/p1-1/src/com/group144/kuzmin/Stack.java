package com.group144.kuzmin;

public class Stack {
    private List list = new List();

    public void push(int value) {
        int size = this.list.size();
        this.list.add(value, size);
    }

    public int peek() {
        if (this.isEmpty())
            return -1;

        return this.list.peek(0);
    }

    public int pop() {
        if (this.isEmpty())
            return -1;

        int result = this.list.peek(0);
        this.list.delete(0);
        return result;
    }

    public void print() {
        this.list.print();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }
}
