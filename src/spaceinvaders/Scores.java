package spaceinvaders;

import java.util.Map;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Scores extends SubScene {

    private boolean isHidden = true;
    private final AnchorPane root = (AnchorPane) this.getRoot();
    private final Map<String, Integer> leaderboard = new Leaderboard().get();
    private static final double WIDTH = 560;
    private static final double HEIGHT = 440;

    public Scores() {
        super(new AnchorPane(), WIDTH, HEIGHT);
        root.getStylesheets().add("spaceinvaders/Scores.css");
        setLayoutX(1060);
        setLayoutY(100);

        // init titlelabel
        Label titleLabel = new Label("LEADERBOARD");
        titleLabel.setId("titleLabel");

        // init namesandscoresgp
        GridPane nameAndScoresGP = new GridPane();
        int counter = leaderboard.entrySet().size();

        for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) {
            counter--;
            Label name = new Label(entry.getKey());
            Label score = new Label(entry.getValue().toString());
            GridPane.setConstraints(name, 1, counter);
            GridPane.setConstraints(score, 2, counter);
            nameAndScoresGP.getChildren().addAll(name, score);
        }

        // init scrollpane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("sp");
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setPrefHeight(330);

        // set coordinates inside scrollpane
        nameAndScoresGP.setLayoutX(20);
        nameAndScoresGP.setLayoutY(0);

        // set coordinates inside root
        titleLabel.setLayoutX(20);
        titleLabel.setLayoutY(20);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(80);

        AnchorPane scrollPaneContent = new AnchorPane();
        scrollPaneContent.setId("spContent");
        scrollPaneContent.getChildren().add(nameAndScoresGP);
        scrollPane.setContent(scrollPaneContent);
        root.getChildren().addAll(scrollPane, titleLabel);

    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if (isHidden) {
            transition.setToX(-660);
            isHidden = false;
        } else {
            transition.setToX(660);
            isHidden = true;
        }
        transition.play();
    }
}
