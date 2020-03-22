package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Enemy extends Sprite {

    // set variables
    public static final double WIDTH = 100;
    private boolean removed = false;

    // constructor that calls super (sprite) constructor
    public Enemy(double x, double y) {
        super(x, y, "enemy", getEnemyImage());
    }

    // load enemyimage
    private static Image getEnemyImage() {
        Image enemyImage = null;
        try {
            enemyImage = new Image(new FileInputStream("resources/enemy.png"), WIDTH, 0, true, false);
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
            // avoids nullpointerexception from repeated bullets
            if (!this.removed) {
                // remove this enemy from it
                root.getChildren().remove(this);
                // set removed from screen to true
                this.removed = true;
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

    @Override
    public boolean validMove(String direction, double currentPos, double nextPos) {
        switch (direction) {
            case "MOVEX": {
                return nextPos > 0 && nextPos < 1060;
            }
            case "LEFT": {
                return nextPos < currentPos && nextPos >= 0;
            }
            case "RIGHT": {
                return nextPos > currentPos && nextPos <= (1060 - this.getWidth());
            }
            case "DOWN": {
                return nextPos > currentPos && nextPos <= (550 - this.getHeight());
            }
        }

        return false;
    }
}
