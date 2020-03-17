package spaceinvaders;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// TODO turn into a subscene
public class Instructions {
//public class Instructions extends SubScene{

    private static final double WIDTH = 500;
    private static final double HEIGHT = 400;
    private static final String TITLE = "Instructions";
    private static final String INSTRUCTIONS
            = "Use left and right arrow keys to move\n"
            + "Use up arrow key to shoot";

//    public Instructions() {
//        super(new AnchorPane(), WIDTH, HEIGHT);
//    }
    public static void display() {
        // init stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle(TITLE);

        // init messagelabel
        Label messageLabel = new Label(INSTRUCTIONS);
        messageLabel.setTextAlignment(TextAlignment.CENTER);

        /// init closebutton
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());

        // init layout 
        AnchorPane layout = new AnchorPane();
        layout.setPadding(new Insets(20));

        // set coordinates of components in layout
        closeButton.setLayoutX(WIDTH - 120);
        closeButton.setLayoutY(HEIGHT - 70);

        // add components to layout
        layout.getChildren().addAll(messageLabel, closeButton);

        // init scene
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        scene.getStylesheets().add("spaceinvaders/Instructions.css");

        window.setScene(scene);
        window.showAndWait();
    }

}
