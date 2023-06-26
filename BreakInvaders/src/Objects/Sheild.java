package Objects;

import interfaces.IShowableObject;
import processing.core.PApplet;

import java.util.Random;

import images.Images;
import interfaces.ICollectibleItem;

public class Sheild implements IShowableObject, ICollectibleItem {
    PApplet processing;
    private int sheildwidth;
    private int sheildheight;
    private int sheildX;
    private int sheildY;
    static boolean sheildAffect = false;

    public Sheild(int sheildheight, int sheildwidth, PApplet processing) {
        this.sheildheight = sheildheight;
        this.sheildwidth = sheildwidth;
        this.processing = processing;
        sheildY-=sheildheight;
        sheildX = randomNum(10, processing.width-sheildwidth-10);


    }

    @Override
    public void show() {
        processing.image(Images.Shield, sheildX, sheildY, sheildwidth,
                sheildheight);
    }

    @Override
    public void Collect() {

    }

    public int getShieldX() {
        return this.sheildX;
    }

    public int getY() {
        return this.sheildY;
    }

    public int getShieldHeight() {
        return this.sheildheight;
    }

    public int getShieldWidth() {
        return this.sheildwidth;
    }

    public void setSheildX(int x) {
        this.sheildX = x;
    }

    public void setSheildY(int y) {
        this.sheildY = y;
    }

    public void setSheildHeight(int h) {
        this.sheildheight = h;
    }

    public void setShieldWidth(int w) {
        this.sheildwidth = w;
    }

    @Override
    public void move() {
    this.sheildY+=10;
    }
    private int randomNum(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start + 1) + start;
    }

}
