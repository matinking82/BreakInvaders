package Objects;

import interfaces.IHittableObject;
import interfaces.IShowableObject;
import processing.core.PApplet;

public class Brick implements IShowableObject ,IHittableObject   {
    
    PApplet processing;
    int level;
    private   int width;
    private   int height;
    // public static int speedY=-80;
    private int Blocky;
    private int Blockx;
    private int heart;


    public Brick(int width  , int height  , int level , PApplet processing)
    {
        this.width=width;
        this.height=height;
        this.level=level;
        switch (level) {
            case 1:
              heart=2;
              break;
            case 2:
                heart=4; 
                break;

            case 3:
                heart=6;
                break; 
            case 4:
               heart=10;
               break;
        
            default:
                break;
        }
        this.processing=processing;


    }
    public int getBlocky() {
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
    public void show()
    {
        processing.image(processing.loadImage("../images/chick"+level+".png"), Blockx, Blocky, width, height);


    }
    public void hit()
    {
        heart--;

    }

    @Override
    public void move() {

    }




    
}
