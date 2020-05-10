package galacticmail.gameobject.movable;

import galacticmail.GameWorld;
import galacticmail.gameobject.GameObject;

import java.awt.image.BufferedImage;

public abstract class Movable extends GameObject {

    private int vx;
    private int vy;

    private int prevX;
    private int prevY;

    private int R = 1;
    private int rotationSpeed = 2;

    public Movable(int x, int y, int vx, int vy, int angle, BufferedImage image) {
        super(x, y, angle, image);
        this.vx = vx;
        this.vy = vy;
    }

    protected void rotateLeft() {
        this.setAngle( this.getAngle() - this.rotationSpeed );
    }

    protected void rotateRight() {
        this.setAngle( this.getAngle() + this.rotationSpeed );
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

    public void checkBorder() {
        if (this.getX() < -1 * this.getImage().getWidth()) {
            this.setX( GameWorld.SCREEN_WIDTH - 2 );
        }
        if (this.getX() >= GameWorld.SCREEN_WIDTH) {
            this.setX( (-1 * this.getImage().getWidth()) + 1 );
        }
        if (this.getY() < -1 * this.getImage().getHeight()) {
            this.setY( GameWorld.SCREEN_HEIGHT - 2 );
        }
        if (this.getY() >= GameWorld.SCREEN_HEIGHT) {
            this.setY( (-1 * this.getImage().getHeight()) + 1 );
        }
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public void setR(int r) {
        R = r;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
}
