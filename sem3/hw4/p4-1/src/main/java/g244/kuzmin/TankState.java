package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

import static javafx.scene.paint.Color.*;

public class TankState {
    private int x;
    private int y;
    private Color color;
    private int width;
    private int height;
    private float angel;
    private int gunSize;

    /**
     * @return size[byte] that it need to keep information
     */
    public static int size() {
        return 4 + 4 + 1 + 4 + 4 + 4 + 4;
    }

    public TankState(int x, int y, Color color, int width, int height, float angel, int gunSize) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
        this.height = height;
        this.angel = angel;
        this.gunSize = gunSize;
    }

    /**
     * Decode itself using series of bytes
     * @param byteBuffer series of bytes
     */
    private TankState(ByteBuffer byteBuffer) {
        x = byteBuffer.getInt();
        y = byteBuffer.getInt();
        width = byteBuffer.getInt();
        height = byteBuffer.getInt();
//        angel = byteBuffer.getFloat();
        angel = byteBuffer.getFloat();
        gunSize = byteBuffer.getInt();
        byte colorByte = byteBuffer.get();
        if (colorByte == 0) {
            color = GREEN;
        } else {
            if (colorByte == 1) {
                color = RED;
            } else {
                if (colorByte == 2) {
                    color = BLUE;
                } else {
                    if (colorByte == 3) {
                        color = BLACK;
                    }
                }
            }
        }
    }

    public static TankState decode(byte[] bytes) {
        return new TankState(ByteBuffer.wrap(bytes));
    }

    public static TankState decode(ByteBuffer byteBuffer) {
        return new TankState(byteBuffer);
    }

    /**
     * Draws itself
     * @param gc graphic context where it could draws
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x - width / 2, y - height / 2, width, height);

        gc.setStroke(Color.RED);
        gc.strokeLine(x, y, x + Math.cos(angel) * gunSize, y + Math.sin(angel) * gunSize);
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
        byteBuffer.putInt(height);
        byteBuffer.putFloat(angel);
        byteBuffer.putInt(gunSize);
        byte colorByte = 0;
        if (color == GREEN) {
            colorByte = 0;
        } else {
            if (color == RED) {
                colorByte = 1;
            } else {
                if (color == BLUE) {
                    colorByte = 2;
                } else {
                    if (color == BLACK) {
                        colorByte = 3;
                    }
                }
            }
        }

        byteBuffer.put(colorByte);
        byteBuffer.flip();
        return byteBuffer;
    }
}
