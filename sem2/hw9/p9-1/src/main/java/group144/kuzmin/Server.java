package group144.kuzmin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server implements Adapter {
    ServerSocketChannel serverSocketChannel;
    SocketChannel socketChannel;
    private Game game;

    public Server(int port) throws IOException {
        game = new Game();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        socketChannel = serverSocketChannel.accept();
    }

    @Override
    public boolean turn(int row, int column) {
        return game.turn(row, column);
    }

    @Override
    public String getLastTurnedPlayersName() {
        return game.getLastTurnedPlayersName();
    }

    @Override
    public String state() {
        return game.state();
    }

    @Override
    public int[] opponentTurn() {
        return new int[0];
    }


    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println("Server's buffer: " + byteBuffer.get(0) + " "  + byteBuffer.get(1) + " " + byteBuffer.get(2) + " " + byteBuffer.get(3));
    }
}
