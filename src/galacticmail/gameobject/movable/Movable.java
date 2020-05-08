package galacticmail.gameobject.movable;

import galacticmail.GameWorld;
import galacticmail.gameobject.GameObject;
import galacticmail.resourcetable.Resource;
import galacticmail.resourcetable.Sprite;

import java.awt.image.BufferedImage;

public abstract class Movable extends GameObject {

    private int vx;
    private int vy;

    private int prevX;
    private int prevY;

    private int R = 1;
    private final int ROTATION_SPEED = 2;

    public Movable(int x, int y, int vx, int vy, int angle, BufferedImage image) {
        super(x, y, angle, image);
        this.vx = vx;
        this.vy = vy;
    }

    protected void rotateLeft() {
        this.setAngle( this.getAngle() - this.ROTATION_SPEED );
    }

    protected void rotateRight() {
        this.setAngle( this.getAngle() + this.ROTATION_SPEED );
    }

    protected void moveBackwards() {
        this.prevX = this.getX();
        this.prevY = this.getY();

        vx = (int) Math.round(R * Math.cos(Math.toRadians( this.getAngle() )));
        vy = (int) Math.round(R * Math.sin(Math.toRadians( this.getAngle() )));
        this.setX( this.getX() - vx );
        this.setY( this.getY() - vy );
        checkBorder();
        this.setHitBoxLocation(this.getX(), this.getY());
    }

    protected void moveForwards() {
        this.prevX = this.getX();
        this.prevY = this.getY();

        vx = (int) Math.round(R * Math.cos(Math.toRadians( this.getAngle() )));
        vy = (int) Math.round(R * Math.sin(Math.toRadians( this.getAngle() )));
        this.setX( this.getX() + vx );
        this.setY( this.getY() + vy );
        checkBorder();
        this.setHitBoxLocation(this.getX(), this.getY());
    }

    private void checkBorder() {
        if (this.getX() < 0) {
            this.setX( GameWorld.SCREEN_WIDTH - 2 );
        }
        if (this.getX() >= GameWorld.SCREEN_WIDTH) {
            this.setX( 2 );
        }
        if (this.getY() < 0) {
            this.setY( GameWorld.SCREEN_HEIGHT - 2 );
        }
        if (this.getY() >= GameWorld.SCREEN_HEIGHT) {
            this.setY( 2 );
        }
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setR(int r) {
        R = r;
    }
}
