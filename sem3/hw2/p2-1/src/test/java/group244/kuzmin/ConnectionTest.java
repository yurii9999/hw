package group244.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionTest {
    class Computer100 extends Computer {
        @Override
        public Double getProbability() {
            return 1.0;
        }

        @Override
        public String getOs() {
            return "100% Computer";
        }
    }

    class Computer0 extends Computer {
        @Override
        public Double getProbability() {
            return 0.0;
        }

        @Override
        public String getOs() {
            return "0% Computer";
        }
    }

    @Test
    public void interactionTest() {
        /*
        верхний зараженный
                    1
                1       1
            1               0
         1     0
      1
         */
        Computer c1 = new Computer100();
        c1.forceInfect();
        Computer c2 = new Computer100();
        Computer c3 = new Computer100();
        Computer c4 = new Computer100();
        Computer c5 = new Computer0();
        Computer c6 = new Computer100();
        Computer c7 = new Computer0();
        Computer c8 = new Computer100();

        Connection co1 = new Connection(c1, c2);
        Connection co2 = new Connection(c1, c3);
        Connection co3 = new Connection(c2, c4);
        Connection co4 = new Connection(c3, c5);
        Connection co5 = new Connection(c4, c6);
        Connection co6 = new Connection(c4, c7);
        Connection co7 = new Connection(c6, c8);

        Network network = new Network();
        network.addConnection(co1);
        network.addConnection(co2);
        network.addConnection(co3);
        network.addConnection(co4);
        network.addConnection(co5);
        network.addConnection(co6);
        network.addConnection(co7);

        // 0:
        assertTrue(c1.isInfected());
        assertFalse(c2.isInfected());
        assertFalse(c3.isInfected());
        assertFalse(c4.isInfected());
        assertFalse(c5.isInfected());
        assertFalse(c6.isInfected());
        assertFalse(c7.isInfected());
        assertFalse(c8.isInfected());

        network.update();
        // 1:
        assertTrue(c1.isInfected());
        assertTrue(c2.isInfected());
        assertTrue(c3.isInfected());
        assertFalse(c4.isInfected());
        assertFalse(c5.isInfected());
        assertFalse(c6.isInfected());
        assertFalse(c7.isInfected());
        assertFalse(c8.isInfected());

        network.update();
        // 2:
        assertTrue(c1.isInfected());
        assertTrue(c2.isInfected());
        assertTrue(c3.isInfected());
        assertTrue(c4.isInfected());
        assertFalse(c5.isInfected());
        assertFalse(c6.isInfected());
        assertFalse(c7.isInfected());
        assertFalse(c8.isInfected());

        network.update();
        // 3:
        assertTrue(c1.isInfected());
        assertTrue(c2.isInfected());
        assertTrue(c3.isInfected());
        assertTrue(c4.isInfected());
        assertFalse(c5.isInfected());
        assertTrue(c6.isInfected());
        assertFalse(c7.isInfected());
        assertFalse(c8.isInfected());

        network.update();
        // 4:
        assertTrue(c1.isInfected());
        assertTrue(c2.isInfected());
        assertTrue(c3.isInfected());
        assertTrue(c4.isInfected());
        assertFalse(c5.isInfected());
        assertTrue(c6.isInfected());
        assertFalse(c7.isInfected());
        assertTrue(c8.isInfected());
    }
}