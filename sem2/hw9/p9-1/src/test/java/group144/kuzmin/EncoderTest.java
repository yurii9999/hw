package group144.kuzmin;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

public class EncoderTest {
    @Test
    public void shortEncodeTest() {
        ByteBuffer byteBuffer = Encoder.encodeShort(true, "X_WON");
        assertEquals(true, Encoder.decodeShort(byteBuffer));
        assertEquals("X_WON", Encoder.getStateShort(byteBuffer));
    }

    @Test
    public void clientTurnEncodeTest() {
        ByteBuffer byteBuffer = Encoder.clientTurn(2, 1);
        assertArrayEquals(new int[]{2, 1}, Encoder.decodeClientTurn(byteBuffer));
    }

    @Test
    public void serverTurnEncodeTest() {
        ByteBuffer byteBuffer = Encoder.encode(1, 0, "STANDOFF");
        assertEquals("STANDOFF", Encoder.getState(byteBuffer));
        assertArrayEquals(new int[]{1, 0}, Encoder.getServerTurn(byteBuffer));
    }
}