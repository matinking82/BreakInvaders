package Objects;

import interfaces.IShowableObject;

import Game.Game;
import images.Images;
import interfaces.ICollectibleItem;

public class Sheild implements IShowableObject, ICollectibleItem {
    Game processing;
    private int sheildwidth;
    private int sheildheight;
    private int sheildX;
    private int sheildY;
    static boolean sheildAffect = false;

    public Sheild(int x, int y, int sheildheight, int sheildwidth, Game processing) {
        this.sheildheight = sheildheight;
        this.sheildwidth = sheildwidth;
        this.processing = processing;
        sheildY = y;
        sheildX = x;

    }

    @Override
    public void show() {
        processing.image(Images.Shield, sheildX, sheildY, sheildwidth,
                sheildheight);
    }

    @Override
    public void Collect() {
        Game.Shield++;
    }

    public int getX() {
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
        this.sheildY += 10;
    }
}
