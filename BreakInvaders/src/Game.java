import java.util.ArrayList;
import java.util.List;

import Objects.SpaceShip;
import Objects.ball;
import interfaces.IShowableObject;
import processing.core.PApplet;

public class Game extends PApplet {
    List<IShowableObject> objects;

    @Override
    public void setup() {
        objects = new ArrayList<IShowableObject>();
        objects.add(new SpaceShip((int) (width * 0.1), (int) (width * 0.1), this));
        noCursor();
    }

    @Override
    public void draw() {
        background(0);

        for (IShowableObject obj : objects) {
            obj.move();
            obj.show();
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
}
