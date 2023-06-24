package Objects;

import interfaces.IShowableObject;
import processing.core.PApplet;
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
    }

    @Override
    public void show() {
<<<<<<< HEAD
        processing.image(processing.loadImage("../images/Shield.png"), sheildX, sheildY, sheildwidth,
                sheildheight);
=======
     processing.image(processing.loadImage("../images/"), sheildX, sheildY, sheildheight, sheildwidth);
>>>>>>> 315ecb6e7e2fb0cd9d91fcf0c845a8279b973585
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

}
