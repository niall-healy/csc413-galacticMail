package galacticmail.hud;

import galacticmail.gameobject.movable.Ship;
import galacticmail.GameWorld;

import java.awt.*;
import java.util.ArrayList;

public class Hud {

    private ArrayList<HudElement> hudElements;

    //BEWARE: Magic numbers ahead
    public Hud() {
        this.hudElements = new ArrayList<>();

        this.hudElements.add(new LevelNum(50, GameWorld.SCREEN_HEIGHT - 90, Color.GREEN));
        this.hudElements.add(new Score(50, GameWorld.SCREEN_HEIGHT - 35, Color.GREEN));
    }

    public void update() {
        this.hudElements.forEach(HudElement::update);
    }

    public void drawImage(Graphics g) {
        this.hudElements.forEach(hudElement -> hudElement.drawImage(g));
    }
}
