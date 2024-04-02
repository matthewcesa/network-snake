package SnakeGame.View;

import SnakeGame.App;
import SnakeGame.Controller.ButtonFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MenuScene implements SceneAdaptater {
    private Scene menuScene;
    private Pane root;
    private boolean is2V2 = false;
    public MenuScene() {
        root=new Pane();
        menuScene=new Scene(root, App.getScreenWidth(), App.getScreenHeight());
        initialise();
    }

    public void initialise(){ 
        Label l = new Label("Ceci est la page du menu");
        root.getChildren().add(l);

        Button snakeMulti=ButtonFactory.createButton(App.getScreenWidth()/2.3, 400,"Snake multi mode",
                    ()->SceneManager.getInstance().changeScene(new GameScene1V1()));
                    

        Button snakeSolo=ButtonFactory.createButton(App.getScreenWidth()/2.3, 300,"Snake solo mode",
                    ()->SceneManager.getInstance().changeScene(new SoloGameScene()));


        Button multi=ButtonFactory.createButton(App.getScreenWidth()/2.3,500,"Multi mode",
                    ()->SceneManager.getInstance().changeScene(new MultiGameScene()));
             
        root.getChildren().addAll(snakeSolo,snakeMulti,multi);
    }

    @Override
    public Scene getScene(){
        return menuScene;
    }
    // public boolean getIs2V2(){
    //     return this.is2v2;
    // }
}
