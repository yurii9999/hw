package group244.kuzmin;

public class Main {
    public static void main(String[] args) {
        // пытаемся исполнить такое: https://imgur.com/a/FANo2lm
        Computer computer1 = new Computer(1, "Windows");
        Computer computer2 = new Computer(2, "Windows");
        Computer computer3 = new Computer(3, "Windows");
        Computer computer4 = new Computer(4, "Linux");
        Computer computer5 = new Computer(5, "Linux");
        Computer computer6 = new Computer(6, "Windows");

        computer3.forceInfect();

        Connection connection1 = new Connection(computer1, computer6);
        Connection connection2 = new Connection(computer1, computer2);
        Connection connection3 = new Connection(computer1, computer4);
        Connection connection4 = new Connection(computer3, computer6);
        Connection connection5 = new Connection(computer4, computer5);
        Connection connection6 = new Connection(computer5, computer6);

        Network network = new Network();
        network.addConnection(connection1);
        network.addConnection(connection2);
        network.addConnection(connection3);
        network.addConnection(connection4);
        network.addConnection(connection5);
        network.addConnection(connection6);

        System.out.println(network);
        System.out.println("=========================================");
        while (!network.isInfectedNetwork()) {
            network.update();
            System.out.println(network);
            System.out.println("=========================================");
        }
    }
}
