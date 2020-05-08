package galacticmail;

import galacticmail.gameobject.GameObject;
import galacticmail.gameobject.movable.Ship;
import galacticmail.hud.Hud;
import galacticmail.resourcetable.Resource;
import galacticmail.resourcetable.ResourceTable;
import galacticmail.resourcetable.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameWorld extends JPanel {
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 736;
    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jFrame;
    private Hud hud;

    private static ArrayList<GameObject> gameObjectArrayList;

    public static int tickCount = 0;

    private static Boolean isRunning = true;
    private static Boolean gameOver = false;

    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld();
        gameWorld.init();

        try {
            while (isRunning) {
                gameObjectArrayList.forEach(GameObject::update);

                CollisionDetector.detectCollisions(gameObjectArrayList);

                //gameWorld.hud.update();

                gameWorld.repaint();
                tickCount++;
                //  System.out.println(gameWorld.t1);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }

    }

    private void init() {
        this.jFrame = new JFrame("Galactic Mail");
        this.world = new BufferedImage(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

        //this.hud = new Hud();

        gameObjectArrayList = new ArrayList<>();

        ResourceTable.init();

        Ship player = new Ship(170, 170, 0, 0, 0, ResourceTable.getImage("ship"));
        PlayerControl playerControl = new PlayerControl(player,
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D);

        gameObjectArrayList.add(player);

        //hud.init(player);

        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(playerControl);
        this.jFrame.setSize(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = world.createGraphics();

        g2.setColor(Color.black);
        g2.fillRect(0, 0, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);

        if(gameOver) {
            String gameOverMessage = "Game Over!";
            String restartMessage = "Exit and reopen to play again";

            g2.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 50));
            g2.setColor(Color.GREEN);
            g2.drawString(gameOverMessage, 125, 300);
            g2.drawString(restartMessage, 50, 400);
            GameWorld.isRunning = false;
        }
        else {
            drawBackground(buffer);
            drawObjects(buffer);

            g2.drawImage(world, 0, 0, null);

            //hud.drawImage(g);
        }
    }

    private void drawBackground(Graphics g) {
        BufferedImage backgroundImage = ResourceTable.getImage("background");

        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < SCREEN_WIDTH; i += backgroundImage.getWidth()) {
            for(int j = 0; j < SCREEN_HEIGHT; j += backgroundImage.getHeight()) {
                g2d.drawImage(backgroundImage, i, j, null);
            }
        }
    }

    private void drawObjects(Graphics g) {
        for(int i = 0; i < gameObjectArrayList.size(); i++) {
            gameObjectArrayList.get(i).drawImage(g);
        }
    }

    public static void setGameOver(Boolean bool) {
        GameWorld.gameOver = bool;
    }

}
