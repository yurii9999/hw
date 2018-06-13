package group144.kuzmin;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface Adapter {
    /**
     * Method makes turn and tell us about results, answer means "need i next opponent turn???"
     *
     * @param row first coordinate of pos
     * @param column second coordinate of pos
     * @return true if my turn was puted and i need wait for opponent's turn and
     * false if my turn was puted and game has end, or cell that you want to use is already full
     * @throws IOException throws when it's something wrong with SocketServerChannel - SocketChannel connection
     */
    boolean turn(int row, int column) throws IOException;

    /**
     * Method returns state of game, and that it don't throws anything means that game's state saves on both computers
     * @return [String] state
     */
    String state();

    /**
     * Method wait opponent turn
     *
     * @return coord's array {row, column}
     * @throws IOException throws when it's something wrong with SocketServerChannel - SocketChannel connection
     */
    int[] opponentTurn() throws IOException;
}
