package group244.kuzmin;

import java.util.Random;

public abstract class Computer {
    private boolean isInfected;
    private final Double probability = 0.0;
    private int number;
    private String os;
    private Network network;

    public boolean isInfected() {
        return isInfected;
    }

    public abstract Double getProbability();

    public void setNetwork(Network network) {
        this.network = network;
    }

    public boolean infect() {
        Random random = new Random();
        if (random.nextDouble() < getProbability()) {
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
