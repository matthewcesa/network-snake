package SnakeGame.View;

import SnakeGame.Model.Food;

public interface GameView {
    public void animate();
    public void foodGenerate();
    public boolean checkCollisionWithFood(int b);
    public void snakeAnimation();
    public Food getFood();
} 
