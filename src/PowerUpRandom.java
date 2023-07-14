import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class PowerUpRandom extends JPanel {
    public static int randomLuckX;
    public static int randomLuckY;
    public static int randomLuck = 3;
    public static boolean verifyLuck = false;
    public GameGUI game;
    public int powerUpLuckTime = 0;
    public PacMan pacMan;
    int timerCounter = 500;
    Random random = new Random();
    public Timer time = new Timer(10, e -> timerAction());

    public PowerUpRandom(GameGUI game, PacMan pacMan) {
        this.game = game;
        this.pacMan = pacMan;
        this.repaint();
        this.setSize(50, 50);
        time.start();
    }

    // public void drawPowerUpRandom() {
    //     this.repaint();
    // }

    public void paintComponent(Graphics g) {
        super.setOpaque(false);
        super.paintComponent(g);

        try {
            g.drawImage(ImageIO.read(new File("Grafic/Random.png")), 12, 12, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void timerAction() {
        if (pacMan.atePowerUpRandom) {
            this.setVisible(false);
            if (randomLuck == 3) {
                randomLuck = random.nextInt(3);
            }
            powerUpLuckTime++;
            System.out.println(randomLuck);
            if (powerUpLuckTime == 165) {
                pacMan.atePowerUpRandom = false;
                powerUpLuckTime = 0;
                randomLuck = 3;
                timerCounter = 0;
            }
        }

        if (timerCounter == 500) {
            verifyLuck = false;
            this.setVisible(true);
            while (!verifyLuck) {
                randomLuckX = random.nextInt(16);
                randomLuckY = random.nextInt(16);
                if (GameGUI.fields[game.level][randomLuckY][randomLuckX] == 0) {
                    this.setLocation(randomLuckX * GameGUI.fieldSize, randomLuckY * GameGUI.fieldSize);
                    verifyLuck = true;
                }
            } // end of while
            timerCounter = 0;
        }
        timerCounter++;
    }
}