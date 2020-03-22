package spaceinvaders;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenu extends Scene {

    private CannonChooser cannonChooser = new CannonChooser();
    private NameInputter nameInputter = new NameInputter();
    private Instructions instructions = new Instructions();
    private Scores scores = new Scores();
    private static SubScenes subSceneShowing = null;
    private static boolean anySubSceneShowing = false;
    public static String name;
    public static String cannonColour;
    private AnchorPane mainAP;

    public MainMenu() {
        super(new AnchorPane(), 1060, 700);
        mainAP = (AnchorPane) this.getRoot();
        mainAP.getStylesheets().add("spaceinvaders/MainMenu.css");

        // init playbutton
        Button playButton = new Button("PLAY");
        playButton.setLayoutX(100);
        playButton.setLayoutY(100);
        playButton.setOnAction(e -> handlePlayRequest(e));
        playButton.setOnMouseEntered(e -> handleMouseEntered(e));
        playButton.setOnMouseExited(e -> handleMouseExited(e));

        // init instructionsbutton
        Button instructionsButton = new Button("INSTRUCTIONS");
        instructionsButton.setId("instructionsButton");
        instructionsButton.setLayoutX(100);
        instructionsButton.setLayoutY(250);
        instructionsButton.setOnAction(e -> handleInstructionsRequest(e));
        instructionsButton.setOnMouseEntered(e -> handleMouseEntered(e));
        instructionsButton.setOnMouseExited(e -> handleMouseExited(e));
        
        // init scoresbutton
        Button scoresButton = new Button("LEADERBOARD");
        scoresButton.setId("scoresButton");
        scoresButton.setLayoutX(100);
        scoresButton.setLayoutY(400);
        scoresButton.setOnAction(e -> handleScoresRequest(e));
        scoresButton.setOnMouseEntered(e -> handleMouseEntered(e));
        scoresButton.setOnMouseExited(e -> handleMouseExited(e));

        // init exitbutton
        Button exitButton = new Button("EXIT");
        exitButton.setLayoutX(100);
        exitButton.setLayoutY(550);
        exitButton.setOnAction(e -> handleExitRequest(e));
        exitButton.setOnMouseExited(e -> handleMouseExited(e));
        exitButton.setOnMouseEntered(e -> handleMouseEntered(e));
        
        mainAP.getChildren().addAll(playButton, instructionsButton, scoresButton, exitButton);
        mainAP.getChildren().addAll(cannonChooser, instructions, nameInputter, scores);
    }

    private void handleMouseEntered(MouseEvent e) {
        ((Button) e.getSource()).setStyle("-fx-border-color:red");
    }

    private void handleMouseExited(MouseEvent e) {
        ((Button) e.getSource()).setStyle("-fx-border-color:null");
    }

    private void handlePlayRequest(ActionEvent e) {
        System.out.println("BEFORE PLAYBUTTON");
        System.out.println("anySubSceneShowing = " + anySubSceneShowing);
        System.out.println("subSceneShowing = " + subSceneShowing);
        if (!anySubSceneShowing) {
            cannonChooser.moveSubScene();
            anySubSceneShowing = true;
            subSceneShowing = SubScenes.CannonChooser;
        } else {
            switch (subSceneShowing) {
                case CannonChooser: {
                    cannonChooser.moveSubScene();
                    anySubSceneShowing = false;
                    subSceneShowing = null;
                    break;
                }
                case Instructions: {
                    instructions.moveSubScene();
                    cannonChooser.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.CannonChooser;
                    break;
                }
                case NameInputter: {
                    nameInputter.moveSubScene();
                    cannonChooser.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.CannonChooser;
                    break;
                }
                case Scores: {
                    scores.moveSubScene();
                    cannonChooser.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.CannonChooser;
                    break;
                }
            }
        }
        System.out.println("AFTER PLAYBUTTON");
        System.out.println("anySubSceneShowing = " + anySubSceneShowing);
        System.out.println("subSceneShowing = " + subSceneShowing);
    }

    private void handleExitRequest(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    private void handleScoresRequest(ActionEvent e) {
        System.out.println("BEFORE SCORESBUTTON");
        System.out.println("anySubSceneShowing = " + anySubSceneShowing);
        System.out.println("subSceneShowing = " + subSceneShowing);
        if (!anySubSceneShowing) {
            scores.moveSubScene();
            anySubSceneShowing = true;
            subSceneShowing = SubScenes.Scores;
        } else {
            switch (subSceneShowing) {
                case Scores: {
                    scores.moveSubScene();
                    anySubSceneShowing = false;
                    subSceneShowing = null;
                    break;
                }
                case CannonChooser: {
                    cannonChooser.moveSubScene();
                    scores.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.Scores;
                    break;
                }
                case Instructions: {
                    instructions.moveSubScene();
                    scores.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.Scores;
                    break;
                }
                case NameInputter: {
                    nameInputter.moveSubScene();
                    scores.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.Scores;
                    break;
                }
            }
        }
        System.out.println("AFTER SCORESBUTTON");
        System.out.println("anySubSceneShowing = " + anySubSceneShowing);
        System.out.println("subSceneShowing = " + subSceneShowing);
    }

    private void handleInstructionsRequest(ActionEvent e) {
        System.out.println("BEFORE INSTRUCTIONSBUTTON");
        System.out.println("anySubSceneShowing = " + anySubSceneShowing);
        System.out.println("subSceneShowing = " + subSceneShowing);
        if (!anySubSceneShowing) {
            instructions.moveSubScene();
            anySubSceneShowing = true;
            subSceneShowing = SubScenes.Instructions;
        } else {
            switch (subSceneShowing) {
                case Instructions: {
                    instructions.moveSubScene();
                    anySubSceneShowing = false;
                    subSceneShowing = null;
                    break;
                }
                case CannonChooser: {
                    cannonChooser.moveSubScene();
                    instructions.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.Instructions;
                    break;
                }
                case Scores: {
                    scores.moveSubScene();
                    instructions.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.Instructions;
                    break;
                }
                case NameInputter: {
                    nameInputter.moveSubScene();
                    instructions.moveSubScene();
                    anySubSceneShowing = true;
                    subSceneShowing = SubScenes.Instructions;
                    break;
                }
            }
        }

        System.out.println("AFTER INSTRUCTIONSBUTTON");
        System.out.println("anySubSceneShowing = " + anySubSceneShowing);
        System.out.println("subSceneShowing = " + subSceneShowing);
    }

    public static void setAnySubSceneShowing(boolean anySubSceneShowing) {
        MainMenu.anySubSceneShowing = anySubSceneShowing;
    }

    public static void setSubSceneShowing(SubScenes subSceneShowing) {
        MainMenu.subSceneShowing = subSceneShowing;
    }
}
