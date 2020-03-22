package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class Bullet extends Sprite {

    private static final String BULLET_IMAGE_PATH = "resources/bullet.png";

    public Bullet(double x, double y, String type) {
        super(x, y, type + "Bullet", getBulletImage());
    }

    private static Image getBulletImage() {
        Image image = null;
        try {
            image = new Image(new FileInputStream(BULLET_IMAGE_PATH), 2, 0, true, false);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return image;
    }

    public void moveUp() {
        this.setTranslateY(this.getTranslateY() - 5);
    }

    public void moveDown() {
        this.setTranslateY(this.getTranslateY() + 5);
    }
}
