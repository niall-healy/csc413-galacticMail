package galacticmail.hud;

import galacticmail.gameobject.movable.Ship;

import java.awt.*;

public abstract class HudElement {

    private int x;
    private int y;
    Color color;

    public HudElement(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract void drawImage(Graphics g);
    public abstract void update();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
