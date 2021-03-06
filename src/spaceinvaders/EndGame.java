package spaceinvaders;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EndGame extends SubScene {

    private boolean isHidden = true;
    private AnchorPane root = (AnchorPane) this.getRoot();
    private static final double WIDTH = 530;
    private static final double HEIGHT = 350;

    public EndGame(String name, int score, String cannonColour) {
        super(new AnchorPane(), WIDTH, HEIGHT);
        root.getStylesheets().add("spaceinvaders/EndGame.css");

        Leaderboard lb = new Leaderboard();
        lb.add(name, score);

        // init gameover label
        Label gameOver = new Label("GAME OVER!");

        // init details label
        Label details = new Label("SCORE: " + score);
        details.setId("details");

        // init playagain button
        Button playAgain = new Button("PLAY AGAIN");
        playAgain.setOnAction(e -> {
            Scene scene = new GameScreen(name, cannonColour);
            Stage newStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            newStage.setScene(scene);
            newStage.show();
        });

        // init exitbutton
        Button exitButton = new Button("EXIT");
        exitButton.setOnAction(e -> {
            MainMenu scene = new MainMenu();
            Stage newStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            newStage.setScene(scene);
            newStage.show();
        });

        // set coordinates of components in root
        gameOver.setLayoutX(20);
        gameOver.setLayoutY(20);
        details.setLayoutX(20);
        details.setLayoutY(170);
        playAgain.setLayoutX(165);
        playAgain.setLayoutY(210);
        exitButton.setLayoutX(165);
        exitButton.setLayoutY(280);

        // add components to root
        root.getChildren().addAll(gameOver, details, playAgain, exitButton);

        // set layout of this subscene in gamescreen anchorpane
        this.setLayoutX(265 + 1060);
        this.setLayoutY(175);
    }

    // moves the subscreen when called
    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if (isHidden) {
            transition.setToX(-1060);
            isHidden = false;
        } else {
            transition.setToX(-1060);
            isHidden = true;
        }
        transition.play();
    }
}
