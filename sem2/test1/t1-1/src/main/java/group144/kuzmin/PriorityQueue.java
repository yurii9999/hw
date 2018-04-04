package group144.kuzmin;

public class PriorityQueue<T> {
    private Node root;

    public PriorityQueue() {
        root = null;
    }

    /**
     * Method adds elements in queue
     *
     * @param value value that M adds in queue
     * @param priority priority, place that M adds element to
     */
    public void enqueue(T value, int priority) {
        if (isEmpty()) {
            this.root = new Node(value, priority, null);
            return;
        }

        if (root.priority < priority) {
            root = new Node(value, priority, root);
            return;
        }

        Node previous = null;
        Node temp = root;
        while (temp!= null && temp.priority >= priority) {
            previous = temp;
            temp = temp.next;
        }

        previous.next = new Node(value, priority, previous.next);
    }

    /**
     * Methods pop first (with biggest priority) element
     *
     * @return value that stays first in queue
     * @throws EmptyQueueException throws when queue is empty
     */
    public T dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();

        T result = root.value;
        root = root.next;

        return result;
    }

    /**
     * Method checks is queue empty
     *
     * @return true if it is, false if it is not
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Method converts queue to string
     *
     * @return [el1:p1 el2:p2 ..]
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "[ ]";

        StringBuffer result = new StringBuffer("[" + root.value + ":" + root.priority);
        Node temp = root;
        while (temp.next != null) {
            temp = temp.next;
            result.append(" " + temp.value + ":" + temp.priority);
        }

        return result.toString() + "]";
    }

    public int a() {
        return root.priority;
    }


    private class Node {
        private T value;
        private int priority;
        private Node next;

        public Node(T value, int priority, Node next) {
            this.value = value;
            this.priority = priority;
            this.next = next;
        }
    }
}
