package galacticmail.hud;

import galacticmail.GameWorld;
import galacticmail.gameobject.movable.Ship;

import java.awt.*;

public class Score extends HudElement {

    private int score;

    public Score(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 25));
        g2d.drawString( "Score: " + score, this.getX(), this.getY());
    }

    @Override
    public void update() {
        this.score = GameWorld.getScore();
    }
}
