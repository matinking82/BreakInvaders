package Game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import DbContext.interfaces.IDatabaseContext;
import DbContext.models.GameRecord;
import DbContext.services.DatabaseContext;
import Objects.Blast;
import Objects.Brick;
import Objects.DoubleB;
import Objects.DoubleScore;
import Objects.Heart;
import Objects.Sheild;
import Objects.SpaceShip;
import Objects.ball;
import images.Images;
import interfaces.ICollectibleItem;
import interfaces.IShowableObject;
import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
    public static int chickenCount = 50;
    private static int button = 0;
    private static boolean gameOver;
    public static int lives = 3;
    public static int Shield = 0;
    public static int score;
    public static int DoubleBullet = 1;
    IDatabaseContext db = new DatabaseContext();
    private boolean checkSave = false;
    private boolean checkBoss = true;

    public static List<IShowableObject> objects;
    List<ball> balls;

    @Override
    public void setup() {
        objects = new ArrayList<IShowableObject>();
        balls = new ArrayList<>();

        Images.Pause = loadImage("../images/pause.png");
        Images.Blast = loadImage("../images/Blast.png");
        Images.Heart = loadImage("../images/heart.png");
        Images.DoubleB = loadImage("../images/DoubleB.png");
        Images.Score = loadImage("../images/DoubleScore.png");
        Images.Shield = loadImage("../images/shield.png");
        Images.SpaceShip = loadImage("../images/sps.png");
        Images.chicken1 = loadImage("../images/chick1.png");
        Images.chicken2 = loadImage("../images/chick2.png");
        Images.chicken3 = loadImage("../images/chick3.png");
        Images.chicken4 = loadImage("../images/chick4.png");
        Images.Bullet1 = loadImage("../images/bullet/1.png");
        Images.Bullet2 = loadImage("../images/bullet/2.png");
        Images.Bullet3 = loadImage("../images/bullet/3.png");
        Images.Bullet4 = loadImage("../images/bullet/4.png");
    }

    public void addChicken() {

        if (chickenCount <= 0) {
            return;
        }
        int level = randomNum(1, 3);

        Brick brick = new Brick((int) (width * 0.085), (int) (width * 0.085), level,
                getChickByLevel(level), this);
        objects.add(brick);
        chickenCount--;

    }

    private PImage getChickByLevel(int level) {
        switch (level) {
            case 1:
                return Images.chicken1;
            case 2:
                return Images.chicken2;
            case 3:
                return Images.chicken3;
            case 4:
                return Images.chicken4;
        }

        return Images.chicken1;
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
        text("Start Game", (width / 2), 320);

        fill(0, 0, 0);
        stroke(222, 207, 73);
        rect((width / 2) - 190, 360, 380, 40);
        fill(222, 207, 73);
        textSize(30);
        text("Records", (width / 2), 375);

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

    private int randomNum(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start + 1) + start;
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
                    if (obj instanceof ICollectibleItem) {
                        ICollectibleItem c = (ICollectibleItem) obj;
                        if (c.getY() > height - (0.15 * width)) {
                            if (c.getX() > mouseX - (0.1 * width) && c.getX() < mouseX + (0.05 * width)) {
                                objects.remove(obj);
                                i--;
                                c.Collect();
                                continue;
                            }
                        }
                    }
                    if (obj.getY() > height) {
                        objects.remove(i);
                        if (obj instanceof Brick) {
                            loseHeart();
                        }
                        i--;
                        continue;
                    }
                }
                for (int i = 0; i < balls.size(); i++) {
                    ball ball1 = balls.get(i);
                    ball1.move();
                    ball1.show();
                    if (ball1.getY() < 0) {
                        balls.remove(ball1);
                        i--;
                        continue;
                    }
                    for (int j = 0; j < objects.size(); j++) {
                        if (objects.get(j) instanceof Brick) {
                            Brick brick = (Brick) objects.get(j);
                            if (isHit(ball1, brick)) {
                                int loop = (DoubleBullet > 0) ? 2 : 1;
                                DoubleBullet = DoubleBullet + 1 - loop;
                                for (int k = 0; k < loop; k++) {
                                    if (brick.hit()) {
                                        objects.remove(brick);
                                        j--;
                                        score += brick.getScore();
                                        addItem(brick.getBlockx(), brick.getY());
                                        addChicken();
                                        break;
                                    }
                                }

                                balls.remove(ball1);
                                i--;
                                break;
                            }
                        }
                    }
                }

            } else {

                if (checkBoss) {

                    objects.add(new Brick((int) (width * 0.2), (int) (width * 0.2), 4,
                            Images.chicken4, this));
                    objects.add(new Brick((int) (width * 0.2), (int) (width * 0.2), 4,
                            Images.chicken4, this));
                    checkBoss = false;
                } else {
                    won();
                }
            }
        } else {
            lost();
        }
    }

    private void addItem(int x, int y) {
        int i = randomNum(1, 10);
        switch (i) {
            case 1: {
                objects.add(new Heart(x, y, (int) (width * 0.05), (int) (width * 0.05), this));
                break;
            }
            case 2: {
                objects.add(new Blast(x, y, (int) (width * 0.05), (int) (width * 0.05), this));
                break;
            }
            case 3: {
                objects.add(new Sheild(x, y, (int) (width * 0.05), (int) (width * 0.05), this));
                break;
            }
            case 4: {
                objects.add(new DoubleScore(x, y, (int) (width * 0.05), (int) (width * 0.05), this));
                break;
            }
            case 5: {
                objects.add(new DoubleB(x, y, (int) (width * 0.05), (int) (width * 0.05), this));
                break;
            }
            default:
                break;
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
            score += 1000;
            Date date = new Date();
            db.AddGameRecord(new GameRecord(date.toString(), score));
            checkSave = true;

        }
        textSize(20);
        text("Lives :" + lives, (width / 2) - 100, 350);
        text("Score :" + score, (width / 2) + 100, 350);
        ButtonClicked4();

    }

    public void textOnPage() {
        fill(201, 14, 20);
        textSize(35);
        image(Images.Pause, width - 60, 10, 50, 50);
        text("Lives :" + lives, 20, 40);
        text("Shield :" + Shield, 20, 90);
        text("Score :" + score, 20, 140);
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
        chickenCount = 50;
        checkBoss = true;
        checkSave = false;
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
        if (Shield <= 0) {
            lives--;
        } else {
            Shield--;
        }
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
        text("top 10 Records", width / 2, 50);
        int i = 1;

        for (GameRecord gameRecord : list) {

            fill(240, 0, 10);
            textSize(30);
            text(i + " - Date :" + gameRecord.getDate(), (width / 2) - 300, 80 + fixSize);

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
