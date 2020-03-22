package spaceinvaders;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SpaceInvaders extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.TRANSPARENT);
        MainMenu mainMenu = new MainMenu();
        stage.setScene(mainMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
