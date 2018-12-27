package g244.kuzmin;

import javafx.scene.paint.Color;

public class BulletFactory {
    /**
     * Bullet has a lot of tunes, bullet factory can create bullets wit different parameters and say that it is a different bullets
     * @param x x coord
     * @param y y coord
     * @param dx acceleration along x
     * @param dy acceleration along y
     * @param type factory knows any sets of tunes, and if it doesn't know this tune's number, it return 'default' bullet
     * @return bullet with given parameters
     */
    public static Bullet createBullet(Double x, Double y, Double dx, Double dy, int type) {
        switch (type) {
            case 1:
                return new Bullet(x, y, dx, dy, Color.GREEN, 5.0, 20.0, 1.0);
            case 3:
                return new Bullet(x, y, dx, dy, Color.RED, 7.0, 40.0, 1.5);
            default:
                return new Bullet(x, y, dx, dy, Color.BLUE, 6.0, 30.0, 1.25);
        }
    }
}
