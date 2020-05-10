package galacticmail.gameobject.movable;

import galacticmail.GameWorld;
import galacticmail.gameobject.immovable.Moon;

import java.awt.image.BufferedImage;

public class Ship extends Movable {

    private int tickCountGotLightning;
    private int tickLastLeftMoon;

    private int numLives;

    private int startingX;
    private int startingY;

    private boolean hasLightning;
    private Moon moonOn;

    private boolean launchPressed;
    private boolean rightPressed;
    private boolean leftPressed;


    public Ship(int x, int y, int vx, int vy, int angle, BufferedImage image) {
        super(x, y, vx, vy, angle, image);

        this.startingX = x;
        this.startingY = y;

        this.hasLightning = false;
        this.moonOn = null;

        this.numLives = 1;
        this.tickLastLeftMoon = 0;
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

        if(numLives <= 0) {
            GameWorld.setGameOver(true);
        }

        if(this.moonOn == null) {
            this.setRotationSpeed(2);
            this.moveForwards();
        }
        else {
            this.setRotationSpeed(3);
            GameWorld.decrementScore();
        }

        if (this.launchPressed && (GameWorld.tickCount - this.tickLastLeftMoon) > 30 && this.moonOn != null) {
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
        this.setX(this.startingX);
        this.setY(this.startingY);

        this.numLives--;

        this.setHitBoxLocation(this.startingX, this.startingY);
    }

    public int getNumLives() {
        return numLives;
    }

    public Moon getMoonOn() {
        return moonOn;
    }

    public void setMoonOn(Moon moonOn) {
        this.moonOn = moonOn;
    }
}
