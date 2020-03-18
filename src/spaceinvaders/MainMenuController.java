package spaceinvaders;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {

    private CannonChooser cannonChooser = new CannonChooser();
    private NameInputter nameInputter = new NameInputter();
    private Instructions instructions = new Instructions();
    private Scores scores = new Scores();
    private static SubScenes subSceneShowing = null;
    private static boolean anySubSceneShowing = false;
    public static String name;
    public static String cannonColour;

    @FXML
    private AnchorPane mainAP;

    @FXML
    private Button playButton, scoresButton, exitButton;

    @FXML
    private void handleMouseEntered(MouseEvent e) {
        ((Button) e.getSource()).setStyle("-fx-border-color:red");
    }

    @FXML
    private void handleMouseExited(MouseEvent e) {
        ((Button) e.getSource()).setStyle("-fx-border-color:null");
    }

    @FXML
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

    @FXML
    private void handleExitRequest(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
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

    @FXML
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainAP.getStylesheets().add("spaceinvaders/MainMenu.css");
        mainAP.getChildren().addAll(cannonChooser, instructions, scores, nameInputter);
    }

    public static void setAnySubSceneShowing(boolean anySubSceneShowing) {
        MainMenuController.anySubSceneShowing = anySubSceneShowing;
    }

    public static void setSubSceneShowing(SubScenes subSceneShowing) {
        MainMenuController.subSceneShowing = subSceneShowing;
    }
}
