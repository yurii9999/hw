package com.group144.kuzmin;

public class List {
    private ListElement head;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(int value, int position) {
        if (position > size || position < 0)
            return;

        size++;
        if (position == 0) {
            head = new ListElement(value, head);
            return;
        }

        ListElement temp = head;

        for (int i = 1; i < position; i++)
            temp = temp.next;

        temp.next = new ListElement(value, temp.next);
    }

    public int peek(int position) {
        ListElement temp = head;

        for (int i = 0; i < position; i++)
            temp = temp.next;

        return temp.value;
    }

    public void delete(int position) {
        if (position >= size || position < 0)
            return;

        size--;
        if (position == 0) {
            head = head.next;
            return;
        }

        ListElement temp  = head;

        for (int i = 1; i < position; i++)
            temp = temp.next;

        temp.next = temp.next.next;

    }

    public void print() {
        ListElement temp = head;
        int i = 0;

        while (temp != null) {
            System.out.println("Pos: " + i + " value: " + temp.value);
            temp = temp.next;
            i++;
        }
    }

    private class ListElement {
        private int value;
        private ListElement next;

        ListElement(int value, ListElement next) {
            this.value = value;
            this.next = next;
        }
    }
}