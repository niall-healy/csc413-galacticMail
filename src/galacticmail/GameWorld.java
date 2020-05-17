package galacticmail;

import galacticmail.gameobject.GameObject;
import galacticmail.gameobject.immovable.Explosion;
import galacticmail.gameobject.immovable.Moon;
import galacticmail.gameobject.movable.Asteroid;
import galacticmail.gameobject.movable.Ship;
import galacticmail.hud.Hud;
import galacticmail.resourcetable.ResourceTable;

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
    private static JFrame jFrame;
    private Hud hud;
    private HighScores highScores;

    private static MapLoader mapLoader;
    private static ArrayList<GameObject> gameObjectArrayList;

    public static int tickCount = 0;
    public static int score = 0;
    public static int numLives;

    private static Boolean isRunning = true;
    private static Boolean gameOver = false;
    private static Boolean newHighScore = false;

    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld();
        gameWorld.init();

        try {
            while (isRunning) {
                if(numLives <= 0) {
                    gameOver = true;
                }
                mapLoader.update();
                for(int i = 0; i < gameObjectArrayList.size(); i++) {
                    gameObjectArrayList.get(i).update();
                }

                CollisionDetector.detectCollisions(gameObjectArrayList);

                gameWorld.hud.update();

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
        jFrame = new JFrame("Galactic Mail");
        this.world = new BufferedImage(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

        gameObjectArrayList = new ArrayList<>();
        ResourceTable.init();
        this.hud = new Hud();
        this.highScores = new HighScores();
        this.highScores.getScoresFromFile();

        Ship player = new Ship(170, 170, 0, 0, 0, ResourceTable.getImage("ship"));
        PlayerControl playerControl = new PlayerControl(player,
                KeyEvent.VK_SPACE,
                KeyEvent.VK_A,
                KeyEvent.VK_D);

        gameObjectArrayList.add(player);

        mapLoader = new MapLoader(player);

        numLives = 5;

        jFrame.setLayout(new BorderLayout());
        jFrame.add(this);
        jFrame.addKeyListener(playerControl);
        jFrame.setSize(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT + 30);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = world.createGraphics();

        g2.setColor(Color.black);
        g2.fillRect(0, 0, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);

        if(gameOver) {
            this.highScores.writeScoresToFile(score, "Sorry");
            ArrayList<String> scoreList =  this.highScores.getScoreArrayList();
            String gameOverMessage = "Game Over! Your Score: " + score;
            String restartMessage = "Exit and reopen to play again";

            g2.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 50));
            g2.setColor(Color.GREEN);
            g2.drawString(gameOverMessage, 75, 75);

            if(newHighScore) {
                g2.drawString("New High Score!!", 250, 160);
            }

            g2.drawString("High Scores", 320, 250);
            for(int i = 0; i < scoreList.size(); i++) {
                g2.drawString(scoreList.get(i), 375, (i * 50) + 320);
            }

            g2.drawString(restartMessage, 50, 650);
            GameWorld.isRunning = false;
        }
        else {
            drawBackground(buffer);
            drawObjects(buffer);

            g2.drawImage(world, 0, 0, null);

            hud.drawImage(g);
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
        Ship player = null;
        for(int i = 0; i < gameObjectArrayList.size(); i++) {
            if(gameObjectArrayList.get(i) instanceof Ship) {
                player = (Ship)gameObjectArrayList.get(i);
            } else {
                gameObjectArrayList.get(i).drawImage(g);
            }
        }

        if(player == null) {
            System.out.println("No ship reference in list");
        } else {
            player.drawImage(g);
        }
    }

    public static void setGameOver(Boolean bool) {
        GameWorld.gameOver = bool;
    }

    public static void setNewHighScore(Boolean newHighScore) {
        GameWorld.newHighScore = newHighScore;
    }

    public static void gameObjectArrayListAdd(GameObject object) {
        gameObjectArrayList.add(object);
    }

    public static void gameObjectArrayListRemove(int index) {
        if(gameObjectArrayList.get(index) instanceof Moon) {
            mapLoader.decrementMoonCount();
        }
        gameObjectArrayList.remove(index);

        //update all subsequent asteroidID's, moonID's, & ExplosionID's
        for(int i = index; i < gameObjectArrayList.size(); i++) {
            if(gameObjectArrayList.get(i) instanceof Asteroid) {
                ((Asteroid)gameObjectArrayList.get(i)).setAsteroidID(i);
            }
            else if(gameObjectArrayList.get(i) instanceof Moon) {
                ((Moon)gameObjectArrayList.get(i)).setMoonID(i);
            }
            else if(gameObjectArrayList.get(i) instanceof Explosion) {
                ((Explosion)gameObjectArrayList.get(i)).setExplosionID(i);
            }
        }
    }

    public static int getObjectListSize() {
        return gameObjectArrayList.size();
    }

    public static int getScore() {
        return score;
    }

    public static void incrementScore() {
        score += 1000;
    }

    public static void decrementScore() {
        if(score > 0) {
            score -= 1;
        }
    }

    public static int getLevel() {
        return mapLoader.getLevelNum();
    }

    public static void loseLife() {
        numLives--;
    }

    public static int getNumLives() {
        return numLives;
    }
}
