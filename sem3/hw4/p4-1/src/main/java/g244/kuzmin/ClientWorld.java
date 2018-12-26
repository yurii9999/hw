package g244.kuzmin;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Copy of real world, but withous internal state
 */
public class ClientWorld {
    private int height;
    private int width;

    private Collection<TankState> tanks;
    private Collection<BulletState> bullets;

    private List<Point2D> ground;

    public ClientWorld(ByteBuffer byteBuffer) {
        tanks = new LinkedList<>();
        bullets = new LinkedList<>();

        ground = new LinkedList<>();

        width = byteBuffer.getInt();
        height = byteBuffer.getInt();
        int amountPoints = byteBuffer.getInt();
        for (int i = 0; i < amountPoints; i++) {
            int x = byteBuffer.getInt();
            int y = byteBuffer.getInt();
            ground.add(new Point2D(x, y));
        }
    }

    /**
     * Draws itself
     * @param gc graphic context where it could draws
     */
    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, width, height);

        gc.setStroke(Color.BLACK);
        ground.stream().reduce((p1, p2) -> {gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY()); return p2;});

        for (TankState ts : tanks) {
            ts.draw(gc);
        }
        for (BulletState bs : bullets) {
            bs.draw(gc);
        }
    }

    /**
     * Updates itself according bytebuffer
     * @param byteBuffer real world's current state
     */
    public void update(ByteBuffer byteBuffer) {
        tanks.clear();
        bullets.clear();

        int amountTanks = byteBuffer.getInt();
        byte[] currentTank = new byte[TankState.size()];
        for (int i = 0; i < amountTanks; i++) {
            byteBuffer.get(currentTank);
            tanks.add(TankState.decode(currentTank));
        }

        int amountBullet = byteBuffer.getInt();
        byte[] currentBullet = new byte[BulletState.size()];
        for (int i = 0; i < amountBullet; i++) {
            byteBuffer.get(currentBullet);
            bullets.add(BulletState.decode(currentBullet));
        }
    }
}
