package spaceinvaders;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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

    private CannonChooser cannonChooser;
    private NameInputter nameInputter;

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

    // *****************************************************************************************************************
    // find a way to clean up this disgusting code
    @FXML
    private void handlePlayRequest(ActionEvent e) throws IOException {
        System.out.println(mainAP.getChildren());
        if (mainAP.getChildren().size() > 5) {
            for (int o = 5; o < mainAP.getChildren().size(); o++) {
                mainAP.getChildren().remove(o);
            }
        }
        if (mainAP.getChildren().size() == 4) {
            if (mainAP.getChildren().get(3) instanceof CannonChooser == false) {
                createSubScene();
            }
        }
        cannonChooser.moveSubScene();
        if (mainAP.getChildren().size() == 5) {
            if (mainAP.getChildren().get(4) instanceof NameInputter) {
                nameInputter = (NameInputter) (mainAP.getChildren().get(4));
                if (nameInputter.isHidden() == false) {
                    nameInputter.moveSubScene();
                }
            }
        }
        if (mainAP.getChildren().size() == 5 && (cannonChooser.isHidden() == false)) {
            mainAP.getChildren().remove(4);
        }
    }
    // *****************************************************************************************************************

    @FXML
    private void handleExitRequest(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleScoresRequest(ActionEvent e) {
        Leaderboard lb = new Leaderboard();
        Map<String, Integer> leaderboard = lb.get();
//        for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) {
//            System.out.println("Key : " + entry.getKey()
//                    + " Value : " + entry.getValue());
//        }
        Scores s = new Scores(leaderboard);
        mainAP.getChildren().add(s);
        s.moveSubScene();
    }

    private void createSubScene() {
        cannonChooser = new CannonChooser();
        mainAP.getChildren().add(cannonChooser);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainAP.getStylesheets().add("spaceinvaders/MainMenu.css");
        createSubScene();
    }
}
