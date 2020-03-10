package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class Enemy extends Sprite {

    private boolean dead = false;

    public Enemy(double x, double y) {
        super(x, y, "enemy", enemyImage());
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    private static Image enemyImage() {
        Image i = null;
        try {
            i = new Image(new FileInputStream("resources/enemy.png"), 100, 0, true, false);
            return i;
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return i;
    }

}
