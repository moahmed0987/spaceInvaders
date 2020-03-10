package spaceinvaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SpaceInvaders extends Application {

    private static Button chosenCannon;
    private static String userName;

    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setChosenCannon(Button chosen) {
        chosenCannon = chosen;
    }

    public static void setName(String name) {
        userName = name;
    }
    
    public static Button getChosenCannon(){
        return chosenCannon;
    }

}
