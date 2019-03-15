package group244.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionTest {
    class zeroPercentInfector implements Infector {
        @Override
        public Double getProbability() {
            return 1.0;
        }
    }
    class hundredPercentInfector implements Infector {
        @Override
        public Double getProbability() {
            return 0.0;
        }
    }

    @Test
    public void infectionTest() {
        Computer c1 = new WindowsComputer(new zeroPercentInfector());
        c1.forceInfect();
        Computer c2 = new WindowsComputer(new hundredPercentInfector());
        Computer c3 = new WindowsComputer(new zeroPercentInfector());
        Computer c4 = new WindowsComputer(new hundredPercentInfector());
        Computer c5 = new WindowsComputer(new zeroPercentInfector());
//            2     4       5
//        1
//            3

        Connection co1 = new Connection(c1, c2);
        Connection co2 = new Connection(c1, c3);
        Connection co3 = new Connection(c2, c4);
        Connection co4 = new Connection(c4, c5);

        Network network = new Network();
        network.addConnection(co1);
        network.addConnection(co2);
        network.addConnection(co3);
        network.addConnection(co4);

        assertTrue(c1.isInfected());
        network.update();
        assertTrue(c2.isInfected());
        assertFalse(c3.isInfected());
        network.update();
        assertTrue(c4.isInfected());
        network.update();
        assertFalse(c5.isInfected());
    }
}