package Objects;

import interfaces.ICollectibleItem;
import interfaces.IShowableObject;
import processing.core.PApplet;

public class DoubleScore implements ICollectibleItem ,IShowableObject{
    PApplet processing;
    private int width;
    private int height;
    private int scoreX;
    private int scoreY;


    public DoubleScore(int width , int height , PApplet processing)
    {
        this.width=width;
        this.height=height;
        this.processing=processing;
    }
    

    @Override
    public void show() {
         processing.image(processing.loadImage("../images/DoubleScore.png"),scoreX , scoreY,  width, height);
    }

    @Override
    public void move() {

    }

    @Override
    public void Collect() {

    }

    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }


    public int getScoreX() {
        return scoreX;
    }


    public void setScoreX(int scoreX) {
        this.scoreX = scoreX;
    }


    public int getY() {
        return scoreY;
    }


    public void setScoreY(int scoreY) {
        this.scoreY = scoreY;
    }



    
}
