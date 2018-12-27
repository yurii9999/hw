package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

import static javafx.scene.paint.Color.*;

public class BulletState {
    private int x;
    private int y;
    private Color color;
    private int width;

    /**
     * @return size[byte] that it need to keep information
     */
    public static int size() {
        return 4 + 4 + 4 + 1;
    }

    public BulletState(int x, int y, Color color, int width) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
    }

    /**
     * Decode itself using series of bytes
     * @param byteBuffer series of bytes
     */
    private BulletState(ByteBuffer byteBuffer) {
        x = byteBuffer.getInt();
        y = byteBuffer.getInt();
        width = byteBuffer.getInt();
        byte colorByte = byteBuffer.get();
        if (colorByte == 0) {
            color = GREEN;
        } else {
            if (colorByte == 1) {
                color = RED;
            } else {
                if (colorByte == 2) {
                    color = BLUE;
                }
            }
        }
    }

    public static BulletState decode(byte[] bytes) {return new BulletState(ByteBuffer.wrap(bytes));}
    public static BulletState decode(ByteBuffer byteBuffer) {return new BulletState(byteBuffer);}

    /**
     * Draws itself
     * @param gc graphic context where it could draws
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, width);
    }

    /**
     * Encode state in bytes series
     * @return series of bytes
     */
    public ByteBuffer encode() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(size());
        byteBuffer.putInt(x);
        byteBuffer.putInt(y);
        byteBuffer.putInt(width);
        byte colorByte = 0;
        if (color == GREEN) {
            colorByte = 0;
        } else {
            if (color == RED) {
                colorByte = 1;
            } else {
                if (color == BLUE) {
                    colorByte = 2;
                }
            }
        }

        byteBuffer.put(colorByte);
        byteBuffer.flip();
        return byteBuffer;
    }
}
