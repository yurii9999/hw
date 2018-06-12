package group144.kuzmin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client implements Adapter {
    SocketChannel socketChannel;
    String state;

    public Client(int port) throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(port));
    }

    @Override
    public boolean turn(int row, int column) throws IOException {
        ByteBuffer clientTurn = Encoder.clientTurn(row, column);
        socketChannel.write(clientTurn);
        ByteBuffer answer = ByteBuffer.allocate(Encoder.SHORT_LENGTH);
        socketChannel.read(answer);
        state = Encoder.getStateShort(answer);
        // Special situation: my turn is right and i game ends, and i dont need opponent's turn; then false means that where will not one more turn from opponent
        if (!state.equals("PLAYING") && Encoder.decodeShort(answer) == true)
            return false;

        return Encoder.decodeShort(answer);
    }

    @Override
    public String state() {
        return state;
    }

    @Override
    public synchronized int[] opponentTurn() throws IOException {
        ByteBuffer serverTurn = ByteBuffer.allocate(Encoder.SERVER_TURN_LENGTH);
        System.out.println("Client: reading");
        socketChannel.read(serverTurn);
        state = Encoder.getState(serverTurn);
        return Encoder.getServerTurn(serverTurn);
    }
}
