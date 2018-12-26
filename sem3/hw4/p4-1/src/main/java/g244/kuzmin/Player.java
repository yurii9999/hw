package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;

public class Player implements Tank {
    private World world;

    private Double x = 10.0;
    private Double y;

    private final Double width = 10.0;
    private final Double height = 10.0;

    private Double angel = - Math.PI / 2;
    private final int gunLength = 10;

    private Double da = 0.0;

    private Double dx = 1000.0;

    private long lastFire = System.nanoTime();

    private int bulletType = 1;

    private boolean isDead = false;

    private final Double maxAngel = - Math.PI / 4;
    private final Double minAngel = - 3 * Math.PI / 4;

    public Player(int x) {
        this.x = Double.valueOf(x);
    }

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

    /**
     * @return external state of tank
     */
    @Override
    public TankState getState() {
        return new TankState((int)Math.round(x), (int)Math.round(y), Color.BLACK, (int)Math.round(width), (int)Math.round(height), angel.floatValue(), (int)Math.round(gunLength));
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

        if (codes.contains("DIGIT1")) {
            if (bulletType != 1) {
                lastFire = System.nanoTime();
            }
            bulletType = 1;
        } else {
            if (codes.contains("DIGIT2")) {
                if (bulletType != 2) {
                    lastFire = System.nanoTime();
                }
                bulletType = 2;
            } else {
                if (codes.contains("DIGIT3")) {
                    if (bulletType != 3) {
                        lastFire = System.nanoTime();
                    }
                    bulletType = 3;
                }
            }
        }

        if (codes.contains("SPACE")) {
            fire();
        }
    }

    /**
     * Create bullet and fire it according current gun's position, angel and chosen type of bullet
     */
    private void fire() {
        if (isDead)
            return;

        if (System.nanoTime() - lastFire < 1000000000) {
            return;
        }

        int power = 5;
        Bullet bullet = BulletFactory.createBullet(x, y, Math.cos(angel) * power * world.getUnitAcceleration(), Math.sin(angel) * power * world.getUnitAcceleration(), bulletType);
        lastFire = System.nanoTime();
        world.add(bullet);
    }
}
