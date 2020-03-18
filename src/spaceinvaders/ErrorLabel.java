package spaceinvaders;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ErrorLabel extends Label {

    private boolean isHidden = true;
    
    public ErrorLabel(String text) {
        super(text);
        this.setTextFill(Paint.valueOf("red"));
        this.setOpacity(0);
        this.setPrefWidth(520);
        this.setAlignment(Pos.CENTER);
        this.setFont(Font.font("Lucida Console", 30));
    }
    
    public boolean isHidden(){
        return this.isHidden;
    }
    
    public void setHidden(boolean isHidden){
        this.isHidden = isHidden;
    }
}
