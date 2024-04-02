package SnakeGame.Controller;

import javafx.scene.control.Button;

public class ButtonFactory {
    private ButtonFactory(){}
    public static Button createButton(double x, double y, String buttonText, Runnable action) {
        Button button = new Button(buttonText);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnAction(e -> action.run());
        return button;
    }
}
