package galacticmail;

import galacticmail.gameobject.GameObject;
import galacticmail.gameobject.immovable.Explosion;
import galacticmail.gameobject.immovable.Immovable;
import galacticmail.gameobject.immovable.Moon;
import galacticmail.gameobject.immovable.PowerUp;
import galacticmail.gameobject.movable.Asteroid;
import galacticmail.gameobject.movable.Ship;
import galacticmail.resourcetable.ResourceTable;

import java.util.ArrayList;

public class CollisionDetector {

    //This is some bad code so strap yourself in
    public static void detectCollisions(ArrayList<GameObject> gameObjectArrayList) {
        GameObject outerLoopObject, innerLoopObject;

        //Loop through all objects in the ArrayList
        for(int outerLoopIndex = 0; outerLoopIndex < gameObjectArrayList.size(); outerLoopIndex++) {
            outerLoopObject = gameObjectArrayList.get(outerLoopIndex);

            //Ignore Immovable's in first loop (Moons, PowerUps)
            if( !(outerLoopObject instanceof Immovable) ){

                //Loop through all objects again
                for(int innerLoopIndex = 0; innerLoopIndex < gameObjectArrayList.size(); innerLoopIndex++) {
                    innerLoopObject = gameObjectArrayList.get(innerLoopIndex);

                    //Don't check an object against its own hitBox
                    if(innerLoopIndex != outerLoopIndex) {

                        //If objects are colliding
                        if(outerLoopObject.getHitBox().intersects( innerLoopObject.getHitBox() )) {
                            if(outerLoopObject instanceof Ship) {

                                if(innerLoopObject instanceof Asteroid) {
                                    shipHitAsteroid( (Ship)outerLoopObject, (Asteroid)innerLoopObject );
                                }
                                else if(innerLoopObject instanceof PowerUp) {
                                    shipHitPowerUp( (Ship)outerLoopObject, (PowerUp)innerLoopObject );
                                }
                                else if(innerLoopObject instanceof Moon) {
                                    shipHitMoon( (Ship)outerLoopObject, (Moon)innerLoopObject );
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public static void shipHitAsteroid(Ship ship, Asteroid asteroid) {
        if(ship.getMoonOn() == null) {
            ship.respawn();
            asteroid.selfDestruct();
            GameWorld.gameObjectArrayListAdd(new Explosion(asteroid.getX(), asteroid.getY(), 0, GameWorld.getObjectListSize()));
        }
    }

    public static void shipHitPowerUp(Ship ship, PowerUp powerUp) {

    }

    public static void shipHitMoon(Ship ship, Moon moon) {
        if(ship.getMoonOn() == null) {
            ship.setMoonOn(moon);

            //ship.setX(moon.getX() + (moon.getImage().getWidth()/2 - ship.getImage().getWidth()/2));
            //ship.setY(moon.getY() + (moon.getImage().getHeight()/2 - ship.getImage().getHeight()/2));
        }
    }
}
