package Game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DbContext.interfaces.IDatabaseContext;
import DbContext.models.GameRecord;
import DbContext.services.DatabaseContext;
import Objects.Brick;
import Objects.SpaceShip;
import Objects.ball;
import interfaces.IShowableObject;
import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
    public static int chickenCount = 10;
    private static int button = 0;
    private static boolean gameOver;
    private PImage pauseImage;
    public static int lives = 3;
    public static int score;
    static int multiplier = 1;
    IDatabaseContext db = new DatabaseContext();
    private boolean checkSave = false;
    private boolean checkBoss = true;

    public static List<IShowableObject> objects;
    List<ball> balls;

    @Override
    public void setup() {
        objects = new ArrayList<IShowableObject>();
        balls = new ArrayList<>();

        // noCursor();
        pauseImage = loadImage("../images/pause.png");
    }

    private void addChicken() {

        if (chickenCount <= 0) {
            return;
        }
        int level = (int) (random(1, 3));
        Brick brick = new Brick((int) (width * 0.085), (int) (width * 0.085), level,
                loadImage("../images/chick" + level + ".png"), this);
        objects.add(brick);
        chickenCount--;

    }

    @Override
    public void draw() {
        if (button == 1) {
            startGame();

        } else if (button == 2) {
            Records();
        } else if (button == 4) {
            pauseMenu();

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
        textAlign(CENTER, CENTER);

        background(0);
        fill(122, 150, 235);
        textSize(60);
        text("BreakInvaders", (width / 2), 120);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width / 2) - 190, 300, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Start Game", (width / 2) , 320);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width / 2) - 190, 360, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Records", (width / 2) , 375);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width / 2) - 190, 420, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Exit", (width / 2), 435);
        ButtonClicked();
    }

    public void ButtonClicked() {

        if (mouseX > 580 && mouseX < 960 && mouseY > 300 && mouseY < 340 && mousePressed) {
            resetData();
            button = 1;
        } else if (mouseX > 580 && mouseX < 960 && mouseY > 360 && mouseY < 400 && mousePressed) {
            button = 2;
        } else if (mouseX > 580 && mouseX < 960 && mouseY > 420 && mouseY < 460 && mousePressed) {
            exit();
        } else {
            button = 0;

        }
        mousePressed = false;
    }

    private void startGame() {

        background(0);
        if (!gameOver) {
            if (chickenCount > 0 || checkChickens()) {
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
                                if (brick.hit()) {
                                    objects.remove(j);
                                    chickenCount--;
                                    score += multiplier * brick.getScore();
                                    addChicken();
                                }

                                balls.remove(i);
                                i--;
                                break;
                            }
                        }
                    }
                }

            } else {

                if (checkBoss) {

                    objects.add(new Brick((int) (width * 0.2), (int) (width * 0.2), 4,
                            loadImage("../images/chick" + 4 + ".png"), this));
                    checkBoss = false;
                } else {
                    won();
                }
            }
        } else {
            lost();
        }
    }

    private boolean checkChickens() {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Brick) {
                return true;
            }
        }
        return false;
    }

    private void won() {
        background(0);
        textAlign(CENTER, CENTER);
        background(0);
        fill(48, 230, 60);
        textSize(50);
        text("You Won!", width / 2, 300);


        fill(0, 0, 0);
        stroke(48, 230, 60);
        rect((width / 2) - 190, 400, 380, 50);
        fill(48, 230, 60);
        textSize(35);
        text("Menu", (width / 2), 420);

        fill(0, 0, 0);
        stroke(48, 230, 60);
        rect((width / 2) - 190, 460, 380, 50);
        fill(48, 230, 60);
        textSize(35);
        text("Exit", (width / 2), 480);
        if (!checkSave) {
            Date date = new Date();
            db.AddGameRecord(new GameRecord(date.toString(), score));
            checkSave = true;
            score+=1000;

        }
        textSize(20);
        text("Lives :" + lives, (width / 2) - 100, 350);
        text("Score :" + score, (width / 2) + 100, 350);
        ButtonClicked4();

    }

    public void textOnPage() {
        fill(201, 14, 20);
        textSize(35);
        image(pauseImage, width - 60, 10, 50, 50);
        text("Lives :" + lives, 20, 40);
        text("Score :" + score, 20, 90);
        ButtonClicked2();

    }

    public void ButtonClicked2() {
        if (mouseX > width - 60 && mouseX < width - 10 && mouseY > 10 && mouseY < 60 && mousePressed) {
            button = 4;
        }
        mousePressed = false;
    }

    public void ButtonClicked4() {

        if (mouseX > (width / 2) - 190 && mouseX < (width / 2) + 190 && mouseY > 400 && mouseY < 450 &&
                mousePressed) {
            button = 0;

        } else if (mouseX > (width / 2) - 190 && mouseX < (width / 2) + 190 && mouseY > 460 && mouseY < 510 &&
                mousePressed) {
            exit();
        }
        mousePressed = false;
    }

    public void lost() {

        background(0);
        textAlign(CENTER, CENTER);
        background(0);
        fill(240, 0, 10);
        textSize(50);
        text("GAME OVER!", width / 2, 300);

        fill(0, 0, 0);
        stroke(240, 0, 10);
        rect((width / 2) - 190, 350, 380, 50);
        fill(240, 0, 10);
        textSize(35);
        text("Menu", (width / 2), 370);

        fill(0, 0, 0);
        stroke(240, 0, 10);
        rect((width / 2) - 190, 410, 380, 50);
        fill(240, 0, 10);
        textSize(35);
        text("Exit", (width / 2), 430);
        // gameOver = true;

        if (!checkSave) {
            Date date = new Date();
            db.AddGameRecord(new GameRecord(date.toString(), score));
            checkSave = true;
        }
        ButtonClicked3();

    }

    private void resetData() {
        lives = 3;
        score = 0;
        chickenCount = 10;
        checkBoss = true;
        gameOver = false;
        objects.clear();
        balls.clear();
        objects.add(new SpaceShip((int) (width * 0.1), (int) (width * 0.1), this));
        // addChicken();
        addChicken();
        addChicken();

    }

    public void ButtonClicked3() {

        if (mouseX > (width / 2) - 190 && mouseX < (width / 2) + 190 && mouseY > 350 && mouseY < 400 &&
                mousePressed) {
            button = 0;
        } else if (mouseX > (width / 2) - 190 && mouseX < (width / 2) + 190 && mouseY > 410 && mouseY < 460 &&
                mousePressed) {
            exit();

        }
        mousePressed = false;
    }

    private boolean isHit(ball ball1, Brick brick) {
        return (ball1.getEllipseX() >= brick.getBlockx() - ball1.getEllipseWidth()) &&
                (ball1.getEllipseX() <= brick.getBlockx() + brick.getWidth()) &&
                (ball1.getY() >= brick.getY() - ball1.getEllipseHeight())
                && (ball1.getY() <= brick.getY() + brick.getHeight());
    }

    public void loseHeart() {
        lives--;
        chickenCount--;
        if (lives <= 0) {
            gameOver = true;
        }

    }

    public void Records() {
        background(0);
        List<GameRecord> list = db.getTopGameRecords();
        int fixSize = 15;

        fill(240, 0, 10);
        textSize(30);
        text("top 10 Records", width/2, 50);
        int i=1;

        for (GameRecord gameRecord : list) {

            fill(240, 0, 10);
            textSize(30);
            text(i+" - Date :" + gameRecord.getDate(), (width / 2) - 300, 80 + fixSize);

            fill(240, 0, 10);
            textSize(30);
            text(" Score :" + gameRecord.getScore(), (width / 2) + 300, 80 + fixSize);
            i++;

            fixSize += 50;

        }

        if (mousePressed) {
            button = 0;

        }
        mousePressed = false;

    }

    public void pauseMenu() {

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width / 2) - 190, 360, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Records", (width / 2) - 50, 390);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width / 2) - 190, 420, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Resume", (width / 2) - 50, 450);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width / 2) - 190, 480, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Exit", (width / 2) - 30, 510);
        ButtonClicked5();

    }

    public void ButtonClicked5() {

        if (mouseX > (width / 2) - 190 && mouseX < (width / 2) + 190 && mouseY > 360 && mouseY < 400 && mousePressed) {
            button = 2;
        } else if (mouseX > (width / 2) - 190 && mouseX < (width / 2) + 190 && mouseY > 420 && mouseY < 460
                && mousePressed) {
            button = 1;
        } else if (mouseX > (width / 2) - 190 && mouseX < (width / 2) + 190 && mouseY > 480 && mouseY < 520
                && mousePressed) {
            exit();

        } else {
            button = 4;

        }
        mousePressed = false;
    }

}
