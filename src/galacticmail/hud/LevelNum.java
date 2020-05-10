package galacticmail.hud;

import galacticmail.GameWorld;

import java.awt.*;

public class LevelNum extends HudElement {

    private int levelNum;

    public LevelNum(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 25));
        g2d.drawString( "Level " + levelNum, this.getX(), this.getY());
    }

    @Override
    public void update() {
        this.levelNum = GameWorld.getLevel();
    }
}
