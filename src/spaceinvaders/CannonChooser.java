package spaceinvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class CannonChooser extends SubScene {

    private ImageView BLACK_SPACE_INVADER, BLUE_SPACE_INVADER, PINK_SPACE_INVADER, YELLOW_SPACE_INVADER;
    private boolean isHidden = true;
    private Button selectedButton;

    public CannonChooser() {
        super(new AnchorPane(), 560, 440);
        AnchorPane root = (AnchorPane) this.getRoot();
        root.setStyle("-fx-background-color: grey");
        root.getStylesheets().add("spaceinvaders/CannonChooser.css");
        setLayoutX(1060);
        setLayoutY(100);

        // get images
        try {
            BLACK_SPACE_INVADER = new ImageView(new Image(new FileInputStream("resources/black space invader.png"), 100, 0, true, false));
            BLUE_SPACE_INVADER = new ImageView(new Image(new FileInputStream("resources/blue space invader.png"), 100, 0, true, false));
            PINK_SPACE_INVADER = new ImageView(new Image(new FileInputStream("resources/pink space invader.png"), 100, 0, true, false));
            YELLOW_SPACE_INVADER = new ImageView(new Image(new FileInputStream("resources/yellow space invader.png"), 100, 0, true, false));
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }

        // init choose colour label
        Label chooseColour = new Label("CHOOSE A COLOUR");

        // init black button
        Button blackButton = new Button("BLACK");
        blackButton.setId("BLACK");
        blackButton.setGraphic(BLACK_SPACE_INVADER);
        blackButton.setOnAction(e -> handleButtonClick(e));

        // init blue button
        Button blueButton = new Button("BLUE");
        blueButton.setGraphic(BLUE_SPACE_INVADER);
        blueButton.setOnAction(e -> handleButtonClick(e));

        // init pink button
        Button pinkButton = new Button("PINK");
        pinkButton.setGraphic(PINK_SPACE_INVADER);
        pinkButton.setOnAction(e -> handleButtonClick(e));

        // init yellow button
        Button yellowButton = new Button("YELLOW");
        yellowButton.setGraphic(YELLOW_SPACE_INVADER);
        yellowButton.setOnAction(e -> handleButtonClick(e));

        // add buttons to hbox
        HBox buttonsHB = new HBox();
        buttonsHB.setPrefWidth(500);
        buttonsHB.getChildren().addAll(blackButton, blueButton, pinkButton, yellowButton);
        buttonsHB.setAlignment(Pos.CENTER);

        // add components to anchorpane
        root.getChildren().addAll(buttonsHB, chooseColour);

        // set coordinates for components
        buttonsHB.setLayoutX(20);
        buttonsHB.setLayoutY(150);
        chooseColour.setLayoutX(20);
        chooseColour.setLayoutY(0);
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

    private void handleButtonClick(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        if (clicked.equals(selectedButton) == false) {
            setAsSelected(clicked);
        }
    }

    private void setAsSelected(Button clicked) {
        if (selectedButton == null) {
            showNextButton();
        }
        if (selectedButton != null) {
            selectedButton.setStyle("-fx-border-color:white");
        }
        clicked.setStyle("-fx-border-color:red");
        selectedButton = clicked;
    }

    private void showNextButton() {
        Button nextButton = new Button("NEXT");
        nextButton.setId("nextButton");
        nextButton.setOnAction(e -> handleNextButtonRequest(e));
        AnchorPane root = (AnchorPane) this.getRoot();
        root.getChildren().add(nextButton);
        nextButton.setLayoutX(340);
        nextButton.setLayoutY(370);
    }

    private void handleNextButtonRequest(ActionEvent e) {
        SpaceInvaders.setChosenCannon(selectedButton);
        System.out.println("selectedID = " + selectedButton.getId());
        NameInputter nameInputter = new NameInputter(selectedButton.getId());
        AnchorPane root = (AnchorPane) this.getRoot();
        AnchorPane s = (AnchorPane) root.getScene().getRoot();
        s.getChildren().add(nameInputter);
        nameInputter.moveSubScene();
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    public Button getSelectedButton() {
        return selectedButton;
    }
}
