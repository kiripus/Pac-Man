import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PacMan extends JPanel {

    public boolean atePowerUp = false;
    public boolean atePowerUpRandom = false;
    public boolean open = false;
    public boolean directionChanged;
    public int counter = 1;
    public int scoreScorepointsEaten = 0;
    public int scoreGhostsEaten = 0;
    public int lives = 3;
    public int[] coordinates = new int[]{1, 1};
    public BufferedImage pacManOpen = ImageIO.read(new File("Grafic/PacManauf.png"));
    public BufferedImage pacManClosed = ImageIO.read(new File("Grafic/PacManzu.png"));
    public GameGUI game;
    private GUI mainGUI;
    public GameGUI.direction directionPacMan = GameGUI.direction.empty;
    public GameGUI.direction newDirection;
    ScoreDisplay scoreDisplay;
    LivesDisplay livesDisplay;
    int[] delay = new int[]{10, 10, 100, 50};
    public Timer step = new Timer(50, e -> stepAction());

    public PacMan(GameGUI game, ScoreDisplay scoreDisplay, LivesDisplay livesDisplay, GUI mainGUI) throws IOException {
        this.game = game;
        this.mainGUI = mainGUI;
        this.drawPacMan();
        this.setSize(50, 50);
        this.setLocation(coordinates[0] * GameGUI.fieldSize, coordinates[1] * GameGUI.fieldSize);
        this.scoreDisplay = scoreDisplay;
        this.livesDisplay = livesDisplay;
    }

    public void drawPacMan() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        switch (directionPacMan) {
            case up:
                g2d.rotate(Math.PI / 2, (double) this.getWidth() / 2, (double) this.getHeight() / 2);
                break;
            case down:
                g2d.rotate(-Math.PI / 2, (double) this.getWidth() / 2, (double) this.getHeight() / 2);
                break;
            case right:
                g2d.rotate(Math.PI, (double) this.getWidth() / 2, (double) this.getHeight() / 2);
                break;
        }
        if (open) {
            g2d.drawImage(pacManOpen, 3, 2, null);
        } else {
            g2d.drawImage(pacManClosed, 3, 2, null);
        }
    }

    public void stepAction() {
        step.setDelay(delay[PowerUpRandom.randomLuck]);

        if (scoreScorepointsEaten % game.maximumScorePoints[game.level] == 0) {
            for (int i = 0; i < game.maximumScorePoints[game.level]; i++) {
                game.scorePoint[i].setVisible(true);
            } // end of for
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    if (GameGUI.fields[game.level][j][k] == 0) {
                        GameGUI.scoreAvailability[game.level][j][k] = true;
                    }
                }
            }
        }
        if (counter == 1) {
            if (GameGUI.scoreAvailability[game.level][coordinates[1]][coordinates[0]]) {
                GameGUI.scoreAvailability[game.level][coordinates[1]][coordinates[0]] = false;
                int l = 0;
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 16; k++) {
                        if (GameGUI.fields[game.level][j][k] == 0) {
                            if (!GameGUI.scoreAvailability[game.level][j][k]) {
                                if (game.scorePoint[l].isVisible()) {
                                    game.scorePoint[l].setVisible(false);
                                    scoreScorepointsEaten++;
                                    scoreDisplay.drawDisplay(scoreScorepointsEaten + scoreGhostsEaten);
                                }
                            }
                            l++;
                        }
                    }
                }
            }

            if (PowerUps.randomX == coordinates[0] && PowerUps.randomY == coordinates[1] && PowerUps.verify) {
                atePowerUp = true;
                game.frightenGhosts();
                PowerUps.verify = false;
            }
            if (PowerUpRandom.randomLuckX == coordinates[0] && PowerUpRandom.randomLuckY == coordinates[1] && PowerUpRandom.verifyLuck) {
                atePowerUpRandom = true;
                PowerUpRandom.verifyLuck = false;
            }
            open = !open;
        }

        MovementResult result = GameGUI.move(this, counter, directionPacMan, directionChanged, newDirection, coordinates, game, 0, false);
        this.coordinates = result.coordinates;
        this.counter = result.counter;
        this.directionPacMan = result.directionObject;
        this.directionChanged = result.directionChanged;
    }

    public void keyPressed(KeyEvent e) {
        if (!step.isRunning()) {
            step.start();
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                newDirection = GameGUI.direction.up;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                newDirection = GameGUI.direction.down;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                newDirection = GameGUI.direction.left;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                newDirection = GameGUI.direction.right;
                break;
        }
        if (counter == 0) {
            counter = 5;
        }
    }

    public void gotEaten() {
        if (lives > 1) {
            lives--;
            livesDisplay.drawDisplay(lives);
            game.reset();
        } else {
            //lives = Integer.MAX_VALUE;
            game.reset();
            game.stopAll();
            mainGUI.restart();
            //new GUI();
        }
    }

    public void ateGhost() {
        scoreGhostsEaten += 50;
        scoreDisplay.drawDisplay(scoreScorepointsEaten + scoreGhostsEaten);
    }
}