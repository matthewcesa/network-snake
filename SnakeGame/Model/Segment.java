package SnakeGame.Model;

import java.util.List;

import javafx.scene.Node;



public interface Segment{
    public Node getSegment();
    public double getSegX();
    public double getSegY();
    public void move(double x,double y);
    public void updateSegmentsList(List<Segment> list);
    public void setSegPos(double x,double y);
}
