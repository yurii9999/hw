package group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriorityQueueTest {

    @Test
    public void enqueueTest() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(3, 1);
        queue.enqueue(3, 5);
        queue.enqueue(3, 5);

        assertEquals("[3:5 3:5 3:1]", queue.toString());
    }

    @Test
    public void dequeueTest() throws EmptyQueueException {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(3, 5);
        queue.enqueue(5, 1);
        queue.enqueue(6, 9);

        Integer[] result = {queue.dequeue(), queue.dequeue(), queue.dequeue()};
        Integer[] correct = {6, 3, 5};
        assertArrayEquals(correct, result);
    }

    @Test
    public void isEmptyTrueTest() {
        PriorityQueue<Integer> emptyQueue = new PriorityQueue<>();
        assertTrue(emptyQueue.isEmpty());
    }

    @Test
    public void isEmptyFalseTest() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(3, 5);
        assertFalse(queue.isEmpty());
    }
}