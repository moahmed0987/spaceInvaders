package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class GameScreen extends Scene {

    private static final double WIDTH = 1060;
    private static final double HEIGHT = 700;
    private Image cannonImage;
    private double t;
    private AnchorPane root;
    private PlayerCannon playerCannon;
    private AnimationTimer timer;
    private int score;
    private String name;
    private static final Random randWidth = new Random((long) (WIDTH - 100));
    private static final Random randHeight = new Random((long) (HEIGHT - 100));
    private static Bounds BOUNDARIES;
    private boolean playerLeft, playerRight;
    private String cannonColour;
    private Label scoreLabel;

    public GameScreen(String name, String cannonColour) {
        super(new AnchorPane(), WIDTH, HEIGHT);
        this.name = name;
        this.cannonColour = cannonColour;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

        root = (AnchorPane) this.getRoot();
        root.getStylesheets().add("spaceinvaders/GameScreen.css");
        BOUNDARIES = root.getLayoutBounds();

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

        playerCannon = new PlayerCannon(cannonImage);
        scoreLabel = new Label("SCORE: " + score);
        scoreLabel.setLayoutX(0);
        scoreLabel.setLayoutY(0);
        root.getChildren().addAll(playerCannon, scoreLabel);
        System.out.println(WIDTH);
        System.out.println(playerCannon.getWidth());
        playerCannon.setTranslateX((WIDTH - playerCannon.getWidth()) / 2);
        playerCannon.setTranslateY(HEIGHT - 100);
        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    playerLeft = true;
                    break;
                case RIGHT:
                    playerRight = true;
                    break;
                case UP:
                    shoot(playerCannon);
                    break;
            }
        });
        this.setOnKeyReleased(e ->{
            switch(e.getCode()){
                case LEFT:
                    playerLeft = false;
                case RIGHT:
                    playerRight = false;
            }
        });
        newLevel();
    }

    private void newLevel() {
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 150);
            root.getChildren().add(e);
        }
    }

    private void shoot(Sprite s) {
        Bullet b = new Bullet(s.getTranslateX() + (s.getWidth() / 2), s.getTranslateY(), s.getType());
        root.getChildren().add(b);
    }

    private void update() {
        // increment timer
        t += 0.016;

        if (playerLeft) {
//            if (playerCannon.validMove("LEFT", playerCannon.getTranslateX(), playerCannon.getTranslateX() - 10)) {
//                playerCannon.setTranslateX(playerCannon.getTranslateX() - 10);
//            }
            playerCannon.moveLeft(10);
        }
        if (playerRight) {
//            if (playerCannon.validMove("RIGHT", playerCannon.getTranslateX(), playerCannon.getTranslateX() + 10)) {
//                playerCannon.setTranslateX(playerCannon.getTranslateX() + 10);
//            }
            playerCannon.moveRight(10);
        }

        // get sprites and add to lists
        List<Bullet> bullets = new ArrayList<>();
        List<Enemy> enemies = new ArrayList<>();
        for (Object o : root.getChildren()) {
            if (o instanceof Bullet) {
                bullets.add((Bullet) o);
            } else if (o instanceof Enemy) {
                enemies.add((Enemy) o);
            }
        }

        // check if bullets have collided with player or enemy
        bullets.forEach(bullet -> {
            switch (bullet.getType()) {
                case "enemyBullet":
                    bullet.moveDown();
                    if (bullet.getBoundsInParent().intersects(playerCannon.getBoundsInParent())) {
                        playerCannon.setHealth(playerCannon.getHealth() - 100);
//****************************************************************************************************************************************************************
                        bullet.setDead(true);
                    }
                    break;
                case "playerBullet":
                    bullet.moveUp();
                    enemies.forEach(enemy -> {
                        if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                            enemy.setDead(true);
                            enemy.die();
                            bullet.setDead(true);
                            score += 10;
                        }
                    });
                    break;
            }
        });

        // enemies shoot randomly
        enemies.forEach(enemy -> {
            if (t > 2) {
                if (Math.random() < 0.3) {
                    shoot(enemy);
                }
            }
        });

        // move enemies
        enemies.forEach(enemy -> {
            System.out.println("here");
            double r = Math.random();
            System.out.println(r);
            if (t > 2) {

                if (r < 0.25) { // 1/4 chance of left
                    moveEnemy("LEFT", enemy);
                } else if (r > 0.25 && r < 0.5) { // 1/4 chance of right
                    moveEnemy("RIGHT", enemy);
                } else if (r > 0.5) { // 1/2 chance of down
                    moveEnemy("DOWN", enemy);
                }
            }
        });

        // check if player is dead
        if (playerCannon.getHealth() <= 0) {
            playerCannon.setDead(true);
            timer.stop();
            showEndGame();
        }

        // remove dead enemies
        for (Enemy e : enemies) {
            if (e.isDead()) {
                root.getChildren().remove(e);
            }
        }
        
        // remove dead bullets
        for(Bullet b : bullets){
            if(b.isDead()){
                root.getChildren().remove(b);
            }
        }

        // newlevel if all enemies are dead
        if (enemies.isEmpty()) {
            newLevel();
        }

        // reset timer
        if (t > 2) {
            t = 0;
        }
        
        // update scorelabel
        scoreLabel.setText("SCORE: " + score);
    }

    private void showEndGame() {
        EndGame eg = new EndGame(name, score, cannonColour);
        eg.setLayoutX(265 + 1060);
        eg.setLayoutY(175);
        root.getChildren().addAll(eg);
        eg.moveSubScene();
    }

    private void moveEnemy(String direction, Enemy e) {
        System.out.println(direction);
        switch (direction) {
            case "LEFT": {
                int randX = (int) ((Math.random() * 500) / 5);
                System.out.println("randX = " + randX);
                e.setBoundaries(BOUNDARIES);
                e.moveLeft(randX);
                break;
            }
            case "RIGHT": {
                int randX = (int) ((Math.random() * 500) / 5);
                System.out.println("randX = " + randX);
                e.setBoundaries(BOUNDARIES);
                e.moveRight(randX);
                break;
            }
            case "DOWN": {
                int randY = (int) ((Math.random() * 500) / 5);
                System.out.println("randY = " + randY);
                e.setBoundaries(BOUNDARIES);
                e.moveDown(randY);
                break;
            }
        }
    }
}
