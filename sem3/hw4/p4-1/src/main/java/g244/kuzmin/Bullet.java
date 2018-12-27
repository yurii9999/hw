package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Bullet flys in the sky and falls down
 * Have a lot of parameters for tune
 */
public class Bullet {
    private Double x;
    private Double y;
    private Double dx;
    private Double dy;

    private final Double radius;

    private final Color color;
    private final Double width;

    private final Double mass;

    private World world;

    public Bullet(Double x, Double y, Double dx, Double dy, Color color, Double width, Double radius, Double mass) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
        this.width = width;
        this.radius = radius;
        this.mass = mass;
    }

    /**
     * Update bullet's state according internal data
     * @param time time from last updating
     */
    public void update(Double time) {
        x += dx * time;
        y += dy * time;
        dy += world.getG() * time * mass;
    }

    /**
     * Draws itself
     * @param gc graphic context where it could draws
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, width);
    }

    /**
     * Set the world where it lives
     * @param world
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Check is bullet already exploded
     * @return true if it was
     */
    public boolean isExploded() {
        return y > world.getY(x);
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getRadius() {
        return radius;
    }

    /**
     * Method gives external bullet's parameters
     * @return bulletState, it could be useful to draw copy of this bullet
     */
    public BulletState getState() {
        return new BulletState((int)Math.round(x), (int)Math.round(y), color, (int)Math.round(width));
    }
}
