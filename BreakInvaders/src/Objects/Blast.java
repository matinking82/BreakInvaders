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
        processing.image(processing.loadImage("../images/"), blastX, blastY, blastHeight, blastWidth);
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

    @Override
    public void Collect() {
        throw new UnsupportedOperationException("Unimplemented method 'Collect'");
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    public int getBlastX() {
        return this.blastX;
    }

    public void setBlastX(int blastX) {
        this.blastX = blastX;
    }

    public int getBlastY() {
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
