package group144.kuzmin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server implements Adapter {
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private Game game;

    public Server(int port) throws IOException {
        game = new Game();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        socketChannel = serverSocketChannel.accept();
    }

    @Override
    public boolean turn(int row, int column) throws IOException {
        if (game.turn(row, column)) {
            ByteBuffer byteBuffer = Encoder.encode(row, column, game.state());
            socketChannel.write(byteBuffer);
            return game.state().equals("PLAYING");
        }

        return false;
    }

    @Override
    public String state() {
        return game.state();
    }

    @Override
    public int[] opponentTurn() throws IOException {
        boolean turned = false;
        int[] coords = {0, 0};
        while (!turned) {
            ByteBuffer clientTurn = ByteBuffer.allocate(Encoder.CLIENT_TURN_LENGTH);
            socketChannel.read(clientTurn);
            coords = Encoder.decodeClientTurn(clientTurn);
            turned = game.turn(coords[0], coords[1]);
            ByteBuffer answer = Encoder.encodeShort(turned, game.state());
            socketChannel.write(answer);
        }

        return coords;
    }
}
