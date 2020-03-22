package spaceinvaders;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HealthBar extends HBox {

    private static final double WIDTH = 200;
    private static final double HEIGHT = 30;
    private Rectangle healthRemainingBar, healthLostBar;
    private int remainingHealth, lostHealth;

    public HealthBar() {
        healthRemainingBar = new Rectangle(WIDTH, HEIGHT, Paint.valueOf("green"));
        healthLostBar = new Rectangle(0, HEIGHT, Paint.valueOf("black"));
        this.getChildren().addAll(healthRemainingBar, healthLostBar);
    }

    public void updateHealthBar(int newHealth) {
        this.remainingHealth = newHealth;
        this.lostHealth = 100 - newHealth;
        healthLostBar = new Rectangle(lostHealth * 2, HEIGHT, Paint.valueOf("black"));
        healthRemainingBar = new Rectangle(remainingHealth * 2, HEIGHT, Paint.valueOf("green"));

        this.getChildren().set(0, healthRemainingBar);
        this.getChildren().set(1, healthLostBar);
        if (remainingHealth <= 30) {
            healthLostBar.setFill(Paint.valueOf("red"));
        }
    }

}
