package Objects;

import interfaces.IShowableObject;
import Game.Game;
import images.Images;
import interfaces.ICollectibleItem;

public class DoubleB implements IShowableObject, ICollectibleItem {
    private int doubleBX;
    private int doubleBY;
    private int doubleBWidth;
    private int doubleBHeight;
    Game processing;

    public DoubleB(int doubleBX, int doubleBY, int doubleBHeight, int doubleBWidth, Game processing) {
        this.doubleBHeight = doubleBHeight;
        this.doubleBWidth = doubleBWidth;
        this.processing = processing;
        this.doubleBY = doubleBY;
        this.doubleBX = doubleBX;
    }

    @Override
    public void show() {
        processing.image(Images.DoubleB, doubleBX, doubleBY, doubleBHeight, doubleBWidth);

    }

    @Override
    public void Collect() {
        Game.DoubleBullet++;
    }

    @Override
    public void move() {
        doubleBY += 10;
    }

    public int getX() {
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
}
