package g244.kuzmin;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {
    private Collection<Tank> tanks;
    private Collection<Bullet> bullets;

    private final Double leftBound;
    private final Double rightBound;

    private final int width;
    private final int height;

    private List<Point2D> floor;

    private final Double unitAcceleration = 50.0;

    public Double getUnitAcceleration() {
        return unitAcceleration;
    }

    public Double getG() {
        return 10 * getUnitAcceleration();
    }

    public Double getLeftBound() {
        return leftBound;
    }

    public Double getRightBound() {
        return rightBound;
    }


    public World(int width, int height) {
        this.width = width;
        this.height = height;

        this.leftBound = 0.0;
        this.rightBound = Double.valueOf(width);
        floor = new LinkedList<>();
        floor.add(new Point2D(0, 250));
        floor.add(new Point2D(30, 200));
        floor.add(new Point2D(70, 270));
        floor.add(new Point2D(100, 250));
        floor.add(new Point2D(150, 290));
        floor.add(new Point2D(200, 200));
        floor.add(new Point2D(width, 300));

        tanks = new CopyOnWriteArrayList<>();
        bullets = new CopyOnWriteArrayList<>();
    }

    /** Adds new tank in the world that will be update and draw */
    public void add(Tank tank) {
        tanks.add(tank);
        tank.setWorld(this);
    }

    /** Adds new bullet in the world that will be update and draw */
    public void add(Bullet bullet) {
        bullets.add(bullet);
        bullet.setWorld(this);
    }

    /**
     * Updates all entities and removes died
     * @param time time from last update
     */
    public void update(Double time) {
        for (Bullet bullet: bullets) {
            bullet.update(time);
            if (bullet.isExploded()) {
                for (Tank tank: tanks) {
                    tank.interact(bullet);
                }

                bullets.remove(bullet);
            }
        }

        for (Tank tank: tanks) {
            tank.update(time);
            if (tank.isDead()) {
                tanks.remove(tank);
            }
        }
    }

    /**
     * Draws ground and all objects(bullets and tanks)
     * @param gc graphicsContext where tank will be drawn
     */
    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, width, height);

        gc.setStroke(Color.BLACK);
        floor.stream().reduce((p1, p2) -> {gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY()); return p2;});

        for (Tank tank: tanks) {
            tank.draw(gc);
        }

        for (Bullet bullet: bullets) {
            bullet.draw(gc);
        }
    }

    /**
     * We have 1d world and 2d frame
     * second world's coord on frame we can get by first
     *
     * @param x first coord
     * @return second coord
     */
    public Double getY(Double x) {
        Iterator<Point2D> i = floor.iterator();
        Point2D cur = i.next();
        Point2D prev = cur;
        while (i.hasNext() && cur.getX() < x) {
            prev = cur;
            cur = i.next();
        }

        // y = kx + b -> k = (y1 - y2) / (x1 - x2), b = y1 -k * x1
        Double k = (cur.getY() - prev.getY()) / (cur.getX() - prev.getX());
        Double b = cur.getY() - k * cur.getX();
        // -> y = kx + b
        return k * x + b;
    }
}
