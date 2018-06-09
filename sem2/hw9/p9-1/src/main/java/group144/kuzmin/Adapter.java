package group144.kuzmin;

public interface Adapter {
    boolean turn(int row, int column);
    String getLastTurnedPlayersName();
    String state();
    int[] opponentTurn();
}
