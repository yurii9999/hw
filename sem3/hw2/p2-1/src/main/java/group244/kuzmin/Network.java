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

    /**
     * update all connection in the net
     */
    public void update() {
        for (Connection connection : connections) {
            connection.interact();
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

    /**
     * check are all computers in the net is infected
     * @return true if all are
     */
    public boolean isInfectedNetwork() {
        for (Connection connection : connections) {
            if (!connection.isInfected()) {
                return false;
            }
        }

        return true;
    }
}
