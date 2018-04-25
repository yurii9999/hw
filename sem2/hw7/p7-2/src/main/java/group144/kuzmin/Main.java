package group144.kuzmin;

import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.howManyStartWithPrefix("string"));
    }
}
