package g244.kuzmin;

import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract bullet that lives in world, could be updated, drawn, know is it explided, has x, y and radius of explosion
 */
public interface Bullet {
    void update(Double time);
    void draw(GraphicsContext gc);
    void setWorld(World world);
    boolean isExploded();
    Double getX();
    Double getY();
    Double getRadius();
}
