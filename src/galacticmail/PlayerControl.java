package galacticmail;

import galacticmail.gameobject.movable.Ship;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerControl implements KeyListener {

    private Ship ship;
    private final int up;
    private final int down;
    private final int right;
    private final int left;

    public PlayerControl(Ship ship, int up, int down, int left, int right) {
        this.ship = ship;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.ship.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.ship.toggleDownPressed();
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
        if (keyReleased  == up) {
            this.ship.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.ship.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.ship.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.ship.unToggleRightPressed();
        }
    }
}
