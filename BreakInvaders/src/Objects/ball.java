package Objects;

import images.Images;
import interfaces.IShowableObject;
import processing.core.PApplet;
import processing.core.PImage;

public class ball implements IShowableObject {
    PApplet processing;
    private int ellipseX;
    private int ellipseY;
    private int ellipseWidth;
    private int ellipseHeight;
    int i = 1;

    public ball(int ellipseX, int ellipseY, int ellipseHeight, int ellipseWidth, PApplet processing) {
        this.ellipseX = ellipseX;
        this.ellipseY = ellipseY;
        this.ellipseHeight = ellipseHeight;
        this.ellipseWidth = ellipseWidth;
        this.processing = processing;
    }

    @Override
    public void show() {

        PImage img = null;
        switch (i) {
            case 1:
                img = Images.Bullet1;
                i++;
                break;
            case 2:
                img = Images.Bullet2;
                i++;
                break;
            case 3:
                img = Images.Bullet3;
                i++;
                break;
            case 4:
                img = Images.Bullet4;
                i = 1;
                break;
        }

        processing.image(img, ellipseX, ellipseY, ellipseWidth, ellipseHeight);
    }

    public int getY() {
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
