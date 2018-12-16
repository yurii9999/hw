package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimpleBullet implements Bullet {
    private Double x;
    private Double y;
    private Double dx;
    private Double dy;

    private final Double radius = 20.0;

    private World world;

    public SimpleBullet(Double x, Double y, Double dx, Double dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Updates pos according inside state
     * @param time time form last update
     */
    @Override
    public void update(Double time) {
        x += dx * time;
        y += dy * time;
        dy += world.getG() * time;
    }

    /**
     * Draws bullet
     * @param gc graphicsContext where tank will be drawn
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(x, y, 5, 5);
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Checks is it exploded according position in the world
     * @return true if it is and false otherwise
     */
    @Override
    public boolean isExploded() {
        return y > world.getY(x);
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Double getRadius() {
        return radius;
    }
}
