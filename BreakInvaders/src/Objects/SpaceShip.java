package Objects;

import interfaces.IShowableObject;
import processing.core.PApplet;
import processing.core.PImage;

public class SpaceShip implements IShowableObject {
    private int width;
    private int height;
    private int x, y;
    private PApplet processing;
    private PImage img;

    public SpaceShip(int width, int height, PApplet processing) {
        this.width = width;
        this.height = height;
        this.processing = processing;
        x = processing.mouseX;
        y = processing.height - height;
        img = processing.loadImage("../images/sps.png");
    }

    @Override
    public void show() {
        processing.image(img, x, y, width, height);
    }

    @Override
    public void move() {
        x = processing.mouseX - (width / 2);
    }

    @Override
    public int getY() {
        return y;
    }

}
