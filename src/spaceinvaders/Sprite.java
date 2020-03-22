package spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {

    private String type;
    private boolean dead = false;
    private final double widthOfSprite, heightOfSprite;

    public Sprite(double x, double y, String type, Image image) {
        super(image);
        this.widthOfSprite = image.getWidth();
        this.heightOfSprite = image.getHeight();
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.type = type;
        this.dead = false;
    }

    public void moveLeft(double newX) {
        if (validMove("LEFT", this.getTranslateX(), this.getTranslateX() - newX)) {
            this.setTranslateX(this.getTranslateX() - newX);
        }
    }

    public void moveRight(double newX) {
        if (validMove("RIGHT", this.getTranslateX(), this.getTranslateX() + newX)) {
            this.setTranslateX(this.getTranslateX() + newX);
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
                case "MOVEX": {
                    return nextPos > 0 && nextPos < 1060;
                }
                case "LEFT": {
                    return nextPos < currentPos && nextPos >= 0;
                }
                case "RIGHT": {
                    return nextPos > currentPos && nextPos <= (1060 - this.widthOfSprite);
                }
                case "UP": {
                    return nextPos < currentPos && nextPos >= 0;
                }
                case "DOWN": {
                    return nextPos > currentPos && nextPos <= (700 - this.heightOfSprite);
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
        return this.widthOfSprite;
    }

    public double getHeight() {
        return this.heightOfSprite;
    }
}
