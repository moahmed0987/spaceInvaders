package spaceinvaders;

import javafx.scene.image.Image;

public class PlayerCannon extends Sprite {

    private int health = 100;

    public PlayerCannon(Image cannonImage) {
        super(40, 400, "player", cannonImage);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
