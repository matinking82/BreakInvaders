package Objects;

import interfaces.IShowableObject;
import processing.core.PApplet;
import interfaces.ICollectibleItem;

public class Sheild implements IShowableObject,ICollectibleItem{
   PApplet processing;
    private   int sheildwidth;
    private   int sheildheight;
    private   int sheildX;
    private   int sheildY;
    static boolean sheildAffect=false;


   public Sheild(int sheildX,int setEllipseY,int sheildheight,int sheildwidth,PApplet procesisng){
    
    this.sheildheight=sheildheight;
    this.sheildwidth=sheildwidth;
    this.processing=processing;
   }

    @Override
    public void show() {
     //processing.image(processing.loadImage("../images/"), sheildX, sheildX, sheildX, sheildX, sheildwidth, sheildheight, sheildY, sheildX);
    }

    @Override
    public void Collect() {

    }
    public int getShieldX(){
        return this.sheildX;
    }
    public int getShieldY(){
        return this.sheildY;
    }
    public int getShieldHeight(){
        return this.sheildheight;
    }
    public int getShieldWidth(){
        return this.sheildwidth;
    }
    public void setSheildX(int x){
    this.sheildX=x;
    }
    public void setSheildY(int y){
        this.sheildY=y;
    }
    public void setSheildHeight(int h){
      this.sheildheight=h;
      }
    public void setShieldWidth(int w){
       this.sheildwidth=w;
         }
    
}
