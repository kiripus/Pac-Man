import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PowerUps extends JPanel {
    public static int randomX;
    public static int randomY;
    public static boolean verify = false;
    public GameGUI game;
    public int powerUpTime = 0;
    public PacMan pacMan;
    int timerCounter = 1000;
    Random random = new Random();
    public Timer time = new Timer(10, e -> timerAction());

    public PowerUps(GameGUI game, PacMan pacMan) {
        this.game = game;
        this.pacMan = pacMan;
        this.drawPowerUps();
        this.setSize(50, 50);
        time.start();
    }

    public void drawPowerUps() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.setOpaque(false);
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("Grafic/Powerup.png")), 12, 12, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void timerAction() {
        if (pacMan.atePowerUp) {
            this.setVisible(false);
            powerUpTime++;
            if (powerUpTime == 300) {
                pacMan.atePowerUp = false;
                powerUpTime = 0;
                timerCounter = 0;
            }
        }

        if (timerCounter == 1000) {
            verify = false;
            this.setVisible(true);
            while (!verify) {
                randomX = random.nextInt(16);
                randomY = random.nextInt(16);
                if (GameGUI.fields[game.level][randomY][randomX] == 0) {
                    this.setLocation(randomX * GameGUI.fieldSize, randomY * GameGUI.fieldSize);
                    verify = true;
                }
            } // end of while
            timerCounter = 0;
        }
        timerCounter++;
    }
}