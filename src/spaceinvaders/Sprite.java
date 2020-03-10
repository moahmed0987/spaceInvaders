package spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {

    private String type;
    private boolean dead;

    public Sprite(double x, double y, String type, Image image) {
        super(image);
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.type = type;
        this.dead = false;
    }

    public void moveLeft() {
        this.setTranslateX(this.getTranslateX() - 5);
    }

    public void moveRight() {
        this.setTranslateX(this.getTranslateX() + 5);
    }

    public void moveUp() {
        this.setTranslateY(this.getTranslateY() - 5);
    }

    public void moveDown() {
        this.setTranslateY(this.getTranslateY() + 5);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
