package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class GameScreen extends Scene {

    private static double WIDTH = 1060;
    private static double HEIGHT = 700;
    private Image cannonImage;
    private double t;
    private AnchorPane root;
    private PlayerCannon playerCannon;
    private AnimationTimer timer;
    private int score;
    private String name;

    public GameScreen(String name, String cannonColour) {
        super(new AnchorPane(), WIDTH, HEIGHT);
        this.name = name;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

        root = (AnchorPane) this.getRoot();
        root.getStylesheets().add("spaceinvaders/GameScreen.css");

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
        root.getChildren().add(playerCannon);
        playerCannon.setTranslateX(0);
        playerCannon.setTranslateY(HEIGHT - 100);
        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    playerCannon.setTranslateX(playerCannon.getTranslateX() - 10);
                    break;
                case RIGHT:
                    playerCannon.setTranslateX(playerCannon.getTranslateX() + 10);
                    break;
                case UP:
                    shoot(playerCannon);
                    break;
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
        Bullet b = new Bullet(s.getTranslateX() + (s.getFitWidth() / 2), s.getTranslateY(), s.getType());
        root.getChildren().add(b);
    }

    private void update() {
        // increment timer
        t += 0.016;

        // get sprites and add to lists
        List<Sprite> sprites = root.getChildren().stream().map(n -> (Sprite) n).collect(Collectors.toList());
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

        // check if player is dead
        if (playerCannon.getHealth() <= 0) {
            playerCannon.setDead(true);
            timer.stop();
            showEndGame();
        }

        // remove dead enemies
        for (Sprite s : sprites) {
            if (s.isDead()) {
                root.getChildren().remove(s);
            }
        }

        if (t > 2) {
            t = 0;
        }
    }

    private void showEndGame() {
        EndGame eg = new EndGame(name, score);
        eg.setLayoutX(265 + 1060);
        eg.setLayoutY(175);
        root.getChildren().addAll(eg);
        eg.moveSubScene();
    }
}
