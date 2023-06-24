package Objects;

import interfaces.IShowableObject;
import processing.core.PApplet;
import interfaces.ICollectibleItem;


public class Blast implements IShowableObject,ICollectibleItem{
    private int blastX;
    private int blastY;
    private int blastHeight;
    private int blastWidth;
    PApplet processing;


    public Blast(int blastX,int blastY,int blastHeight,int blastWidth,PApplet processing){
     this.blastHeight=blastHeight;
     this.blastWidth=blastWidth;
     this.processing=processing;
    }

     @Override
    public void show() {
<<<<<<< HEAD
        processing.image(processing.loadImage("../images/Blast.png"), blastX, blastY, blastHeight, blastWidth);
        throw new UnsupportedOperationException("Unimplemented method 'show'");
=======
        processing.image(processing.loadImage("../images/"), blastX, blastY, blastHeight, blastWidth);
>>>>>>> 315ecb6e7e2fb0cd9d91fcf0c845a8279b973585
    }

    @Override
    public void Collect() {
    
    }

    @Override
    public void move() {
        this.blastY+=10;
    }
    public int getBlastX() {
        return this.blastX;
    }

    public void setBlastX(int blastX) {
        this.blastX = blastX;
    }

    public int getY() {
        return this.blastY;
    }

    public void setBlastY(int blastY) {
        this.blastY = blastY;
    }

    public int getBlastHeight() {
        return this.blastHeight;
    }

    public void setBlastHeight(int blastHeight) {
        this.blastHeight = blastHeight;
    }

    public int getBlastWidth() {
        return this.blastWidth;
    }

    public void setBlastWidth(int blastWidth) {
        this.blastWidth = blastWidth;
    }  
}
