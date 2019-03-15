package group244.kuzmin;

import java.util.Random;

public abstract class Computer {
    private boolean isInfected;
    private final Double probability = 0.0;
    private int number;
    private String os;
    private Network network;
    private Infector infector;

    public boolean isInfected() {
        return isInfected;
    }

    public void setInfector(Infector infector) {
        this.infector = infector;
    }

    public abstract Double getProbability();

    public void setNetwork(Network network) {
        this.network = network;
    }

    public boolean infect() {
        if (infector.getProbability() < getProbability()) {
            isInfected = true;
        }
        return isInfected;
    }

    public int number() {
        return number;
    }

    public void forceInfect() {
        isInfected = true;
    }

    public abstract String getOs();

    @Override
    public String toString() {
        return "os: " + getOs() + " is infected: " + isInfected();
    }
}
