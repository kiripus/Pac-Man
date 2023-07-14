import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Ghosts extends JPanel {
    private final String[] ghostFilePaths = new String[]{"Grafic/GeistGruen.png", "Grafic/GeistBlau.png", "Grafic/GeistOrange.png", "Grafic/GeistRot.png", "Grafic/GeistTuerkis.png"};
    public int[] coordinates;
    private BufferedImage ghostImage;
    private BufferedImage ghostScaredImage;
    private int counter = 5;
    private boolean directionChanged;
    private GameGUI.direction directionGhost;
    private GameGUI.direction newDirection;
    private GameGUI game;
    private PacMan pacMan;
    private boolean cannotUp = false;
    private boolean cannotDown = false;
    private boolean cannotLeft = false;
    private boolean cannotRight = false;
    private boolean gotOutOfCage = false;
    Timer step;

    Ghosts(GameGUI game, int colorIndex, PacMan pacMan, int[] coordinates, int timerDelay) throws IOException {
        step = new Timer(timerDelay, e -> timerEvent());
        this.coordinates = coordinates;
        this.game = game;
        this.setLocation(coordinates[0] * GameGUI.fieldSize, coordinates[1] * GameGUI.fieldSize);
        this.pacMan = pacMan;
        ghostImage = ImageIO.read(new File(ghostFilePaths[colorIndex]));
        ghostScaredImage = ImageIO.read(new File("Grafic/GeistScared.png"));
        step.start();
    }

    void drawGhost() {
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.setOpaque(false);
        super.paintComponent(g);
        if (pacMan.atePowerUp) {
            g.drawImage(ghostScaredImage, 3, 2, null);
        } else {
            g.drawImage(ghostImage, 3, 2, null);
        }
    }

    private void ghostMovement(boolean isFrightened) {
        GameGUI.direction calculatedDirection = null;
        int xPosGhost = coordinates[0];
        int yPosGhost = coordinates[1];
        int[] target = this.calculateTarget();
        int dYUp = target[1] - (yPosGhost - 1);
        int dYDown = target[1] - (yPosGhost + 1);
        int dXLeft = target[0] - (xPosGhost - 1);
        int dXRight = target[0] - (xPosGhost + 1);
        int dX = target[0] - xPosGhost;
        int dY = target[1] - yPosGhost;
        double bestValue;
        if (!isFrightened) {
            bestValue = Integer.MAX_VALUE;
        } else {
            bestValue = 0;
        }

        double distanceUp = Math.sqrt(Math.pow(dX, 2) + Math.pow(dYUp, 2));
        double distanceDown = Math.sqrt(Math.pow(dX, 2) + Math.pow(dYDown, 2));
        double distanceLeft = Math.sqrt(Math.pow(dXLeft, 2) + Math.pow(dY, 2));
        double distanceRight = Math.sqrt(Math.pow(dXRight, 2) + Math.pow(dY, 2));

        boolean right = GameGUI.check(coordinates[0] + 1, coordinates[1], game.level, 1, gotOutOfCage) == 0;
        boolean left = GameGUI.check(coordinates[0] - 1, coordinates[1], game.level, 1, gotOutOfCage) == 0;
        boolean up = GameGUI.check(coordinates[0], coordinates[1] - 1, game.level, 1, gotOutOfCage) == 0;
        boolean down = GameGUI.check(coordinates[0], coordinates[1] + 1, game.level, 1, gotOutOfCage) == 0;

        if (right && !cannotRight) {
            if (this.isBetter(distanceRight, bestValue, isFrightened)) {
                bestValue = distanceRight;
                calculatedDirection = GameGUI.direction.right;
            }
        }
        if (left && !cannotLeft) {
            if (this.isBetter(distanceLeft, bestValue, isFrightened)) {
                bestValue = distanceLeft;
                calculatedDirection = GameGUI.direction.left;
            }
        }
        if (up && !cannotUp) {
            if (this.isBetter(distanceUp, bestValue, isFrightened)) {
                bestValue = distanceUp;
                calculatedDirection = GameGUI.direction.up;
            }
        }
        if (down && !cannotDown) {
            if (this.isBetter(distanceDown, bestValue, isFrightened)) {
                calculatedDirection = GameGUI.direction.down;
            }
        }

        if (calculatedDirection != null) {
            this.setDirection(calculatedDirection);
        } else {
            this.setDirection(this.reverseDirection());
        }
    }

    GameGUI.direction reverseDirection() {
        switch (directionGhost) {
            case up:
                return GameGUI.direction.down;
            case down:
                return GameGUI.direction.up;
            case left:
                return GameGUI.direction.right;
            case right:
                return GameGUI.direction.left;
            default:
                return GameGUI.direction.empty;
        }
    }

    void setDirection(GameGUI.direction direct) {
        newDirection = direct;
        directionChanged = true;
        switch (direct) {
            case right:
                cannotRight = false;
                cannotLeft = true;
                cannotUp = false;
                cannotDown = false;
                break;
            case left:
                cannotRight = true;
                cannotLeft = false;
                cannotUp = false;
                cannotDown = false;
                break;
            case up:
                cannotRight = false;
                cannotLeft = false;
                cannotUp = false;
                cannotDown = true;
                break;
            case down:
                cannotRight = false;
                cannotLeft = false;
                cannotUp = true;
                cannotDown = false;
                break;
        }
    }

    private boolean isBetter(double valueOne, double valueTwo, boolean frightened) {
        if (!frightened) {
            return valueOne <= valueTwo;
        } else {
            return valueOne >= valueTwo;
        }
    }

    void gotEaten() {
        this.setVisible(false);
        gotOutOfCage = false;
        coordinates[0] = GameGUI.ghostSpawnpointX;
        coordinates[1] = GameGUI.ghostSpawnpointY;
    }

    private void timerEvent() {
        if (counter == 5) {
            this.setVisible(true);
            if (pacMan.atePowerUp) {
                this.ghostMovement(true);
            } else {
                this.ghostMovement(false);
            }
            if (GameGUI.fields[game.level][coordinates[1]][coordinates[0]] == 3) {
                gotOutOfCage = true;
            }
        }
        if (pacMan.coordinates[0] == coordinates[0] && coordinates[1] == pacMan.coordinates[1]) {
            if (pacMan.atePowerUp) {
                this.gotEaten();
                pacMan.ateGhost();
            } else {
                pacMan.gotEaten();
                return;
            }
        }
        MovementResult result = GameGUI.move(this, counter, directionGhost, directionChanged, newDirection, coordinates, game, 1, gotOutOfCage);
        this.coordinates = result.coordinates;
        this.counter = result.counter;
        this.directionGhost = result.directionObject;
        this.directionChanged = result.directionChanged;
    }

    abstract int[] calculateTarget();
}