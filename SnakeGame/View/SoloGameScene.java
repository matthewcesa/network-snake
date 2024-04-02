package SnakeGame.View;

import SnakeGame.App;
import SnakeGame.Controller.ButtonFactory;
import javafx.scene.Scene;

public class SoloGameScene extends GameScene{
    public SoloGameScene(){
        initScene();
    }
    @Override
    public void initScene() {
        setScene(new Scene(getRoot(), App.getScreenWidth(), App.getScreenHeight()));
        setGameView(new GameViewSolo(this));
        addRoot(
            ButtonFactory.createButton(100, 500,"Menu bouton",
            ()->   
            SceneManager.getInstance().changeScene(new MenuScene()))
            );

    }
    
}
