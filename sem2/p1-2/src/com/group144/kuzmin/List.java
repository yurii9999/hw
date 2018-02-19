package com.group144.kuzmin;

public class List {
    private ListElement head;

    public boolean isEmpty() {

        return this.head == null;
    }

    public int size() {
        ListElement temp = this.head;
        int result = 0;

        while (temp != null) {
            result++;
            temp = temp.next;
        }

        return result;
    }

    public void add(int value, int position) {
        if (position == 0) {
            this.head = new ListElement(value, this.head);
            return;
        }

        ListElement temp = this.head;

        for (int i = 1; i < position; i++)
            temp = temp.next;

        temp.next = new ListElement(value, temp.next);
    }

    public int peek(int position) {
        ListElement temp = this.head;

        for (int i = 0; i < position; i++)
            temp = temp.next;

        return temp.value;
    }

    public void delete(int position) {
        if (position == 0) {
            this.head = this.head.next;
            return;
        }

        ListElement temp  = this.head;

        for (int i = 1; i < position; i++)
            temp = temp.next;

        temp.next = temp.next.next;
    }

    public void print() {
        ListElement temp = this.head;
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