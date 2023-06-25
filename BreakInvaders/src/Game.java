import java.util.ArrayList;
import java.util.List;

import Objects.Brick;
import Objects.SpaceShip;
import Objects.ball;
import interfaces.IShowableObject;
import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
    private int chickenCount;
    private int chickenRemain;
    private static int button=0;
    private static boolean gameOver;
    private PImage pauseImage;

    List<IShowableObject> objects;
    

    @Override
    public void setup() {
        objects = new ArrayList<IShowableObject>();
        objects.add(new SpaceShip((int) (width * 0.1), (int) (width * 0.1), this));
        //noCursor();
        pauseImage = loadImage("../images/pause.png");
    }

    @Override
    public void draw() {
        if (button == 1) {
            startGame();

        } else if (button == 2) {
            exit();
        } else if (button == 3) {


        } else {
            menu();
        }

        

    }

    @Override
    public void mouseClicked() {
        if (mouseButton == LEFT) {
            fire();
        } else {

        }
    }

    @Override
    public void settings() {
        fullScreen();
    }

    private void fire() {
        ball b1 = new ball(mouseX - (int) (width * 0.05), (int) (height * 0.8), (int) (width * 0.07),
                (int) (width * 0.04), this);
        ball b2 = new ball(mouseX + (int) (width * 0.05), (int) (height * 0.8), (int) (width * 0.07),
                (int) (width * 0.04), this);
        objects.add(b1);
        objects.add(b2);
    }
    private void menu()
    {
        gameOver=false;
        background(0);
        fill(122, 150, 235);
        textSize(60);
        text("BreakInvaders" ,570 ,120);

        fill(0 ,0 ,0);
        stroke(222, 207, 73);
        rect(580 ,300 ,380 ,40);
        fill(222, 207, 73);
        textSize(30);
        text("Start Game" ,685 ,330);

        fill(0 ,0 ,0);
        stroke(222, 207, 73);
        rect(580 ,360 ,380 ,40);
        fill(222, 207, 73);
        textSize(30);
        text("Records" ,710 ,390);
        
        fill(0 ,0 ,0 );
        stroke(222, 207, 73);
        rect(580 ,420 ,380 ,40);
        fill(222, 207, 73);
        textSize(30);
        text("Resume" ,710 ,450);
        

        fill(0 ,0 ,0);
        stroke(222, 207, 73);
        rect(580 ,480 ,380 ,40);
        fill(222, 207, 73);
        textSize(30);
        text("Exit" ,730  ,510);
        ButtonClicked();
    }

    public void ButtonClicked()
    {

        if(mouseX>580 && mouseX<960 && mouseY>300 &&mouseY<340 && mousePressed )
        {
            button=1;
        }
        else if(mouseX>580 && mouseX<960 && mouseY>360 && mouseY<400 && mousePressed) {
            button=3;
        }
        else if (mouseX>580 && mouseX<960 && mouseY>420 && mouseY<460 && mousePressed)
        {
            button=2;
        }
        else if(mouseX>580 && mouseX<960 && mouseY>480 && mouseY<520 && mousePressed )
        {
            exit();

        }
        else {
            button=0;

        }
        mousePressed=false;
    }
    private void startGame()
    {
        
        if(gameOver!=true)
        {
            
            background(0);
            textOnPage();
            for (IShowableObject obj : objects) {
                obj.move();
                obj.show();
            }
        }
        else{
            lost();
        }

        

    }
    // private void wone()
    // {
    //     for (int j=0 ; j<5000 ; j++){

    //         background(0);
    //     }
    //     fill(44, 181, 16);
    //     textSize(50);
    //     text("You Won!" , 100 , 250);


    //     fill(44, 181, 16);
    //     textSize(30);
    //     text("Lives: " , 155 , 300);

    //     fill(44, 181, 16);
    //     textSize(30);
    //     text("Score: " , 150 , 340);

    //     fill(20, 20, 20);
    //     stroke(44, 181, 16);
    //     rect(100 ,380 ,190 ,40);
    //     fill(44, 181, 16);
    //     textSize(30);
    //     text("Menu" ,160 ,410);

    //     fill(20, 20, 20);
    //     stroke(44, 181, 16);
    //     rect(100 ,440 ,190 ,40);
    //     fill(44, 181, 16);
    //     textSize(30);
    //     text("Exit" ,170 ,470);
    //     ButtonClicked4();
    // }

    // public void ButtonClicked4() {

    //     if (mouseX > 100 && mouseX < 290 && mouseY > 380 && mouseY < 420 && mousePressed) {
    //         button = 0;

    //     }
    //     else if (mouseX > 100 && mouseX < 290 && mouseY > 440 && mouseY < 480 && mousePressed) {
    //         button = 2;
    //     }
    // }

    public void lost()
    {

        for (int j=0 ; j<5000 ; j++){

            background(0);
        }
        background(0);
        fill(240, 0 , 10);
        textSize(50);
        text("GAME OVER!" , 66 , 300);

        fill(0 ,0 ,0);
        stroke(240, 0 , 10);
        rect(100 ,350 ,190 ,40);
        fill(240, 0 , 10);
        textSize(30);
        text("Menu" ,160 ,380);

        fill(0 ,0 ,0);
        stroke(240, 0 , 10);
        rect(100 ,410 ,190 ,40);
        fill(240, 0 , 10);
        textSize(30);
        text("Exit" ,170 ,440);
        gameOver=true;
        // ButtonClicked3();

    }
    // public void ButtonClicked3() {
    //     if (mouseX > 100 && mouseX < 290 && mouseY > 350 && mouseY < 390 && mousePressed) {

    //         button = 0;
    //     }
    //     else if (mouseX > 100 && mouseX < 290 && mouseY > 410 && mouseY < 450 && mousePressed) {
    //         button = 2;
    //     }
    // }

    public void textOnPage()
    {
        fill(201, 14, 20);
        textSize(35);
        image(pauseImage,width - 60 , 10, 50,50 );
        text("Lives :"  , 20,40);
        text("Score :" ,20 ,90);
        

    }

    // public void pause()
    // {
    //     fill(89, 32, 11);
    //     stroke(0 ,200 ,100);
    //     strokeWeight(3);
    //     rect(30 ,655 ,150 ,30);
    //     fill(0 ,200 ,100);
    //     textSize(30);
    //     text("Retuern" ,55 ,680);
    //     ButtonClicked2();
    // }
    // public void ButtonClicked2()
    // {
    //     if(mouseX>30 && mouseX<180 && mouseY>655 && mouseY<685&& mousePressed)
    //     {
    //         objects.clear();
    //         button=0;
    //     }
    // }


    

}
