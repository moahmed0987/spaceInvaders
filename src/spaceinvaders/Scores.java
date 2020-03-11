package spaceinvaders;

import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.REMAINING;
import javafx.util.Duration;

public class Scores extends SubScene {

    private boolean isHidden = true;
    private AnchorPane root;

    public Scores(Map<String, Integer> scores) {
        super(new AnchorPane(), 560, 440);
        root = (AnchorPane) this.getRoot();
        root.getStylesheets().add("spaceinvaders/Scores.css");
        setLayoutX(1060);
        setLayoutY(100);

        // init titlelabel
        Label titleLabel = new Label("LEADERBOARD");
        titleLabel.setId("titleLabel");

        // init namesandscoresgp
        GridPane nameAndScoresGP = new GridPane();
        int counter = 0;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            counter++;
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

    private void textScroll(Label name) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(30000), name);
        tt.setFromX(-10);
        tt.setToX(260);
        tt.setCycleCount(Timeline.INDEFINITE);
        tt.setAutoReverse(false);
        tt.play();

        double sceneWidth = root.getWidth();
        double msgWidth = name.getLayoutBounds().getWidth();

        KeyValue initKeyValue = new KeyValue(name.translateXProperty(), sceneWidth);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

        KeyValue endKeyValue = new KeyValue(name.translateXProperty(), -1.0
                * msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(3), endKeyValue);

        Timeline timeline = new Timeline(initFrame, endFrame);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
