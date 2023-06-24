package Objects;

import interfaces.IShowableObject;
import processing.core.PApplet;

public class ball implements IShowableObject{
    PApplet processing;
    private int ellipseX;
    private int ellipseY;
    private int ellipseWidth;
    private int ellipseHeight;

   public ball(int ellipseX,int ellipseY,int ellipseHeight,int ellipseWidth,PApplet procesisng){
    this.ellipseX = ellipseX;
    this.ellipseY = ellipseY;
    this.ellipseHeight=ellipseHeight;
    this.ellipseWidth=ellipseWidth;
    this.processing=processing;
   }

    @Override
    public void show() {
        processing.image(processing.loadImage("../images/"), ellipseHeight, ellipseHeight, ellipseHeight, ellipseWidth, ellipseY, ellipseX, ellipseWidth, ellipseHeight);
        // for (int i=0;i<6;i++){
        //     Main.ellipses.add(new Ellipse(250,speed,));
        //     speed-=200;
        //     Main.ellipses.add(new Ellipse(100,speed,));
        //     speed-=200;
        //     Main.ellipses.add(new Ellipse(40,speed,));
        //     speed-=200;
        // }
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
    public int getEllipseY(){
        return ellipseY;
    }
    public int getEllipseX(){
        return ellipseX;
    }
    public void setEllipseY(int ellipseY){
        this.ellipseY=ellipseY;
    }
    public void setEllipseX(int ellipseX){
        this.ellipseX=ellipseX;
    }
    public int getEllipseHeight(){
        return this.ellipseHeight;
    }
    public int getEllipseWidth(){
        return this.ellipseWidth;
    }

    
}
