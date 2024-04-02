package SnakeGame.View;

import SnakeGame.App;
import SnakeGame.Controller.Direction;
import SnakeGame.Model.Food;
import SnakeGame.Model.IASnake;
import SnakeGame.Model.RectangleSegment;
import SnakeGame.Model.Snake;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class GameViewSolo implements GameView  {

    private GameScene gameScene;
    private long lastFoodTime = 0;
    private long lastDirectionChange=0;
    private final long snakeInterval = 1* 1_000_000_00L; // Intervalle de 0,5 secondes en nanosecondes
    private int snakeMove=10;//deplacement de 10 pixel pour le serpent
    private Snake serpent;
    private Food food;
    IASnake test;
    private boolean isPaused = false; // si les serpents se touchent ou s ils touchent les bords

    public GameViewSolo(GameScene gameScene){
        this.gameScene=gameScene;
        serpent=new Snake(new RectangleSegment(600,600));
        test=new IASnake(this);
        gameScene.addRoot(test.getHeadNode());
        gameScene.addRoot(serpent.getHeadNode());
        foodGenerate();
        autoMoveSnake();
        animate();
    }
    @Override
    public void animate(){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isPaused && now - lastFoodTime >= snakeInterval) {
                    lastFoodTime = now;
                    checkCollisionWithFood(whichSnakeCollisionsWithFood());
                    IASnakeAnimation();
                    test.changeDir();
                    snakeAnimation();

                    if (test.isTouchingBorder() || serpent.isTouchingBorder()
                        || test.isTouchingSnake(serpent) || serpent.isTouchingSnake(test)
                        || test.isTouchingHimself() || serpent.isTouchingHimself(  )
                        ) {
                        // Mettre en pause l'animation si les serpents touche le bord 
                                                                    //touchent l'autre
                                                                    //se touchent eux-mêmes
                        isPaused = true;
                        SceneManager.getInstance().changeScene(new MenuScene());
                    }

                }
            }
        };

        animationTimer.start();
    }
    public int whichSnakeCollisionsWithFood(){
        if(test.getHeadNode().getBoundsInParent().intersects(food.getBoundsInParent())) {
            return 1;
        }else if (serpent.getHeadNode().getBoundsInParent().intersects(food.getBoundsInParent())) {
            return 2;
        }
        return 0;
    }
    //fonction pour verifier si le serpent touche de la nourriture 
    @Override
     public boolean checkCollisionWithFood(int b) {
        if(b != 0){
            // Collision détectée entre le serpent et la nourriture 'food'
            foodGenerate();
            if( b == 1){
                RectangleSegment rec = new RectangleSegment(test.getTail().getSegX(),test.getTail().getSegY());
                test.addToList(rec);
                gameScene.addRoot(rec);
            }else if ( b == 2){
                RectangleSegment rec = new RectangleSegment(serpent.getTail().getSegX(),serpent.getTail().getSegY());
                serpent.addToList(rec);
                gameScene.addRoot(rec);
            }
        }
        return false; // Aucune collision détectée avec la nourriture
    }
    public void IASnakeAnimation(){
        test.autoMove(test.getDirection());
    }
    //fonction pour mettre à jouer la position du serpent en fonction de snakeMove
    public void snakeAnimation(){
        serpent.autoMove(serpent.getDirection());
    }

    public void autoMoveSnake(){
        gameScene.getScene().setOnKeyPressed(e -> {
            switch(e.getCode()){
                case KeyCode.Z : 
                if(serpent.getDirection()  != Direction.DOWN){
                    serpent.setDirection(Direction.UP);
                }
                break;
                case KeyCode.S:
                if(serpent.getDirection()  != Direction.UP){
                    serpent.setDirection(Direction.DOWN);
                }
                    break;
                case KeyCode.Q:
                if(serpent.getDirection()  != Direction.RIGHT){
                    serpent.setDirection(Direction.LEFT);
                }
                    break;
                case KeyCode.D:
                if(serpent.getDirection()  != Direction.LEFT){
                    serpent.setDirection(Direction.RIGHT);
                }
                    break;
                default:
                    break;
            }
        });
    }
    
    
    //fonction qui se gere de faire apparaitre de la nourriture
    public void foodGenerate(){
        gameScene.removeRoot(food);
        food=new Food(App.getScreenWidth(),App.getScreenHeight());
        gameScene.addRoot(food);
    }
    public Food getFood(){return this.food;}
    
}
