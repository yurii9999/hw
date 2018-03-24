package group144.kuzmin;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class SetTest {
    Collection<Integer> a = new Set();
    @Before
    public void prepare() {
        a.add(3);
        a.add(5);
        a.add(2);
        a.add(7);
        a.add(4);
        a.add(10);
        a.add(1);
        a.add(6);
        a.add(3);
        a.add(0);
    }

    @Test
    public void sizeTest() {
        assertEquals(9, a.size());
    }

    @Test
    public void isEmptyFalseTest() {
        assertFalse(a.isEmpty());
    }

    @Test
    public void containsFalseTest() {
        assertFalse(a.contains(100));
    }

    @Test
    public void containsTrueTest() {
        assertTrue(a.contains(10));
    }

    @Test
    public void iterator() {
        Iterator<Integer> iterator = a.iterator();
        Integer[] array = new Integer[9];

        int i = 0;
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }

        Integer[] right = {0, 1, 2, 3, 4, 5, 6, 7, 10};
        assertArrayEquals(right, array);
    }

    @Test
    public void toArrayTest() {
        Object[] array = a.toArray();
        Object[] right = {0, 1, 2, 3, 4, 5, 6, 7, 10};
        assertArrayEquals(right, array);
    }

    @Test
    public void toArray2Test() {
        Integer[] array = new Integer[9];
        array = a.toArray(array);
        Integer[] right = {0, 1, 2, 3, 4, 5, 6, 7, 10};
        assertArrayEquals(right, array);
    }

    @Test
    public void addTrueTest() {
        assertTrue(a.add(1000));
    }

    @Test
    public void addFalseTest() {
        assertFalse(a.add(3));
    }

    @Test
    public void removeTrueTest() {
        assertTrue(a.remove(0));
    }

    @Test
    public void removeFalseTest() {
        assertFalse(a.remove(111));
    }

    Collection<Integer> b = new LinkedList();
    @Before
    public void prepareList() {
        b.add(3);
        b.add(0);
        b.add(7);
    }


    @Test
    public void containsAllTrueTest() {
        assertTrue(a.containsAll(b));
    }

    @Test
    public void containsAllFalseTest() {
        b.add(100);
        assertFalse(a.containsAll(b));
    }

    @Test
    public void addAll() {
        assertFalse(a.addAll(b));
    }

    @Test
    public void removeAll() {
        assertTrue(a.removeAll(b));
    }

    @Test
    public void retainAll() {
        assertTrue(a.retainAll(b));
    }

    @Test
    public void clear() {
        a.clear();
        assertTrue(a.isEmpty());
    }

    @Test
    public void toStringTest() {
        String right = "[0, 1, 2, 3, 4, 5, 6, 7, 10]";
        assertEquals(a.toString(), right);
    }
}