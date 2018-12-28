package group244.kuzmin;

import java.util.Random;

public class Computer {
    private boolean isInfected;
    private final Double probobility;
    private int number;
    private String os;
    private Network network;

    public Computer(int number, String os) {
        this.number = number;
        switch (os) {
            case "Windows":
                this.os = os;
                probobility = 0.3;
                break;
            case "Linux":
                this.os = os;
                probobility = 0.1;
                break;
                default:
                    probobility = 0.0;
                    this.os = os;
        }
    }

    public boolean isInfected() {
        return isInfected;
    }

    public Double getProbability() {
        return probobility;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    /**
     * Try to infect computer
     * @return true if computer is infected
     */
    public boolean infect() {
        Random random = new Random();
        if (random.nextDouble() < probobility) {
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

    @Override
    public String toString() {
        return "number: " + number + " os: " + os + " is infected: " + isInfected;
    }
}
