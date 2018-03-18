package com.group144.kuzmin;

public class List<T> {
    protected ListElement head;
    protected int size;

    /**
     * M check is list empty
     *
     * @return false if list is empty, true if list is not empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * M give list's size
     *
     * @return list's size
     */
    public int size() {
        return size;
    }

    /**
     * M adds elements to list
     *
     * @param value element's value
     * @param position position that element will be added
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
     * Method peek list's elements
     *
     * @param position position from you want to get element
     * @return element from position
     */
    public T peek(int position) {
        ListElement temp = head;

        for (int i = 0; i < position; i++)
            temp = temp.next;

        return temp.value;
    }


    /**
     * Mehod deletes list's elements
     *
     * @param position position from you want delete element
     * @return
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
     * Method converts list to string
     *
     * @return [el0 el1 ..]
     */

    @Override
    public String toString() {
        if (isEmpty())
            return "[ ]";

        StringBuffer result = new StringBuffer("[" + head.value.toString());

        ListElement temp = head.next;
        for (int i = 1; i < size; i++) {
            result.append(" " + temp.value);
            temp = temp.next;
        }

        return result.append("]").toString();
    }

    protected class ListElement {
        protected T value;
        protected ListElement next;

        ListElement(T data, ListElement next) {
            this.value = data;
            this.next = next;
        }
    }
}