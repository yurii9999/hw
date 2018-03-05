package com.group144.kuzmin;

import java.lang.Exception;

public class HashTableList extends List<Element> {

    public Element getByKey(String key) throws NotFoundExcaption {
        ListElement<Element> temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.value.compareTo(key) == 0)
                return temp.value;

            temp = temp.next;
        }

        throw new NotFoundExcaption();
    }

    public boolean isIncluded(String key) {
        ListElement<Element> temp = head;

        for (int i = 0; i < size; i++)
            if (temp.value.compareTo(key) == 0)
                return true;

        return false;
    }

    public void deleteByKey (String key) throws NotFoundExcaption {
        if (head.value.compareTo(key) == 0) {
            head = head.next;
            size--;
            return;
        }

        ListElement<Element> temp = head;
        ListElement<Element> previous = null;

        for (int i = 1; i < size; i++) {
            if (temp.value.compareTo(key) == 0) {
                previous.next = previous.next.next;
                size--;
                return;
            }
            previous = temp;
            temp = temp.next;
        }

        throw new NotFoundExcaption();
    }
}

class NotFoundExcaption extends Exception {
    public NotFoundExcaption() {}
}
