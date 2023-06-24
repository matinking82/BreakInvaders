package Objects;

import java.util.Random;

import interfaces.IHittableObject;
import interfaces.IShowableObject;
import processing.core.PApplet;
import processing.core.PImage;

public class Brick implements IShowableObject ,IHittableObject   {
    
    PApplet processing;
    int level;
    private int width;
    private int height;
    // public static int speedY=-80;
    private int Blocky;
    private int Blockx;
    private int heart;
    private PImage img;


    public Brick(int width  , int height  , int level , PApplet processing)
    {
        this.width=width;
        this.height=height;
        this.level=level;
        img = processing.loadImage("../images/chick"+level+".png");
        switch (level) {
            case 1:
              this.heart=2;
              break;
            case 2:
                this.heart=4; 
                break;

            case 3:
                this.heart=6;
                break; 

            case 4:
               this.heart=10;
               break;
        
            default:
                break;
        }
        this.processing=processing;
        Blocky = -height;
        Blockx = randomNum(10, processing.width-width-10);


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
    public void show()
    {
        processing.image(img, Blockx, Blocky, width, height);


    }
    public void hit()
    {
        heart--;
    }

    @Override
    public void move() {
        Blocky += 10;
    }


    private int randomNum(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start + 1) + start;
    }
    
}
