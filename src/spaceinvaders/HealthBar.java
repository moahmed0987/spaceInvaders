package spaceinvaders;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HealthBar extends HBox {

    private Rectangle remaining, lost;
    private int remainingHealth, lostHealth;

    public HealthBar() {
        remaining = new Rectangle(200, 30, Paint.valueOf("green"));
        lost = new Rectangle(0, 30, Paint.valueOf("black"));
        this.getChildren().addAll(remaining, lost);
    }

    public void updateHealthBar(int newHealth) {
        this.remainingHealth = newHealth;
        this.lostHealth = 100 - newHealth;
        lost = new Rectangle(lostHealth * 2, 30, Paint.valueOf("black"));
        remaining = new Rectangle(remainingHealth * 2, 30, Paint.valueOf("green"));
        
        this.getChildren().set(0, remaining);
        this.getChildren().set(1, lost);
        if (remainingHealth <= 30) {
            lost.setFill(Paint.valueOf("red"));
        }
    }

}
