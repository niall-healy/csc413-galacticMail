package galacticmail.hud;

import galacticmail.GameWorld;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class LifeCount extends HudElement {

    private int numLives;
    private BufferedImage shipImage;
    private final int angle = 270;

    public LifeCount(int x, int y, Color color, BufferedImage image) {
        super(x, y, color);

        this.shipImage = image;
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < numLives; i++) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX() + (i * this.shipImage.getWidth()), this.getY());
            rotation.rotate(Math.toRadians(angle), this.shipImage.getWidth() / 2.0, this.shipImage.getHeight() / 2.0);
            g2d.drawImage(this.shipImage, rotation, null);
        }

    }

    @Override
    public void update() {
        this.numLives = GameWorld.getNumLives();
    }
}
