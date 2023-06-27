package Objects;

import java.util.Random;

import Game.Game;
import interfaces.IHittableObject;
import interfaces.IShowableObject;
import processing.core.PImage;

public class Brick implements IShowableObject, IHittableObject {

    Game processing;
    int level;
    private int width;
    private int height;
    // public static int speedY=-80;
    private int Blocky;
    private int Blockx;
    private int heart;
    private PImage img;
    boolean let = true;

    public Brick(int width, int height, int level, PImage img, Game processing) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.img = img;
        switch (level) {
            case 1:
                this.heart = 2;
                break;
            case 2:
                this.heart = 3;
                break;

            case 3:
                this.heart = 5;
                break;

            case 4:
                this.heart = 20;
                break;

            default:
                break;
        }
        this.processing = processing;
        Blocky = -height;
        Blockx = randomNum(10, processing.width - width - 10);

    }

    public int getY() {
        return Blocky;
    }

    public void setBlocky(int blocky) {
        Blocky = blocky;
    }

    public int getBlockx() {
        return Blockx;
    }

    public void setBlockx(int blockx) {
        Blockx = blockx;
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

    public void show() {
        processing.image(img, Blockx, Blocky, width, height);

    }

    public boolean hit() {
        heart--;
        return heart <= 0;
    }

    @Override
    public void move() {
        Blocky += 3;
        if (let && Blocky >= 2 * height) {
            let = false;
            processing.addChicken();
        }
    }

    public int getScore() {
        switch (level) {
            case 1:
                return 50;
            case 2:
                return 100;
            case 3:
                return 500;
            case 4:
                return 2000;
            default:
                return 0;
        }
    }

    private int randomNum(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start + 1) + start;
    }

}
