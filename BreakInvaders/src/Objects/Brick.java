package Objects;

import interfaces.IHittableObject;
import interfaces.IShowableObject;
import processing.core.PApplet;

public class Brick implements IShowableObject ,IHittableObject  {
    
    PApplet processing;
    int level;
    private   int width;
    private   int height;
    // public static int speedY=-80;
    private int Blocky;
    private int Blockx;


    Public Brick(int width  , int height  , int level , PApplet processing)
    {
        this.width=width;
        this.height=height;
        this.level=level;
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
        

    }
    public void hit()
    {
        level--;

    }
    





    
}
