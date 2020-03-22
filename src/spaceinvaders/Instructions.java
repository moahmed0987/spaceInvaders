package spaceinvaders;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Instructions extends SubScene {

    private static final double WIDTH = 560;
    private static final double HEIGHT = 440;
    private final AnchorPane root = (AnchorPane) this.getRoot();
    private boolean isHidden = true;

    public Instructions() {
        super(new AnchorPane(), WIDTH, HEIGHT);
        root.getStylesheets().add("spaceinvaders/Instructions.css");
        setLayoutX(1060);
        setLayoutY(100);

        // init titlelabel
        Label titleLabel = new Label("INSTRUCTIONS");
        titleLabel.setId("titleLabel");

        // init instructionslabel
        Label instructionsLabel = new Label("Use left and right arrow keys to move\n"
                + "Use up arrow key to shoot");

        root.getChildren().addAll(titleLabel, instructionsLabel);
        titleLabel.setLayoutX(20);
        titleLabel.setLayoutY(20);
        instructionsLabel.setLayoutX(20);
        instructionsLabel.setLayoutY(80);
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

    public boolean isHidden() {
        return isHidden;
    }

}
