package Objects;

import interfaces.IShowableObject;
import Game.Game;
import images.Images;
import interfaces.ICollectibleItem;

public class Blast implements IShowableObject, ICollectibleItem {
    private int blastX;
    private int blastY;
    private int blastHeight;
    private int blastWidth;
    Game processing;

    public Blast(int blastX, int blastY, int blastHeight, int blastWidth, Game processing) {
        this.blastHeight = blastHeight;
        this.blastWidth = blastWidth;
        this.processing = processing;
        this.blastY = blastY;
        this.blastX = blastX;
    }

    @Override
    public void show() {
        processing.image(Images.Blast, blastX, blastY, blastHeight, blastWidth);
    }

    @Override
    public void Collect() {
        for (int i = 0; i < Game.objects.size(); i++) {
            IShowableObject obj = Game.objects.get(i);
            if (obj instanceof Brick) {
                if (((Brick) obj).hit()) {
                    Game.objects.remove(obj);
                    i--;
                }
            }
        }
    }

    @Override
    public void move() {
        this.blastY += 10;
    }

    public int getX() {
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
