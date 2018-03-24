package group144.kuzmin;

import java.util.Collection;
import java.util.Iterator;

public class Set<T extends Comparable<T>> implements Collection <T> {
    private Node<T> root;
    private int size;

    public Set() {
        root = new Node();
        size = 0;
    }

    /** @return amount elements in set */
    @Override
    public int size() {
        return size;
    }

    /** @return true if set is empty, false otherwise */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method check is element included in set
     *
     * @param o checking element
     * @return true if it included, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        return root.contain((T)o);
    }

    /** @return set's iterator */
    @Override
    public Iterator<T> iterator() {
        return new Node.NodeIterator(root);
    }

    /** @return array of set's elements */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Iterator iterator = new Node.NodeIterator(root);

        int i = 0;
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }

        return array;
    }

    /**
     * @param a array with correct size
     * @param <T> correct type
     * @return array of set's elements with correct type
     */
    @Override
    public <T> T[] toArray(T[] a) {
        Iterator iterator = new Node.NodeIterator(root);
        int i = 0;
        while (iterator.hasNext()) {
            a[i] = (T)iterator.next();
            i++;
        }

        return a;
    }

    /**
     * Method adds elements to set
     *
     * @param value element you want to add to set
     * @return true if element added, false if element already included
     */
    @Override
    public boolean add(T value) {
        if (root.add(value)) {
            size++;
            return true;
        }

        return false;
    }

    /**
     * Method remove elements from set
     *
     * @param value element you want to remove
     * @return true if element removed, false if set does not included this element
     */
    @Override
    public boolean remove(Object value) {
        if (root.remove((T)value)) {
            size--;
            return true;
        }

        return false;
    }

    /**
     * Method check is collection included in set
     *
     * @param c collection you want to check
     * @return true if collection fully included, false if there is element in collection that is no included in set
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator iterator = c.iterator();
        while(iterator.hasNext())
            if (!root.contain((T)iterator.next()))
                return false;

        return true;
    }

    /**
     * Method adds all elements from collection to set
     *
     * @param c collection that you want to add
     * @return true if at least one element from collection was included to set, false otherwise
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator iterator = c.iterator();
        boolean result = false;

        while (iterator.hasNext())
            result = root.add((T) iterator.next()) || result;

        return result;
    }

    /**
     * Method removes all collection's elements from set
     *
     * @param c collection you want to subtract
     * @return true if at least one element from collection was removed from set, false otherwise
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator iterator = c.iterator();
        boolean result = false;

        while (iterator.hasNext())
            result = root.remove((T)iterator.next()) || result;

        return result;
    }

    /**
     * Method keeps all set's elements which included in collection
     *
     * @param c collection you want to intersection with set
     * @return true if from set was deleted at least one element, false otherwise
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator iterator = iterator();
        boolean result = false;

        while (iterator.hasNext()) {
            Object current = iterator.next();
            if (!c.contains(current))
                result = root.remove((T)current) || result;
        }

        return result;
    }

    /** Method clears set */
    @Override
    public void clear() {
        root = new Node();
        size = 0;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
