package com.group144.kuzmin;

public class List<T> {
    private ListElement<T> head;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Method adds element to the list
     *
     * @param value - element which you want to add
     * @param position - position that you want to add it to
     */
    public void add(T value, int position) {
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

    /**
     * Method return list's element
     *
     * @param position - position of element you want to get
     * @return element on this position
     */
    public T peek(int position) {
        ListElement<T> temp = head;

        for (int i = 0; i < position; i++)
            temp = temp.next;

        return temp.value;
    }

    /**
     * Method delete list's elements
     *
     * @param position - position of element which you want to delete
     */
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

    /**
     * Method deletes elements from list
     *
     * @param element - method first element that is equal this
     */
    public void delete(T element) {
            if (!isIncluded(element))
                return;

        if (head.value.equals(element)) {
            head = head.next;
            size--;
            return;
        }

        ListElement<T> temp = head.next;
        ListElement<T> previous = head;

        for (int i = 1; i < size(); i++) {
            if (temp.value.equals(element)) {
                previous.next = previous.next.next;
                size--;
                return;
            }

            previous = temp;
            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        ListElement<T> temp = head;
        String result = "[";

        for (int i = 0; i < size; i++) {
            result += temp.value + ", ";
            temp = temp.next;
        }

        return result + "]";
    }

    /**
     * Method check is the element included in the list.
     *
     * @param element - element which you want to check
     * @return true if the element is included
     * @return false if the element is not included
     */
    public boolean isIncluded(T element) {
        ListElement<T> temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.value.equals(element))
                return true;

            temp = temp.next;
        }

        return false;
    }

    private class ListElement<T> {
        private T value;
        private ListElement next;

        ListElement(T data, ListElement next) {
            this.value = data;
            this.next = next;
        }
    }
}