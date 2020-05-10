package galacticmail.gameobject.movable;

import galacticmail.GameWorld;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Asteroid extends Movable {
    private boolean hasDestructed;
    private boolean direction;
    private int asteroidID;

    public Asteroid(int x, int y, int angle, boolean direction, int asteroidID, BufferedImage image) {
        super(x, y, (int)Math.round(1 * Math.cos(Math.toRadians(angle))), (int)Math.round(1 * Math.sin(Math.toRadians(angle))), angle, image);

        this.asteroidID = asteroidID;
        this.direction = direction;
        this.hasDestructed = false;
        this.setRotationSpeed(1);
    }

    @Override
    public void update() {
        if(this.direction) {
            this.rotateLeft();
        } else {
            this.rotateRight();
        }
        this.move();
    }

    public void selfDestruct() {
        if( !hasDestructed ) {
            GameWorld.gameObjectArrayListRemove(asteroidID);
        }

        hasDestructed = true;
    }

    public void setAsteroidID(int asteroidID) {
        this.asteroidID = asteroidID;
    }

    public void move() {
        this.setX( this.getX() + this.getVx() );
        this.setY( this.getY() + this.getVy() );

        this.checkBorder();
        this.setHitBoxLocation(this.getX(), this.getY());
    }
}
