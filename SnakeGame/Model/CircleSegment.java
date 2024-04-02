package SnakeGame.Model;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class CircleSegment  extends Circle implements Segment{
    private static double size = 10;
    
    public CircleSegment(double coorX, double coorY) {
        super(size); // Création d'un cercle avec le rayon donné (ici, size)
        setCenterX(coorX);
        setCenterY(coorY);
    }
    @Override
    public double getSegX() {
       return getCenterX();
    }

    @Override
    public double getSegY() {
       return getCenterY();
    }

    @Override
    public void move(double x, double y) {
        setCenterX(getSegX()+x);
        setCenterY(getSegY()+y);

    }
    public void setSegPos(double x,double y){
        setCenterX(x);
        setCenterY(y);

    }
    @Override
    public void updateSegmentsList(List<Segment> list) {
        for (int i = list.size()-1; i >= 1; i--) {
            Segment prevSegment = list.get(i - 1);
            Segment currentSegment = list.get(i);
            double newPosX=newPos(currentSegment.getSegX(), prevSegment.getSegX());
            double newPosY=newPos(currentSegment.getSegY(), prevSegment.getSegY());
            currentSegment.setSegPos(newPosX,newPosY);
        }
    }
    private double newPos(double start, double end) {
        return start + (end - start)/3;
    }
    @Override
    public Node getSegment() {
      return this;
    } 
    
}
