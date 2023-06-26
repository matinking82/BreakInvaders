package Objects;

import images.Images;
import interfaces.IShowableObject;
import processing.core.PApplet;

public class SpaceShip implements IShowableObject {
    private int width;
    private int height;
    private int x, y;
    private PApplet processing;

    public SpaceShip(int width, int height, PApplet processing) {
        this.width = width;
        this.height = height;
        this.processing = processing;
        x = processing.mouseX;
        y = processing.height - height;
    }

    @Override
    public void show() {
        processing.image(Images.SpaceShip, x, y, width, height);
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
