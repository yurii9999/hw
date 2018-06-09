package group144.kuzmin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client implements Adapter {
    SocketChannel socketChannel;

    public Client(int port) throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(port));
    }

    @Override
    public boolean turn(int row, int column) {
        return false;
    }

    @Override
    public String getLastTurnedPlayersName() {
        return null;
    }

    @Override
    public String state() {
        return null;
    }

    @Override
    public int[] opponentTurn() {
        return new int[0];
    }

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8888));
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.put(new byte[] {1, 8, 19, 1});
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }
}
