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
    private Button nextButton;
    private ErrorLabel errorLabel;
    private boolean nextButtonShowing = false;
    public static String name = "";

    public NameInputter() {
        super(new AnchorPane(), 560, 440);
        root = (AnchorPane) this.getRoot();
        root.getStylesheets().add("spaceinvaders/NameInputter.css");
        setLayoutX(1060);
        setLayoutY(100);

        // init enter name label
        Label enterNameLabel = new Label("ENTER YOUR NAME");
        enterNameLabel.setId("enterNameLabel");

        // init back button
        Button backButton = new Button("BACK");
        backButton.setOnAction(e -> handleBackRequest());

        // init name textfield
        nameTF = new TextField();
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

        // init errorlabel
        errorLabel = new ErrorLabel("ERROR, USERNAME IS INVALID");

        // add components to anchorpane
        root.getChildren().addAll(enterNameLabel, backButton, nameTF, errorLabel);

        // set coordinates for components
        enterNameLabel.setLayoutX(20);
        enterNameLabel.setLayoutY(70);
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        nameTF.setLayoutX(20);
        nameTF.setLayoutY(250);
        errorLabel.setLayoutX(20);
        errorLabel.setLayoutY(210);

    }

    public void moveSubScene() {
        nameTF.setText(name);
        if (!nameTF.getText().isEmpty()) {
            showNextButton();
        }
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
        MainMenu.setAnySubSceneShowing(true);
        MainMenu.setSubSceneShowing(SubScenes.CannonChooser);
        AnchorPane mainAP = (AnchorPane) root.getScene().getRoot();
        mainAP.getChildren().forEach(node -> {
            if (node instanceof CannonChooser) {
                this.moveSubScene();
                ((CannonChooser) node).moveSubScene();
            }
        });
    }

    private void showNextButton() {
        nextButtonShowing = true;
        nextButton = new Button("NEXT");
        nextButton.setId("nextButton");
        nextButton.setOnAction(e -> handleNextButtonRequest(e));
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
        boolean valid = confirmName();
        System.out.println("valid = " + valid);
        System.out.println("errorLabel.isHidden() = " + errorLabel.isHidden());
        if (!valid && errorLabel.isHidden()) {
            toggleErrorLabel();
        } else if (valid) {
            NameInputter.name = nameTF.getText();
            MainMenu.setAnySubSceneShowing(false);
            MainMenu.setSubSceneShowing(null);
            Scene scene = new GameScreen(nameTF.getText(), MainMenu.cannonColour);
            Stage newStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            newStage.setScene(scene);
            newStage.show();
        }
    }

    private boolean confirmName() {
        String nameToCheck = nameTF.getText();
        for (char character : nameToCheck.toCharArray()) {
            if (!Character.isAlphabetic(character)) {
                return false;
            }
        }
        return true;
    }

    private void toggleErrorLabel() {
        if (errorLabel.isHidden()) {
            errorLabel.setOpacity(1);
            errorLabel.setHidden(false);
        } else {
            errorLabel.setOpacity(0);
            errorLabel.setHidden(true);
        }
    }
}
