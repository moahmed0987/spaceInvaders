package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GameScreen extends Scene {

    private static final double WIDTH = 1060;
    private static final double HEIGHT = 700;
    private Image cannonImage;
    private double t;
    private AnchorPane root;
    private PlayerCannon playerCannon;
    private AnimationTimer gameTimer;
    private int score;
    private String playerName;
    private boolean playerToMoveLeft, playerToMoveRight;
    private String cannonColour;
    private final Label scoreLabel, healthLabel;
    private HealthBar healthBar = new HealthBar();

    // contructor for a new gamescreen
    public GameScreen(String playerName, String cannonColour) {

        // call to super class
        super(new AnchorPane(), WIDTH, HEIGHT);

        // init private variables
        this.playerName = playerName;
        this.cannonColour = cannonColour;

        // create gametimer
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGameFrame();
            }
        };

        // start timer
        gameTimer.start();

        // get this gamescene's layout and assign it to a private variable
        root = (AnchorPane) this.getRoot();
        root.getStylesheets().add("spaceinvaders/GameScreen.css");

        // get the image of the player's chosen colour cannon
        try {
            switch (cannonColour) {
                case "BLACK": {
                    cannonImage = new Image(new FileInputStream("resources/black space invader.png"), 0, 100, true, false);
                    break;
                }
                case "BLUE": {
                    cannonImage = new Image(new FileInputStream("resources/blue space invader.png"), 0, 100, true, false);
                    break;
                }
                case "PINK": {
                    cannonImage = new Image(new FileInputStream("resources/pink space invader.png"), 0, 100, true, false);
                    break;
                }
                case "YELLOW": {
                    cannonImage = new Image(new FileInputStream("resources/yellow space invader.png"), 0, 100, true, false);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }

        // create the playercannon passing the image of it
        playerCannon = new PlayerCannon(cannonImage);

        // init scorelabel
        scoreLabel = new Label();

        // init healthlabel
        healthLabel = new Label("HEALTH: " + playerCannon.getHealth());

        // init deathbar ???
        Rectangle deathBar = new Rectangle(WIDTH, 25);
        deathBar.setLayoutX(0);
        deathBar.setLayoutY(HEIGHT - 150);
        deathBar.setFill(Paint.valueOf("white"));

        // add components to root
        root.getChildren().addAll(playerCannon, scoreLabel, healthBar, healthLabel, deathBar);

        // set the position of each component
        AnchorPane.setTopAnchor(healthLabel, Double.valueOf(20));
        AnchorPane.setRightAnchor(healthLabel, Double.valueOf(30));
        AnchorPane.setTopAnchor(healthBar, Double.valueOf(20));
        AnchorPane.setRightAnchor(healthBar, Double.valueOf(20));
        AnchorPane.setTopAnchor(scoreLabel, Double.valueOf(10));
        AnchorPane.setLeftAnchor(scoreLabel, Double.valueOf(10));

        // set player's first position to the middle of the screen
        playerCannon.setTranslateX((WIDTH - playerCannon.getWidth()) / 2);
        playerCannon.setTranslateY(HEIGHT - 100);

        // when left or right keys are pressed, set private variables to true (used in updateGameTimer() method for smoother movements)
        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    playerToMoveLeft = true;
                    break;
                case RIGHT:
                    playerToMoveRight = true;
                    break;
                case UP:
                    shoot(playerCannon);
                    break;
            }
        });

        // set private variables to false when the keys are released
        this.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case LEFT:
                    playerToMoveLeft = false;
                case RIGHT:
                    playerToMoveRight = false;
            }
        });
        // create a new level
        newLevel();
    }

    // new level method, adds five new enemies to the screen
    private void newLevel() {
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 150);
            root.getChildren().add(e);
        }
    }

    // shoot method, creates a new bullet at the top center of the given sprite
    private void shoot(Sprite shootingSprite) {
        Bullet b = new Bullet(shootingSprite.getTranslateX() + (shootingSprite.getWidth() / 2), shootingSprite.getTranslateY(), shootingSprite.getType());
        root.getChildren().add(b);
    }

    // update method for gametimer
    private void updateGameFrame() {
        // increment timer
        t += 0.016;

        // moves playercannon left if variables are true
        if (playerToMoveLeft) {
            playerCannon.moveLeft(10);
        }
        // moves playercannon right if variables are true
        if (playerToMoveRight) {
            playerCannon.moveRight(10);
        }

        // adds sprites currently on screen to lists for reference
        List<Bullet> allBullets = new ArrayList<>();
        List<Enemy> allEnemies = new ArrayList<>();
        for (Object spriteOnScreen : root.getChildren()) {
            if (spriteOnScreen instanceof Bullet) {
                allBullets.add((Bullet) spriteOnScreen);
            } else if (spriteOnScreen instanceof Enemy) {
                allEnemies.add((Enemy) spriteOnScreen);
            }
        }

        // checks if bullets have collided with player or enemy
        allBullets.forEach(bullet -> {
            switch (bullet.getType()) {
                case "enemyBullet":
                    // move enemybullet down in all circumstances
                    bullet.moveDown();
                    // if enemybullet has hit playercannon 
                    if (bullet.getBoundsInParent().intersects(playerCannon.getBoundsInParent())) {
                        // decrement playercannon.health by 10
                        playerCannon.setHealth(playerCannon.getHealth() - 10);
                        // set bullet to dead as it has been used up
                        bullet.setDead(true);
                        // update healthlabel with new health
                        healthLabel.setText("HEALTH: " + playerCannon.getHealth());
                        // update healthbar with new health
                        healthBar.updateHealthBar(playerCannon.getHealth());
                    }
                    break;
                case "playerBullet":
                    // move playerbullet up in all circumstances
                    bullet.moveUp();
                    // check if playerbullet has hit any enemies
                    allEnemies.forEach(enemy -> {
                        if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                            // set enemy to dead as it has been killed
                            enemy.setDead(true);
                            // make enemy disappear
                            enemy.die();
                            // set bullet to dead as it has been used up
                            bullet.setDead(true);
                            // increase the user's score
                            score += 10;
                            // update scorelabel
                            scoreLabel.setText("SCORE: " + score);
                        }
                    });
                    break;
            }
        });

        // enemies shoot randomly
        allEnemies.forEach(enemy -> {
            if (t > 2 && !enemy.isHidden()) {
                if (Math.random() < 0.3) {
                    shoot(enemy);
                }
            }
        });

        // move enemies
        allEnemies.forEach(enemy -> {
            double r = Math.random();
            if (t > 2) {
                if (r < 0.5) { // 1/4 chance of left
                    moveEnemy("MOVEX", enemy);
//                } else if (r > 0.25 && r < 0.5) { // 1/4 chance of right
//                    moveEnemy("RIGHT", enemy);
                } else if (r > 0.5) { // 1/2 chance of down
                    moveEnemy("DOWN", enemy);
                }
            }
        });

        // check if player is dead
        if (playerCannon.getHealth() <= 0) {
            playerCannon.setDead(true);
            gameTimer.stop();
            showEndGame();
        }

        // remove dead enemies
        for (Enemy e : allEnemies) {
            if (e.isDead()) {
                root.getChildren().remove(e);
            }
        }

        // remove dead bullets
        for (Bullet b : allBullets) {
            if (b.isDead()) {
                root.getChildren().remove(b);
            }
        }

        // newlevel if all enemies are dead
        if (allEnemies.isEmpty()) {
            newLevel();
        }

        // reset timer
        if (t > 2) {
            t = 0;
        }
    }

    private void showEndGame() {
        EndGame eg = new EndGame(playerName, score, cannonColour);
        eg.setLayoutX(265 + 1060);
        eg.setLayoutY(175);
        root.getChildren().addAll(eg);
        eg.moveSubScene();
    }

    private void moveEnemy(String direction, Enemy e) {
        switch (direction) {
            case "MOVEX": {
                double newEnemyX = e.getTranslateX() + ((playerCannon.getTranslateX() - e.getTranslateX()) * 0.5);
                e.moveX(newEnemyX);
                break;
            }
            case "DOWN": {
                int randY = (int) ((Math.random() * 500) / 5);
                e.moveDown(randY);
                break;
            }
        }
    }

}
