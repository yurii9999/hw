package group244.kuzmin;

public class Connection {
    private Computer computer1;
    private Computer computer2;

    public Connection(Computer computer1, Computer computer2) {
        this.computer1 = computer1;
        this.computer2 = computer2;
    }

    /**
     * Class explain pair of computers interaction
     * @return true if result of interaction is two infected computers and false otherwise
     */
    public boolean interact() {
        if (computer1.isInfected() && computer2.isInfected()) {
            return true;
        } else {
            if (computer1.isInfected()) {
                return computer2.infect();
            } else {
                if (computer2.isInfected()) {
                    return computer1.infect();
                } else {
                    return false;
                }
            }
        }
    }

    public Computer computer1() {
        return computer1;
    }

    public Computer computer2() {
        return computer2;
    }

    /**
     * Check is both computers in the connection infected
     * @return true if both of computers is infected
     */
    public boolean isInfected() {
        return computer1.isInfected() && computer2.isInfected();
    }

    @Override
    public String toString() {
        return "[" + computer1 + ", " + computer2 + "]";
    }
}
