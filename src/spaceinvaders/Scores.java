package spaceinvaders;

import java.util.Map;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Scores extends SubScene {

    private boolean isHidden = true;
    private AnchorPane root;

    public Scores(Map<String, Integer> scores) {
        super(new AnchorPane(), 560, 440);
        root = (AnchorPane) this.getRoot();
        setLayoutX(1060);
        setLayoutY(100);
        
        // init titlelabel
        Label titleLabel = new Label("SCORES");
        int counter = 0;
        // init scoresLabels
        for(Map.Entry<String, Integer> entry : scores.entrySet()){
            counter++;
            Label name = new Label(entry.getKey());
            Label score = new Label(entry.getValue().toString());
            name.setLayoutX(20);
            name.setLayoutY(20*counter);
            score.setLayoutX(100);
            score.setLayoutY(20*counter);
            root.getChildren().addAll(name,score);
        }
        titleLabel.setLayoutX(20);
        titleLabel.setLayoutY(20);
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
