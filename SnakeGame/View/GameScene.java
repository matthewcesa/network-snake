package SnakeGame.View;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
public abstract class GameScene implements SceneAdaptater{
    private Scene gameScene;
    private GameView gameView;
    private Pane root;
    GameScene(){
        this.root=new Pane();
    }
    public abstract void initScene();

    public void setScene(Scene gameScene){
        this.gameScene=gameScene;
    }
    public void setGameView(GameView gameView){
        this.gameView=gameView;
    }
    public void addRoot(Node... elemt){
        root.getChildren().addAll(elemt);
    }

    public void removeRoot(Node elemt){
        root.getChildren().remove(elemt);
    }
    @Override
    public Scene getScene() {
        return gameScene;
    }

    public Pane getRoot() {
        return root;
    }
    

}
