package galacticmail;

import galacticmail.gameobject.movable.Ship;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerControl implements KeyListener {

    private Ship ship;
    private final int launch;
    private final int right;
    private final int left;

    public PlayerControl(Ship ship, int launch, int left, int right) {
        this.ship = ship;
        this.launch = launch;
        this.right = right;
        this.left = left;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == launch) {
            this.ship.toggleLaunchPressed();
        }
        if (keyPressed == left) {
            this.ship.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.ship.toggleRightPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == launch) {
            this.ship.unToggleLaunchPressed();
        }
        if (keyReleased  == left) {
            this.ship.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.ship.unToggleRightPressed();
        }
    }
}
