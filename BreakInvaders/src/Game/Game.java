package Game;

import java.util.ArrayList;
import java.util.List;

import Objects.Brick;
import Objects.SpaceShip;
import Objects.ball;
import interfaces.IShowableObject;
import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
    public static int chickenCount=6;;
    private int chickenRemain;
    private static int button = 0;
    private static boolean gameOver;
    private PImage pauseImage;
    public static int lives=3;
    public static int score;
    static int multiplier = 1;
    public static int counter=0;

    public static List<IShowableObject> objects;
    List<ball> balls;

    @Override
    public void setup() {
        objects = new ArrayList<IShowableObject>();
        balls = new ArrayList<>();
        objects.add(new SpaceShip((int) (width * 0.1), (int) (width * 0.1), this));
        // addChicken();
        addChicken();
        addChicken();

        // noCursor();
        pauseImage = loadImage("../images/pause.png");
    }

    private void addChicken() {
        int level = (int) (random(1, 3));
        Brick brick=new Brick((int) (width * 0.085), (int) (width * 0.085), level,
        loadImage("../images/chick" + level + ".png"), this);
        objects.add(brick); 
        brick.chickenCountcheck();
                  

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
        balls.add(b1);
        balls.add(b2);
    }

    private void menu() {
        gameOver = false;
        background(0);
        fill(122, 150, 235);
        textSize(60);
        text("BreakInvaders", (width/2)-200, 120);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width/2)-190, 300, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Start Game", (width/2)-70, 330);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width/2)-190, 360, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Records", (width/2)-50, 390);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width/2)-190, 420, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Resume", (width/2)-50, 450);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width/2)-190, 480, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Exit", (width/2)-30, 510);
        ButtonClicked();
    }

    public void ButtonClicked() {

        if (mouseX > 580 && mouseX < 960 && mouseY > 300 && mouseY < 340 && mousePressed) {
            button = 1;
        } else if (mouseX > 580 && mouseX < 960 && mouseY > 360 && mouseY < 400 && mousePressed) {
            button = 3;
        } else if (mouseX > 580 && mouseX < 960 && mouseY > 420 && mouseY < 460 && mousePressed) {
            button = 2;
        } else if (mouseX > 580 && mouseX < 960 && mouseY > 480 && mouseY < 520 && mousePressed) {
            exit();

        } else {
            button = 0;

        }
        mousePressed = false;
    }

    private void startGame() {

        if (!gameOver) {
           if(chickenCount>0)
           {
                background(0);
                textOnPage();
    
                for (int i = 0; i < objects.size(); i++) {
                    IShowableObject obj = objects.get(i);
                    if (obj == null) {
                        continue;
                    }
                    obj.move();
                    obj.show();
                    if (obj.getY() > height) {
                        objects.remove(i);
                        if (obj instanceof Brick) {
                            loseHeart();
                        }
                        i--;
                    }
                }
                for (int i = 0; i < balls.size(); i++) {
                    ball ball1 = balls.get(i);
                    ball1.move();
                    ball1.show();
                    if (ball1.getY() < 0) {
                        balls.remove(i);
                        i--;
                    }
                    for (int j = 0; j < objects.size(); j++) {
                        if (objects.get(j) instanceof Brick) {
                            Brick brick = (Brick) objects.get(j);
                            if (isHit(ball1, brick)) {
                                if(brick.hit()){
                                    objects.remove(j);
                                    score += multiplier*brick.getScore();
                                }
    
                                balls.remove(i);
                                i--;
                                break;
                            }
                        }
                    }
                }

            }
            else if(chickenCount==0 && !gameOver){
               won();
           }


        } else {
           lost();
        }

    }
    private void won()
    {
    for (int j=0 ; j<5000 ; j++){

        background(0);
        }
        fill(44, 181, 16);
        textSize(50);
        text("You Won!" , 100 , 250);

        fill(44, 181, 16);
        textSize(30);
        text("Lives: " , 155 , 300);

        fill(44, 181, 16);
        textSize(30);
        text("Score: " , 150 , 340);

        fill(20, 20, 20);
        stroke(44, 181, 16);
        rect(100 ,380 ,190 ,40);
        fill(44, 181, 16);
        textSize(30);
        text("Menu" ,160 ,410);

        fill(20, 20, 20);
        stroke(44, 181, 16);
        rect(100 ,440 ,190 ,40);
        fill(44, 181, 16);
        textSize(30);
        text("Exit" ,170 ,470);
        ButtonClicked4();
    }

    public void ButtonClicked4() {

    if (mouseX > 100 && mouseX < 290 && mouseY > 380 && mouseY < 420 &&
        mousePressed) {
        button = 0;

        }
        else if (mouseX > 100 && mouseX < 290 && mouseY > 440 && mouseY < 480 &&
        mousePressed) {
        button = 2;
        }
    }

    public void lost() {

        for (int j = 0; j < 5000; j++) {

            background(0);
        }
        textAlign(CENTER, CENTER);
        background(0);
        fill(240, 0, 10);
        textSize(50);
        text("GAME OVER!", width/2, 300);

        fill(0, 0, 0);
        stroke(240, 0, 10);
        rect((width/2)-190, 350, 380, 50);
        fill(240, 0, 10);
        textSize(35);
        text("Menu", (width/2), 370);

        fill(0, 0, 0);
        stroke(240, 0, 10);
        rect((width/2)-190, 410, 380, 50);
        fill(240, 0, 10);
        textSize(35);
        text("Exit", (width/2) , 430);
        gameOver = true;
        ButtonClicked3();

    }
    public void ButtonClicked3() {
        if (mouseX >(width/2)-190  && mouseX <(width/2)+190  && mouseY > 350 && mouseY < 400 &&
        mousePressed) {

             button = 0;
        }
        else if (mouseX > (width/2)-190 && mouseX < (width/2)+190 && mouseY > 410 && mouseY < 460 &&
        mousePressed) {
            button = 2;
        }

        mousePressed=false;
    }

    public void textOnPage() {
        fill(201, 14, 20);
        textSize(35);
        image(pauseImage, width - 60, 10, 50, 50);
        text("Lives :"+lives, 20, 40);
        text("Score :"+score, 20, 90);

    }

    // public void pause()
    // {
    // fill(89, 32, 11);
    // stroke(0 ,200 ,100);
    // strokeWeight(3);
    // rect(30 ,655 ,150 ,30);
    // fill(0 ,200 ,100);
    // textSize(30);
    // text("Retuern" ,55 ,680);
    // ButtonClicked2();
    // }
    // public void ButtonClicked2()
    // {
    // if(mouseX>30 && mouseX<180 && mouseY>655 && mouseY<685&& mousePressed)
    // {
    // objects.clear();
    // button=0;
    // }
    // }

    private boolean isHit(ball ball1, Brick brick) {
        return (ball1.getEllipseX() >= brick.getBlockx() - ball1.getEllipseWidth()) &&
                (ball1.getEllipseX() <= brick.getBlockx() + brick.getWidth()) &&
                (ball1.getY() >= brick.getY() - ball1.getEllipseHeight())
                && (ball1.getY() <= brick.getY() + brick.getHeight());
    }
    
    public void loseHeart(){
        lives--;
        if(lives<=0)
        {
            gameOver=true;
        }

    }

}
