package galacticmail.gameobject.movable;

import galacticmail.GameWorld;
import galacticmail.gameobject.immovable.Moon;
import galacticmail.resourcetable.ResourceTable;

import java.awt.image.BufferedImage;

public class Ship extends Movable {

    private int tickCountGotLightning;
    private int tickLastLeftMoon;
    private int tickLastTookDamage;

    private boolean hasLightning;
    private Moon moonOn;

    private boolean launchPressed;
    private boolean rightPressed;
    private boolean leftPressed;


    public Ship(int x, int y, int vx, int vy, int angle, BufferedImage image) {
        super(x, y, vx, vy, angle, image);

        this.hasLightning = false;
        this.moonOn = null;

        this.tickLastLeftMoon = 0;
        this.tickLastTookDamage = 0;
    }

    public void toggleLaunchPressed() {
        this.launchPressed = true;
    }

    public void toggleRightPressed() {
        this.rightPressed = true;
    }

    public void toggleLeftPressed() {
        this.leftPressed = true;
    }

    public void unToggleLaunchPressed() {
        this.launchPressed = false;
    }

    public void unToggleRightPressed() {
        this.rightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.leftPressed = false;
    }

    @Override
    public void update() {

        if(this.moonOn == null) {
            this.setRotationSpeed(2);
            this.setImage(ResourceTable.getImage("ship"));
            this.moveForwards();
        }
        else {
            this.setRotationSpeed(3);
            this.setImage(ResourceTable.getImage("landed"));
            GameWorld.decrementScore();
        }

        if (this.launchPressed && (GameWorld.tickCount - this.tickLastLeftMoon) > 50 && this.moonOn != null) {
            GameWorld.incrementScore();
            this.moonOn.selfDestruct();
            this.moonOn = null;
            this.tickLastLeftMoon = GameWorld.tickCount;
        }

        if (this.leftPressed) {
            this.rotateLeft();
        }
        if (this.rightPressed) {
            this.rotateRight();
        }

        if(GameWorld.tickCount - tickCountGotLightning > 576) {
            hasLightning = false;
        }

    }

    @Override
    public String toString() {
        return "x=" + this.getX() + ", y=" + this.getY() + ", angle=" + this.getAngle();
    }

    public void gotLightning() {
        this.tickCountGotLightning = GameWorld.tickCount;
        this.hasLightning = true;
    }

    public void respawn() {
        if(GameWorld.tickCount - this.tickLastTookDamage >= 50) {
            GameWorld.loseLife();

            this.tickLastTookDamage = GameWorld.tickCount;
        }
    }

    public Moon getMoonOn() {
        return moonOn;
    }

    public void setMoonOn(Moon moonOn) {
        this.moonOn = moonOn;
    }
}
