package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class Bullet extends Sprite {

    public Bullet(double x, double y, String type) {
        super(x, y, type + "Bullet", getBulletImage());
    }

    private static Image getBulletImage() {
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/bullet.png"), 2, 0, true, false);
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
