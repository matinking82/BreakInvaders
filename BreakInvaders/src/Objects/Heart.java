package Objects;

import Game.Game;
import images.Images;
import interfaces.ICollectibleItem;
import interfaces.IShowableObject;

public class Heart implements IShowableObject, ICollectibleItem {
    Game processing;
    private int width;
    private int height;
    private int heartX;
    private int heartY;

    public Heart(int x, int y, int width, int height, Game processing) {
        this.processing = processing;
        this.width = width;
        this.height = height;
        heartY = y;
        heartX = x;
    }

    @Override
    public void show() {
        processing.image(Images.Heart, heartX, heartY, width, height);

    }

    @Override
    public void Collect() {
        Game.lives++;
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

    public int getX() {
        return heartX;
    }

    public void setHeartX(int heartX) {
        this.heartX = heartX;
    }

    public int getY() {
        return heartY;
    }

    public void setHeartY(int heartY) {
        this.heartY = heartY;
    }

    @Override
    public void move() {
        heartY += 10;

    }
}
