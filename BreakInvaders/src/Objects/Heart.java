package Objects;

import interfaces.ICollectibleItem;
import interfaces.IShowableObject;
import processing.core.PApplet;

public class Heart implements IShowableObject , ICollectibleItem {
    PApplet processing;
    private int width;
    private int height;
    private int heartX;
    private int heartY;

    public Heart(int width , int height , PApplet processing)
    {
        this.processing=processing;
        this.width=width;
        this.height=height;
    }

    @Override
    public void show() {
        //processing.image(processing.loadImage("../images") , heartX , heartY  ,width , height);
        
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

    public int getHeartX() {
        return heartX;
    }

    public void setHeartX(int heartX) {
        this.heartX = heartX;
    }

    public int getHeartY() {
        return heartY;
    }

    public void setHeartY(int heartY) {
        this.heartY = heartY;
    }

    @Override
    public void move() {

    }



    
    
}
