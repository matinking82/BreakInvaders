package Objects;

import interfaces.IShowableObject;
import processing.core.PApplet;

import java.util.Random;

import interfaces.ICollectibleItem;

public class DoubleB implements IShowableObject,ICollectibleItem{
    private int doubleBX;
    private int doubleBY;
    private   int doubleBWidth;
    private   int doubleBHeight;
    PApplet processing;


    public DoubleB(int doubleBX,int doubleBY,int doubleBHeight,int doubleBWidth,PApplet processing){
        this.doubleBHeight=doubleBHeight;
        this.doubleBWidth=doubleBWidth;
        this.processing=processing;
        doubleBY = -doubleBHeight;
        doubleBX = randomNum(10, processing.width-doubleBWidth-10);
    }
    @Override
    public void show() {
        processing.image(processing.loadImage("../images/DoubleB.png"), doubleBX, doubleBY, doubleBHeight, doubleBWidth);

    }
    @Override
    public void Collect() {
        
    }
    @Override
    public void move() {
        doubleBY+=10;
    }

    public int getDoubleBX() {
        return this.doubleBX;
    }
    public void setDoubleBX(int doubleBX) {
        this.doubleBX = doubleBX;
    }
    public int getY() {
        return this.doubleBY;
    }
    public void setDoubleBY(int doubleBY) {
        this.doubleBY = doubleBY;
    }
    public int getDoubleBWidth() {
        return this.doubleBWidth;
    }
    public void setDoubleBWidth(int doubleBWidth) {
        this.doubleBWidth = doubleBWidth;
    }
    public int getDoubleBHeight() {
        return this.doubleBHeight;
    }
    public void setDoubleBHeight(int doubleBHeight) {
        this.doubleBHeight = doubleBHeight;
    }
    private int randomNum(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start + 1) + start;
    }
    
}
