package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Enemy extends Sprite {

    // set private variables
    private boolean dead = false;

    // constructor that calls super (sprite) constructor
    public Enemy(double x, double y) {
        super(x, y, "enemy", getEnemyImage());
    }

    // load enemyimage
    private static Image getEnemyImage() {
        Image enemyImage = null;
        try {
            enemyImage = new Image(new FileInputStream("resources/enemy.png"), 100, 0, true, false);
            return enemyImage;
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return enemyImage;
    }

    // called when enemy is killed
    public void die() {
        // init fade transition
        FadeTransition removeFromScreen = new FadeTransition(Duration.seconds(0.25), this);
        // set opacity after transition to 0
        removeFromScreen.setToValue(0);
        // play transition
        removeFromScreen.play();
        // when transition is finished
        removeFromScreen.setOnFinished(e -> {
            // get parent anchorpane (root of gamescreen) 
            AnchorPane root = (AnchorPane) this.getParent();
            // remove this enemy from it
            try {
                root.getChildren().remove(this);
                // TODO bad programming practice, find better way to fix (is thrown when a bullet hits the enemy whilst it is fading away)
            } catch (NullPointerException npe) {
                System.out.println("Error: " + npe);
            }

        });

    }

    @Override
    public void moveDown(double newY) {
        if (validMove("DOWN", this.getTranslateY(), this.getTranslateY() + newY)) {
            TranslateTransition transitionDown = new TranslateTransition();
            transitionDown.setDuration(Duration.seconds(1));
            transitionDown.setToY(this.getTranslateY() + newY);
            transitionDown.setNode(this);
            transitionDown.play();
        }
    }

    public void moveX(double newX) {
        if (validMove("MOVEX", this.getTranslateX(), newX)) {
            TranslateTransition transitionXDirection = new TranslateTransition();
            transitionXDirection.setDuration(Duration.seconds(1));
            transitionXDirection.setToX(newX);
            transitionXDirection.setNode(this);
            transitionXDirection.play();
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
