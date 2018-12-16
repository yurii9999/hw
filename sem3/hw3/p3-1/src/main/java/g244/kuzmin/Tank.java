package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface describes abstract tank in world, that could be updated, drawn, know about world where it lives,
 * can interact with bullets in this world and know is it dead
 */
public interface Tank {
    void update(Double time);
    void draw(GraphicsContext gc);
    void setWorld(World world);
    void interact(Bullet bullet);
    boolean isDead();
}
