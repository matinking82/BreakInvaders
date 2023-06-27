package Objects;

import java.util.Random;

import images.Images;
import interfaces.ICollectibleItem;
import interfaces.IShowableObject;
import processing.core.PApplet;

public class DoubleScore implements ICollectibleItem, IShowableObject {
    PApplet processing;
    private int width;
    private int height;
    private int scoreX;
    private int scoreY;

    public DoubleScore(int x, int y, int width, int height, PApplet processing) {
        this.width = width;
        this.height = height;
        this.processing = processing;
        scoreY = y;
        scoreX = x;
    }

    @Override
    public void show() {
        processing.image(Images.Score, scoreX, scoreY, width, height);
    }

    @Override
    public void move() {
        scoreY += 10;

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

    private int randomNum(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start + 1) + start;
    }

}
