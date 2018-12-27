package g244.kuzmin;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Read and write byteBuffers in socketChannel
 */
public class DataWriterReader {
    private SocketChannel socketChannel;

    public DataWriterReader(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public ByteBuffer read() throws IOException {
        return read(socketChannel);
    }

    public void write(ByteBuffer byteBuffer) throws IOException {
        write(socketChannel, byteBuffer);
    }

    public static ByteBuffer read(SocketChannel socketChannel) throws IOException {
        ByteBuffer headerBuffer = ByteBuffer.allocate(4);
        socketChannel.read(headerBuffer);
        headerBuffer.flip();

        int dataSize = headerBuffer.getInt();
        ByteBuffer data = ByteBuffer.allocate(dataSize);
        socketChannel.read(data);

        data.flip();
        return data;
    }

    public static void write(SocketChannel socketChannel, ByteBuffer data) throws IOException {
        ByteBuffer headerBuffer = ByteBuffer.allocate(4);
        headerBuffer.putInt(data.capacity());
        headerBuffer.flip();
        socketChannel.write(headerBuffer);
        socketChannel.write(data);
    }

}
