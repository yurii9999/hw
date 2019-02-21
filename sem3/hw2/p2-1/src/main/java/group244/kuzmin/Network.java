package group244.kuzmin;

import java.util.LinkedList;
import java.util.List;

public class Network {
    private List<Connection> connections;

    public Network() {
        connections = new LinkedList<>();
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public void update() {
        List<Computer> infectedInThisTime = new LinkedList<>();

        for (Connection connection : connections) {
            Computer c1 = connection.computer1();
            Computer c2 = connection.computer2();
            if (!infectedInThisTime.contains(c1) && !infectedInThisTime.contains(c2)) {
                boolean before1 = c1.isInfected();
                boolean before2 = c2.isInfected();
                connection.interact();
                boolean after1 = c1.isInfected();
                boolean after2 = c2.isInfected();

                if (!before1 && after1) { infectedInThisTime.add(c1);}
                if (!before2 && after2) { infectedInThisTime.add(c2);}
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Connection connection : connections) {
            stringBuffer.append('\n');
            stringBuffer.append(connection);
        }

        return stringBuffer.toString();
    }

    public boolean isInfectedNetwork() {
        for (Connection connection : connections) {
            if (!connection.isInfected()) {
                return false;
            }
        }

        return true;
    }
}
