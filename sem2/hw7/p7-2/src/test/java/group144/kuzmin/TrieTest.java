package group144.kuzmin;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void twiceAddTest() {
        Trie trie = new Trie();
        assertTrue(trie.add("string"));
        assertFalse(trie.add("string"));
    }

    @Test
    public void removeNotAddedTest() {
        Trie trie = new Trie();
        trie.add("string");
        trie.add("fkfsdkkfads");
        trie.add("stadfsfadsifasdi");

        assertFalse(trie.remove("stinfffffff"));
    }

    @Test
    public void removeFromEmptyTrieTest() {
        Trie emptyTrie = new Trie();
        assertFalse(emptyTrie.remove("st"));
    }

    @Test
    public void amountWordWithPrefixTest() {
        Trie trie = new Trie();
        trie.add("stop");
        trie.add("step");
        trie.add("sfffffff");
        trie.add("sanFierro");
        trie.add("string");

        assertEquals(3, trie.howManyStartWithPrefix("st"));
    }

    @Test
    public void containsTest() {
        Trie trie = new Trie();
        trie.add("long");
        trie.add("rrlogic");

        assertTrue(trie.contains("long"));
        assertFalse(trie.contains("lion"));
    }

    @Test
    public void sizeTest() {
        Trie trie = new Trie();
        trie.add("long string");
        trie.add("short string");
        trie.add("string");

        assertEquals(3, trie.size());
    }

    @Test
    public void serializeAndDeserializeTest() throws IOException, IncorrectStreamException, ClassNotFoundException {
        final String FILE_NAME = "test.txt";
        Trie trie = new Trie();

        trie.add("testString");


        ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        oo.writeObject(trie);

        ObjectInput oi = new ObjectInputStream(new FileInputStream(FILE_NAME));
        Trie trie2 = (Trie) oi.readObject();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        trie.serialize(oos);
        oos.close();

        trie = new Trie();
        assertFalse(trie.contains("testString"));

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        trie.deserialize(ois);

        assertTrue(trie.contains("testString"));
    }
}