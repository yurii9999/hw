package group244.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionTest {
    @Test
    public void infectTrueTest() {
        Computer c1 = new Computer(1, "Windows");
        Computer c2 = new Computer(2, "Linux");
        c1.forceInfect();
        c2.forceInfect();

        Connection connection = new Connection(c1, c2);
        connection.interact();
        connection.interact();
        connection.interact();
        connection.interact();
        connection.interact();

        assertTrue(connection.isInfected());
    }

    @Test
    public void infectFalseTest() {
        Computer c1 = new Computer(1, "Windows");
        Computer c2 = new Computer(2, "Linux");

        Connection connection = new Connection(c1, c2);
        connection.interact();
        connection.interact();
        connection.interact();
        connection.interact();
        connection.interact();

        assertFalse(connection.isInfected());
    }
}