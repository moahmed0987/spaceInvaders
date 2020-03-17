package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Enemy extends Sprite {

    private boolean dead = false;
    private final Enemy c = this;
    private double t;
    private AnimationTimer at;
    private boolean hidden = false;

    public Enemy(double x, double y) {
        super(x, y, "enemy", getEnemyImage());
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    private static Image getEnemyImage() {
        Image i = null;
        try {
            i = new Image(new FileInputStream("resources/enemy.png"), 100, 0, true, false);
            return i;
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return i;
    }

    public void die() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.25), this);
        ft.setToValue(0);
        ft.play();
        hidden = true;

    }

    @Override
    public void moveDown(double newY) {
        if (validMove("DOWN", this.getTranslateY(), this.getTranslateY() + newY)) {
            TranslateTransition x = new TranslateTransition();
            x.setDuration(Duration.seconds(1));
            x.setToY(this.getTranslateY() + newY);
            x.setNode(this);
            x.play();
        }
    }

    public void moveX(double newX) {
        if (validMove("MOVEX", this.getTranslateX(), newX)) {
            TranslateTransition x = new TranslateTransition();
            x.setDuration(Duration.seconds(1));
            x.setToX(newX);
            x.setNode(this);
            x.play();
        }
    }

    public boolean isHidden() {
        return hidden;
    }
}
