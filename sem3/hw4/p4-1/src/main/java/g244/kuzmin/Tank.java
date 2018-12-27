package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;

public interface Tank {
    /** Updating tank according internal state */
    void update(Double time);
    void draw(GraphicsContext gc);
    void setWorld(World world);
    /** Describing how tank interact with bullet */
    void interact(Bullet bullet);
    TankState getState();
    boolean isDead();
}
