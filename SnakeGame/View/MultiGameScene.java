package SnakeGame.View;

import SnakeGame.App;
import SnakeGame.Controller.ButtonFactory;
import javafx.scene.Group;
import javafx.scene.Scene;

public class MultiGameScene extends GameScene{
     
    MultiGameScene(){
        initScene();
    }
 
    @Override
    public void initScene() {
        Group mainGroup = new Group();
        getRoot().setPrefSize(4000, 4000);
        mainGroup.getChildren().add(getRoot());
        setScene(new Scene(mainGroup, App.getScreenWidth(), App.getScreenHeight()));
        setGameView(new GameViewMulti(this));
        addRoot(
            ButtonFactory.createButton(100, 500,"Menu bouton",
            ()->SceneManager.getInstance().changeScene(new MenuScene())));
    }
}
