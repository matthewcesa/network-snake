package SnakeGame.View;

import java.util.ArrayList;
import java.util.List;

import SnakeGame.App;
import SnakeGame.Model.CircleSegment;
import SnakeGame.Model.Food;
import SnakeGame.Model.RectangleSegment;
import SnakeGame.Model.Snake;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
// Classe responsable des animations et de la logique du mode multijoueur du jeu Snake
public class GameViewMulti implements GameView{
    private GameScene gameScene;
    private double deltaX, deltaY;//Distance entre la souris et le serpent
    private long lastFoodTime = 0;
    private double snakeSpeed=5.00;
    private List<Food> foodList;
    private final long snakeInterval = 5* 1_000_000_0L; // Intervalle de 0,5 secondes en nanosecondes
    Snake snake;
   
    GameViewMulti(GameScene gameScene){
        this.gameScene=gameScene;
        snake=new Snake(new CircleSegment(600, 600));
        gameScene.addRoot(snake.getHeadNode());
        
        snake.setSnakeSize(10, gameScene.getRoot());
        setupMouseMovement();
        initFoodList();
        animate();
        

    }
    
    private void setupMouseMovement() {
         gameScene.getScene().setOnMouseMoved(event -> {
            double sceneMouseX = event.getSceneX();
            double sceneMouseY = event.getSceneY();

            // Conversion des coordonnées de la scène en coordonnées locales pour le Pane
            Point2D paneCoords = gameScene.getRoot().sceneToLocal(sceneMouseX, sceneMouseY);

            // Mise à jour des variables mouseX et mouseY
            double mouseX = paneCoords.getX();
            double mouseY = paneCoords.getY();
            deltaX = mouseX - ((Circle) snake.getHead()).getCenterX();
            deltaY = mouseY - ((Circle) snake.getHead()).getCenterY();   
        });
    }
   
    @Override
    public void animate() {
        new AnimationTimer(){

            @Override
            public void handle(long now) {
                if (now - lastFoodTime >= snakeInterval) {
                    lastFoodTime = now;
                    snakeAnimation();
                }
     
            }
            
        }.start();;
    }

    @Override
    public boolean checkCollisionWithFood(int b) {
        // TODO Auto-generated method stub
        // RectangleSegment rec = new RectangleSegment(test.serpent.getTail().getSegX(),test.serpent.getTail().getSegY());
        // test.serpent.addToList(rec);
        // gameScene.addRoot(rec);
        throw new UnsupportedOperationException("Unimplemented method 'checkCollisionWithFood'");
    }
    public void initFoodList(){
        foodList=new ArrayList<>();
        for(int i=0;i<100;i++){
            Food food=new Food(4000, 4000);
            foodList.add(food);
            gameScene.addRoot(food);
        }
    }
    @Override
    public void foodGenerate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'foodGenerate'");
    }
    @Override
    public Food getFood() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFood'");
    }
    @Override
    // Met à jour et anime le serpent en fonction de la position actuelle de la souris.
    public void snakeAnimation() {
        Point2D movement = snakeMovement();
        snake.MoveHeadX(movement.getX());
        snake.MoveHeadY(movement.getY());
        handleScreenBoundaries();
        centerViewOnSnake(snake.getHeadX(),snake.getHeadY(),gameScene.getRoot());
    }
    
    //Calcule et renvoie le nouveau déplacement en X et Y pour la tête du serpent vers la souris
    private Point2D snakeMovement() {
        double angleToMouse = Math.atan2(deltaY, deltaX);
        double moveX = Math.cos(angleToMouse) * snakeSpeed;
        double moveY = Math.sin(angleToMouse) * snakeSpeed;
        return new Point2D(moveX, moveY);
    }
    /*Methode qui gère le cas où la tête du serpent dépasse les limites de l'écran
    en déplaçant le serpent de l'autre côté*/
    private void handleScreenBoundaries() {
        if(snake.getHeadX() >= 4000) snake.setBodySnakePos(-4000,0);
        if(snake.getHeadY() >= 4000) snake.setBodySnakePos(0,-4000);
        if(snake.getHeadX() <= 0) snake.setBodySnakePos(4000,0);
        if(snake.getHeadY() <= 0) snake.setBodySnakePos(0,4000);
    }
    //Ajuste la vue pour que le serpent reste centré à l'écran lors de son déplacement
    public void centerViewOnSnake(double x,double y ,Pane root){
        double posX = x - App.getScreenWidth()/ 2;
        double posY = y - App.getScreenHeight() / 2;

        root.setTranslateX(-posX);
        root.setTranslateY(-posY);
       
    }
    
}
