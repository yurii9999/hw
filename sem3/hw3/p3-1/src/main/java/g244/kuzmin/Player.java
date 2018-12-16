package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;

public class Player implements Tank {
    private World world;

    /** Pos in world information */
    private Double x = 0.0;
    private Double y;
    private Double dx = 1000.0;


    /** Visual information */
    private final Double width = 10.0;
    private final Double height = 10.0;
    private final int gunLength = 10;

    /** Gun information */
    private Double angel = - Math.PI / 2;
    private Double da = 0.0;

    private final Double maxAngel = - Math.PI / 4;
    private final Double minAngel = - 3 * Math.PI / 4;

    /** When if fire last time & is it dead now */
    private long lastFire = System.nanoTime();
    private boolean isDead = false;


    public Player() {
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
        if (isDead)
            return;

        if (Math.abs(bullet.getX() - x) + Math.abs(bullet.getY() - y) < bullet.getRadius()) {
            isDead = true;
        }
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    /**
     * Updates tanks pos and gun information according inside state
     * @param time time form last update
     */
    @Override
    public void update(Double time) {
        if (isDead)
            return;

        if (!((dx > 0 && x > world.getRightBound()) || (dx < 0 && x < world.getLeftBound()))) {
            x += dx * time;
        }
        y = world.getY(x);

        if (!((da > 0 && angel > maxAngel) || (da < 0 && angel < minAngel))) {
            angel += da * time;
        }
    }

    /**
     * Draws tank
     * @param gc graphicsContext where tank will be drawn
     */
    @Override
    public void draw(GraphicsContext gc) {
        if (isDead)
            return;

        gc.setFill(Color.BLACK);
        gc.fillRect(x - width / 2, y - height / 2, width, height);

        gc.setStroke(Color.RED);
        gc.strokeLine(x, y, x + Math.cos(angel) * gunLength, y + Math.sin(angel) * gunLength);
    }

    /**
     * Updates tank's sate according pressed buttons
     * @param codes collection of pressed buttons
     */
    public void control(Collection<String> codes) {
        if (isDead)
            return;

        if (codes.contains("LEFT")) {
            dx = -2 * world.getUnitAcceleration();
        } else {
            if (codes.contains("RIGHT")) {
                dx = 2 * world.getUnitAcceleration();
            } else {
                dx = 0 * world.getUnitAcceleration();
            }
        }

        if (codes.contains("UP")) {
            da = -4.0;
        } else {
            if (codes.contains("DOWN")) {
                da = 4.0;
            } else {
                da = 0.0;
            }
        }

        if (codes.contains("SPACE")) {
            fire();
        }
    }

    /**
     * Create bullet and fire it according current gun's position and angel
     */
    private void fire() {
        if (isDead)
            return;

        if (System.nanoTime() - lastFire < 1000000000) {
            return;
        }

        int power = 5;
        SimpleBullet bullet = new SimpleBullet(x, y, Math.cos(angel) * power * world.getUnitAcceleration(), Math.sin(angel) * power * world.getUnitAcceleration());
        lastFire = System.nanoTime();
        world.add(bullet);
    }
}
