package spaceinvaders;

import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NameInputter extends SubScene {

    private AnchorPane root;
    private boolean isHidden = true;
    private TextField nameTF;
    private String cannonColour;
    private Button nextButton;
    private boolean nextButtonShowing = false;

    public NameInputter(String cannonColour) {
        super(new AnchorPane(), 560, 440);
        root = (AnchorPane) this.getRoot();
        this.cannonColour = cannonColour;
        AnchorPane root = (AnchorPane) this.getRoot();
        root.getStylesheets().add("spaceinvaders/NameInputter.css");
        setLayoutX(1060);
        setLayoutY(100);

        // init enter name label
        Label enterNameLabel = new Label("ENTER YOUR NAME");

        // init back button
        Button backButton = new Button("BACK");
        backButton.setOnAction(e -> handleBackRequest());

        // init name textfield
        nameTF = new TextField();
//        nameTF.setWrapText(true);
        nameTF.setFont(Font.font("Lucida Console"));
        nameTF.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                ke.consume();
            }
        });
        nameTF.setOnKeyPressed((KeyEvent ke) -> {
            if (nameTF.getText().length() == 1 && ke.getCode().equals(KeyCode.BACK_SPACE)) {
                hideNextButton();
            }
            if (!ke.getCode().equals(KeyCode.BACK_SPACE) && !ke.getCode().equals(KeyCode.ENTER) && !nextButtonShowing) {
                showNextButton();
            }
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if (nextButtonShowing) {
                    handleNextButtonRequest(ke);
                }
            }
        });
        // add components to anchorpane
        root.getChildren().addAll(enterNameLabel, backButton, nameTF);

        // set coordinates for components
        enterNameLabel.setLayoutX(20);
        enterNameLabel.setLayoutY(70);
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        nameTF.setLayoutX(20);
        nameTF.setLayoutY(250);

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
        return this.isHidden;
    }

    private void handleBackRequest() {
        this.moveSubScene();
    }

    private void showNextButton() {
        nextButtonShowing = true;
        nextButton = new Button("NEXT");
        nextButton.setId("nextButton");
        nextButton.setOnAction(e -> handleNextButtonRequest(e));
        AnchorPane root = (AnchorPane) this.getRoot();
        root.getChildren().add(nextButton);
        nextButton.setLayoutX(340);
        nextButton.setLayoutY(370);
    }

private void hideNextButton() {
        if (!nextButtonShowing) {
            return;
        }
        Button buttonToRemove = null;
        for (Node node : root.getChildren()) {
            if (node.getId() != null) {
                if (node.getId().equals("nextButton")) {
                    buttonToRemove = (Button) node;
                }
            }
        }
        root.getChildren().remove(buttonToRemove);
        nextButtonShowing = false;
    }

    private void handleNextButtonRequest(Event e) {
        Scene scene = new GameScreen(nameTF.getText(), cannonColour);
        Stage newStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        newStage.setScene(scene);
        newStage.show();
    }
}
