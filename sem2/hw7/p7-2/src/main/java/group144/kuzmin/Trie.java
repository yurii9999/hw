package group144.kuzmin;

import java.io.*;

public class Trie implements Serializable {
    private Node root;
    private int size;

    public Trie() {
        root = new Node();
    }

    /**
     * Method adds new string in trie
     *
     * @param string string that method should add
     * @return true if this string added
     * @return false if this string already is included
     */
    public boolean add(String string) {
        boolean result = root.add(new Parser(string));
        if (result)
            size++;

        return result;
    }

    /**
     * Method checks is string included in this trie
     *
     * @param string string that method should to check
     * @return true if string is included in this trie and false otherwise
     */
    public boolean contains(String string) {
        return root.contains(new Parser(string));
    }

    /**
     * Method removes strings from trie
     *
     * @param string string that method should to remove
     * @return true if string was included and is deleted now
     * @return false if string was not included
     */
    public boolean remove(String string) {
        boolean result = root.remove(new Parser(string));
        if (result)
            size--;

        return result;
    }

    /** @return amount words in trie */
    public int size() {return size;}

    /**
     * Method calculate how many string start with Prefix
     *
     * @param prefix prefix with words start
     * @return amount these words
     */
    public int howManyStartWithPrefix(String prefix) {
        return root.howManyStartWithPrefix(new Parser(prefix));
    }

    /**
     * Serialize trie on stream
     *
     * @param oos stream you want to serialize trie on
     * @throws IOException "Any exception thrown by the underlying
     *          "upd: Object"OutputStream."
     */
    public void serialize(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this);
        oos.flush();
    }

    /**
     * build trie by input stream
     *
     * @param in input stream
     * @throws IOException when st wrong with ObjectInputStream
     * @throws IncorrectStreamException when you give him stream of another serialized types
     */
    public void deserialize(ObjectInputStream in) throws IncorrectStreamException, IOException {
        Trie temp = null;
        try {
            temp = (Trie) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new IncorrectStreamException();
        }

        size = temp.size;
        root = temp.root;
    }


    private class Node implements Serializable {
        private final static int ALPHABET = 256;
        private int amountWords;
        private boolean isTerminal;
        private Node[] next;

        public Node() {
            amountWords = 0;
            isTerminal = false;
            next = new Node[ALPHABET];
        }

        public boolean add(Parser parser) {
            if (parser.hasNext()) {
                int nextChar = parser.next();

                if (next[nextChar] == null)
                    next[nextChar] = new Node();

                boolean result = next[nextChar].add(parser);
                if (result)
                    amountWords++;

                return result;
            }

            if (isTerminal)
                return false;

            amountWords++;
            isTerminal = true;
            return true;
        }

        public boolean contains(Parser parser) {
            if(parser.hasNext()) {
                char nextChar = parser.next();
                if (next[nextChar] == null)
                    return false;

                return next[nextChar].contains(parser);
            }

            if (isTerminal)
                return true;

            return false;
        }

        public boolean remove(Parser parser) {
            if (parser.hasNext()) {
                char nextChar = parser.next();
                if (next[nextChar] == null)
                    return false;

                boolean result = next[nextChar].remove(parser);
                if (result)
                    amountWords--;

                if (next[nextChar].isTail())
                    next[nextChar] = null;

                return result;
            }

            if (!isTerminal)
                return false;

            isTerminal = false;
            amountWords--;
            return true;
        }

        public int howManyStartWithPrefix(Parser parser) {
            if (parser.hasNext()) {
                char nextChar = parser.next();
                if (next[nextChar] == null)
                    return 0;

                return next[nextChar].howManyStartWithPrefix(parser);
            }

            return amountWords;
        }

        private boolean isTail() {return amountWords == 0;}
    }

    private class Parser {
        private char[] string;
        private int i;

        public Parser(String string) {
            this.string = string.toCharArray();
            i = 0;
        }

        public boolean hasNext() {return i + 1 <= string.length;}
        public char next() {return string[i++];}
        public char current() {return string[i];}
    }
}
