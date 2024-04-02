package SnakeGame.View;

import SnakeGame.App;
import SnakeGame.Controller.Direction;
import SnakeGame.Model.Food;
import SnakeGame.Model.IASnake;
import SnakeGame.Model.RectangleSegment;
import SnakeGame.Model.Snake;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class Game1V1 implements GameView  {

    private GameScene gameScene;
    private long lastFoodTime = 0;
    private long lastDirectionChange=0;
    private final long snakeInterval = 1* 1_000_000_00L; // Intervalle de 0,5 secondes en nanosecondes
    // private int snakeMove=10;//deplacement de 10 pixel pour le serpent
    private Snake serpent,serpent2;
    private Food food;
    private boolean isPaused = false; // si les serpents se touchent ou s ils touchent les bords

    public Game1V1(GameScene gameScene){
        this.gameScene=gameScene;
        serpent=new Snake(new RectangleSegment(10,10));
        serpent2=new Snake(new RectangleSegment(600,600));
        gameScene.addRoot(serpent2.getHeadNode());
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
                    checkCollisionWithFood(whichSnakeCollisionsWithFood(food.isBlue()));
                    snakeAnimation();
                    // serpent2Animation();

                    if (serpent2.isTouchingBorder() || serpent.isTouchingBorder()
                        || serpent2.isTouchingSnake(serpent) || serpent.isTouchingSnake(serpent2)
                        || serpent2.isTouchingHimself() || serpent.isTouchingHimself(  )
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
    public int whichSnakeCollisionsWithFood(boolean isBlue){
        if (!isBlue){
            if(serpent2.getHeadNode().getBoundsInParent().intersects(food.getBoundsInParent())) {
                return 1;
            }else if (serpent.getHeadNode().getBoundsInParent().intersects(food.getBoundsInParent())) {
                return 2;
            }
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
                RectangleSegment rec = new RectangleSegment(serpent2.getTail().getSegX(),serpent2.getTail().getSegY());
                serpent2.addToList(rec);
                gameScene.addRoot(rec);
            }else if ( b == 2){
                RectangleSegment rec = new RectangleSegment(serpent.getTail().getSegX(),serpent.getTail().getSegY());
                serpent.addToList(rec);
                gameScene.addRoot(rec);
            }
        }
        return false; // Aucune collision détectée avec la nourriture
    }

    // public void serpent2Animation(){
    // }
    //fonction pour mettre à jouer la position du serpent en fonction de snakeMove
    public void snakeAnimation(){
        serpent2.autoMove(serpent2.getDirection());
        serpent.autoMove(serpent.getDirection());
    }

    public void autoMoveSnake(){
        gameScene.getScene().setOnKeyPressed(e -> {
            switch(e.getCode()){
                case I : 
                if(serpent2.getDirection()  != Direction.DOWN){
                    serpent2.setDirection(Direction.UP);
                }
                break;
                case K:
                if(serpent2.getDirection()  != Direction.UP){
                    serpent2.setDirection(Direction.DOWN);
                }
                    break;
                case J:
                if(serpent2.getDirection()!= Direction.RIGHT){
                    serpent2.setDirection(Direction.LEFT);
                }
                    break;
                case L:
                if(serpent2.getDirection()!= Direction.LEFT){
                    serpent2.setDirection(Direction.RIGHT);
                }
                    break;
                    case Z : 
                if(serpent.getDirection()  != Direction.DOWN){
                    serpent.setDirection(Direction.UP);
                }
                break;
                case S:
                if(serpent.getDirection()  != Direction.UP){
                    serpent.setDirection(Direction.DOWN);
                }
                    break;
                case Q:
                if(serpent.getDirection()  != Direction.RIGHT){
                    serpent.setDirection(Direction.LEFT);
                }
                    break;
                case D:
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
