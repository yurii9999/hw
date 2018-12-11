package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;
/** Tank that just moving across world and don't harm anyone */
public class PeacefulEnemy implements Tank {
    private World world;
    private Double x;
    private Double dx;
    private Double y;

    private Double width = 10.0;
    private Double height = 10.0;
    private Color color;

    private boolean isDead = false;
    private long lastRandom;

    public PeacefulEnemy(Double x, Color color) {
        this.x = x;
        this.color = color;
        dx = 0.0;
        lastRandom = System.currentTimeMillis();
    }

    /**
     * Updates tanks pos and gun information according inside state
     * @param time time form last update
     */
    @Override
    public void update(Double time) {
        if ((x >= 300 && dx > 0) || (x <= 0 && dx < 0)) {
            Random random = new Random();
            dx = Double.valueOf(random.nextInt(3) - 1) * world.getUnitAcceleration();
        }

        x += dx * time;
        y = world.getY(x);

        if (System.currentTimeMillis() - lastRandom < 1000) {
            return;
        }

        lastRandom = System.currentTimeMillis();
        Random random = new Random();
        dx = Double.valueOf(random.nextInt(3) - 1) * world.getUnitAcceleration();
    }

    /**
     * Draws tank
     * @param gc graphicsContext where tank will be drawn
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Method describe how tank interact with bullet
     * @param bullet bullet that could interact with this tank
     */
    @Override
    public void interact(Bullet bullet) {
        if (Math.abs(bullet.getX() - x) + Math.abs(bullet.getY() - y) < bullet.getRadius()) {
            isDead = true;
        }
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}
