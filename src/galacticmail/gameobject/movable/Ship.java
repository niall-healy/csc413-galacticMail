package galacticmail.gameobject.movable;

import galacticmail.GameWorld;
import galacticmail.resourcetable.Resource;
import galacticmail.resourcetable.Sprite;

import java.awt.image.BufferedImage;

public class Ship extends Movable {

    private int tickCountGotLightning;

    private int numLives;

    private int startingX;
    private int startingY;

    private boolean hasLightning;

    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean shootPressed;


    public Ship(int x, int y, int vx, int vy, int angle, BufferedImage image) {
        super(x, y, vx, vy, angle, image);

        this.startingX = x;
        this.startingY = y;

        this.hasLightning = false;

        this.numLives = 3;
    }

    public void toggleUpPressed() {
        this.upPressed = true;
    }

    public void toggleDownPressed() {
        this.downPressed = true;
    }

    public void toggleRightPressed() {
        this.rightPressed = true;
    }

    public void toggleLeftPressed() {
        this.leftPressed = true;
    }

    public void unToggleUpPressed() {
        this.upPressed = false;
    }

    public void unToggleDownPressed() {
        this.downPressed = false;
    }

    public void unToggleRightPressed() {
        this.rightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.leftPressed = false;
    }

    @Override
    public void update() {

        if(numLives == 0) {
            GameWorld.setGameOver(true);
        }

        if (this.upPressed) {
            this.moveForwards();
        }
        if (this.downPressed) {
            this.moveBackwards();
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
}
