package Objects;

import interfaces.IShowableObject;
import processing.core.PApplet;

public class ball implements IShowableObject {
    PApplet processing;
    private int ellipseX;
    private int ellipseY;
    private int ellipseWidth;
    private int ellipseHeight;
    int i = 0;

    public ball(int ellipseX, int ellipseY, int ellipseHeight, int ellipseWidth, PApplet processing) {
        this.ellipseX = ellipseX;
        this.ellipseY = ellipseY;
        this.ellipseHeight = ellipseHeight;
        this.ellipseWidth = ellipseWidth;
        this.processing = processing;
    }

    @Override
    public void show() {
        processing.image(processing.loadImage("../images/bullet/" + Integer.toString((i % 8) / 2 + 1) + ".png"),
                ellipseX, ellipseY, ellipseWidth, ellipseHeight);
        i++;
    }

    public int getEllipseY() {
        return ellipseY;
    }

    public int getEllipseX() {
        return ellipseX;
    }

    public void setEllipseY(int ellipseY) {
        this.ellipseY = ellipseY;
    }

    public void setEllipseX(int ellipseX) {
        this.ellipseX = ellipseX;
    }

    public int getEllipseHeight() {
        return this.ellipseHeight;
    }

    public int getEllipseWidth() {
        return this.ellipseWidth;
    }

    @Override
    public void move() {
        ellipseY -= 30;
    }

}
