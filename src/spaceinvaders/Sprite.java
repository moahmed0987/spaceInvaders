package spaceinvaders;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {

    private String type;
    private boolean dead;
    private double width, height;
    private Bounds BOUNDARIES;

    public Sprite(double x, double y, String type, Image image) {
        super(image);
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.type = type;
        this.dead = false;
    }

    public void moveLeft(double x) {
        if (validMove("LEFT", this.getTranslateX(), this.getTranslateX() - x)) {
            this.setTranslateX(this.getTranslateX() - x);
        }
    }

    public void moveRight(double x) {
        if (validMove("RIGHT", this.getTranslateX(), this.getTranslateX() + x)) {
            this.setTranslateX(this.getTranslateX() + x);
        }
    }

    public void moveUp(double y) {
        if (validMove("UP", this.getTranslateY(), this.getTranslateY() - y)) {
            this.setTranslateY(this.getTranslateY() - y);
        }
    }

    public void moveDown(double y) {
        if (validMove("DOWN", this.getTranslateY(), this.getTranslateY() + y)) {
            this.setTranslateY(this.getTranslateY() + y);
        }
    }

    public boolean validMove(String direction, double currentPos, double nextPos) {
        if (this.type.equals("enemyBullet") || this.type.equals("playerBullet")) {
            return true;
        } else {
            switch (direction) {
                case "LEFT": {
                    return nextPos < currentPos && nextPos >= 0;
                }
                case "RIGHT": {
                    return nextPos > currentPos && nextPos <= (1060 - this.width);
                }
                case "UP": {
                    return nextPos < currentPos && nextPos >= 0;
                }
                case "DOWN": {
                    return nextPos > currentPos && nextPos <= (700 - this.height);
                }
            }
        }
        return false;
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

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setBoundaries(Bounds BOUNDARIES) {
        this.BOUNDARIES = BOUNDARIES;
        System.out.println("BOUNDARIES set: " + BOUNDARIES);
    }
}
