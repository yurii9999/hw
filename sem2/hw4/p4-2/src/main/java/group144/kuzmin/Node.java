package group144.kuzmin;

import java.util.Iterator;

import static sun.swing.MenuItemLayoutHelper.max;

public class Node<T extends Comparable<T>> {
    private Node right;
    private Node left;
    private T value;
    private int height;

    private Node(T value) {
        this.value = value;
        right = new Node();
        left = new Node();
        height = 0;
    }

    private Node(T value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
        updateHight();
    }

    /** Tail of node - guardian in end of all nodes */
    public Node() {
        right = null;
        left = null;
        value = null;
        height = -1;
    }

    /**
     * Method converts node to string of elements
     *
     * @return String "[lowest element, .. , biggest element]"
     */
    @Override
    public String toString() {
        String result = toBadString();
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    private String toBadString() {
        if (isTail())
            return "";

        StringBuffer result = new StringBuffer(left.toBadString());
        result.append(value + ", " + right.toBadString());

        return result.toString();
    }

    /**
     * Method shows elements with relationship
     *
     * @return (parent leftChild rightChild) and null if it's tail
     */
    public String toDebugString() {
        if (isTail())
            return "null";

        StringBuffer result = new StringBuffer("(" + value + ":" + height + " ");
        result.append(left.toString() + " ");
        result.append(right.toString() + ")");

        return result.toString();
    }

    /**
     * Method adds element to Node recurrently & safe balance
     *
     * @param value element which you want to add
     * @return true if element added, and false if Node already include element that is equals this
     */
    public boolean add(T value) {
        if (isTail()) {
            this.value = value;
            left = new Node();
            right = new Node();
            height = 0;

            return true;
        }

        int compare = value.compareTo(this.value);
        if (compare == 0)
            return false;

        if (compare > 0) {
            boolean result = right.add(value);
            balance();

            return result;
        }

        boolean result = left.add(value);
        balance();

        return result;
    }

    /**
     * Method removes elements from node recurrently & safe balance
     *
     * @param value element which you want to remove
     * @return true if element deleted, false if element is not included in this node
     */
    public boolean remove(T value) {
        if (isTail())
            return false;

        int compare = value.compareTo(this.value);
        if (compare == 0) {
            if (left.isTail() && right.isTail()) {
                becomeTail();
                return true;
            }

            if (left.isTail()) {
                become(right);
                balance();
                return true;
            }

            if (right.isTail()) {
                become(left);
                balance();
                return true;
            }

            Node leftMax = left.cutMax();
            leftMax.setChildren(left, right);
            become(leftMax);
            balance();

            return true;
        }

        if (compare > 0) {
            boolean result = right.remove(value);
            balance();
            return result;
        }

        boolean result = left.remove(value);
        balance();
        return result;
    }

    /**
     * Method checks is element included in this node recurrently
     *
     * @param value element's value which you want to check
     * @return true if node include element with this value, false if is not
     */
    public boolean contain(T value) {
        if (isTail())
            return false;

        int compare = value.compareTo(this.value);
        if (compare == 0)
            return true;
        if (compare > 0)
            return right.contain(value);

        return left.contain(value);
    }

    private boolean isTail() {
        return value == null;
    }

    private void becomeTail() {
        value = null;
        right = null;
        left = null;
        height = -1;
    }

    private void become(Node node) {
        value = (T) node.value;
        left = node.left;
        right = node.right;
        height = node.height;
    }

    /**
     * Method cuts max element from node.
     * It's needed to work remove()
     *
     * @return cut node
     */
    private Node cutMax() {
        if (!right.isTail())
            return right.cutMax();

        Node result = new Node(value, left, right);
        become(left);

        return result;
    }

    private void setChildren(Node left, Node right) {
        this.left = left;
        this.right = right;
        updateHight();
    }

    private void updateHight() {
        height = max(left.height, right.height) + 1;
    }

    private int balanceFactor() {
        return right.height - left.height;
    }

    /** Methods change relationships. They are needed to work balance() */
    private void rotateRight() {
        Node temp = new Node();
        temp.become(left);
        left.become(temp.right);
        temp.right.become(this);

        temp.right.updateHight();
        updateHight();

        become(temp);
    }

    private void rotateLeft() {
        Node temp = new Node();
        temp.become(right);
        right.become(temp.left);
        temp.left.become(this);

        temp.left.updateHight();
        updateHight();

        become(temp);
    }

    /** Method keep up balance of node */
    private void balance() {
        updateHight();

        int balanceFactor = balanceFactor();
        if (balanceFactor == 2) {
            if (right.balanceFactor() < 0)
                right.rotateRight();

            rotateLeft();
            return;
        }

        if (balanceFactor == -2) {
            if (left.balanceFactor() > 0)
                left.rotateLeft();

            rotateRight();
        }
    }

    public static class NodeIterator<T extends Comparable<T>> implements java.util.Iterator<T> {
        private Node root;
        private Node current;

        NodeIterator(Node node) {
            root = node;

            current = node;
            while (!current.left.isTail())
                current = current.left;
        }

        /**
         * Method checks is iterator on the end of collection
         * @return true if it has any more elements, and false if it has not
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Method gets next collection's element and move pointer forward
         * @return current collection's element
         */
        @Override
        public T next() {
            if (current == null)
                return null;

            T result = (T)current.value;

            if (!current.right.isTail()) {
                current = current.right;

                while (!current.left.isTail())
                    current = current.left;

                return result;
            }

            Node relative = getRelative(current);

            while (relative.right == current) {
                current = relative;
                relative = getRelative(current);

                if (relative == null) {
                    current = null;
                    return result;
                }
            }

            current = relative;
            return result;
        }

        /**
         * Method searches node which is parent of these
         *
         * @param node child of searching node
         * @return parent of current node
         */
        private Node getRelative(Node node) {
            Node temp = root;
            Node previous = null;

            T value = (T)node.value;
            int compare = value.compareTo((T)temp.value);
            while (compare != 0) {
                previous = temp;
                if (compare > 0)
                    temp = temp.right;
                else
                    temp = temp.left;

                compare = value.compareTo((T)temp.value);
            }

            return previous;
        }
    }

    public static void main(String[] args) {
        Node a = new Node();
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(88);
        a.add(2);
        a.add(0);
        a.add(33);


        System.out.println(a);
        Iterator<Integer> b = new NodeIterator(a);


        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
    }
}
