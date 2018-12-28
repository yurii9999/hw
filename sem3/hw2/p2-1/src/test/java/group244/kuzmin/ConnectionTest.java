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
    public void infectedUnconnectedConnection() {
        Computer c1 = new Computer(1, "Windows");
        Computer c2 = new Computer(2, "Linux");
        Computer c3 = new Computer(2, "Linux");
        c3.forceInfect();
        Computer c4 = new Computer(2, "Windows");
        c4.forceInfect();

        Connection connection1 = new Connection(c1, c2);
        Connection connection2 = new Connection(c3, c4);

        Network network = new Network();
        network.addConnection(connection1);
        network.addConnection(connection2);

        network.update();
        network.update();
        network.update();
        network.update();
        network.update();

        assertFalse(c1.isInfected());
        assertFalse(c2.isInfected());
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