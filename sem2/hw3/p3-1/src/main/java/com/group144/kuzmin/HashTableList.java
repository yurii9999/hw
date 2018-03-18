package com.group144.kuzmin;

public class HashTableList extends List<Element> {

    /**
     * Method gives element by key
     *
     * @param key key of element
     * @return element that's key is equal given key
     * @throws NotFoundException if there is no element with given key in list
     */
    public Element getByKey(String key) throws NotFoundException {
        ListElement temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.value.compareTo(key) == 0)
                return temp.value;

            temp = temp.next;
        }

        throw new NotFoundException();
    }

    /**
     * Method check is there any element's with this key
     *
     * @param key given key
     * @return true if list includes and false otherwise
     */
    public boolean isIncluded(String key) {
        ListElement temp = head;

        for (int i = 0; i < size; i++)
            if (temp.value.compareTo(key) == 0)
                return true;

        return false;
    }

    /**
     * Method delete element from list by key
     *
     * @param key given key
     * @throws NotFoundException if there is no element whit given key in list
     */
    public void deleteByKey (String key) throws NotFoundException {
        if (head.value.compareTo(key) == 0) {
            head = head.next;
            size--;
            return;
        }

        ListElement temp = head;
        ListElement previous = null;

        for (int i = 1; i < size; i++) {
            if (temp.value.compareTo(key) == 0) {
                previous.next = previous.next.next;
                size--;
                return;
            }
            previous = temp;
            temp = temp.next;
        }

        throw new NotFoundException();
    }
}