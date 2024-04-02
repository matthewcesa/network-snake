package SnakeGame.Model;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class RectangleSegment extends Rectangle implements Segment {
    private static double size = 20;

    public RectangleSegment(double coorX,double coorY){
        super(size,size); 
        setTranslateX(coorX);
        setTranslateY(coorY);
    }
    @Override
    public void move(double x, double y) {
        System.out.println(getSegX()+x);
        System.out.println(getSegY()+y);
        setTranslateX(getSegX()+x);
        setTranslateY(getSegY()+y);
    }
    @Override
    public double getSegX() {
       return getTranslateX();
    }
    @Override
    public double getSegY() {
        return getTranslateY();
    }
    @Override
    public void updateSegmentsList(List<Segment> list) {
        // System.out.println("head" +list.get(0).getSegX() + " " + list.get(0).getSegY());
        for (int i = list.size()-1 ; i >= 1 ; i--){
            // System.out.println("previous" +list.get(i).getSegX() + " " + list.get(i).getSegY());
            Segment prev = list.get(i-1);
            list.get(i).setSegPos(prev.getSegX(), prev.getSegY());
            
        }
    }
    @Override
    public void setSegPos(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }
    @Override
    public Node getSegment() {
       return this;
    }
}
