import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameGUI extends JFrame implements KeyListener {
    //global variables
    //<editor-fold desc="fields">
    //fields in 3-dimensional array
    public static final int[][][] fields = new int[][][]{
            {
                    {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                    {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1},
                    {1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 1, 1, 3, 1, 0, 1, 0, 1, 1, 1},
                    {0, 0, 1, 1, 1, 0, 1, 2, 2, 1, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 0, 0, 1, 0, 1},
                    {1, 0, 1, 1, 1, 0, 1, 3, 1, 1, 0, 1, 1, 1, 0, 1},
                    {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                    {0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            },
            {
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1},
                    {0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1},
                    {1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                    {0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 3, 1, 0, 1, 0, 1},
                    {1, 1, 0, 0, 0, 1, 0, 0, 1, 2, 2, 1, 0, 1, 0, 1},
                    {0, 0, 0, 1, 0, 0, 0, 0, 1, 2, 2, 1, 0, 0, 0, 0},
                    {1, 0, 0, 1, 1, 1, 1, 0, 1, 3, 1, 1, 0, 1, 1, 1},
                    {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1},
                    {1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            },
            {
                    {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                    {1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0},
                    {1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                    {1, 0, 1, 1, 3, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                    {1, 0, 1, 2, 2, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 3, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            }
    };
    //</editor-fold>
    static final int fieldSize = 50;
    //</editor-fold>
    //<editor-fold desc="scoreAvailability">
    //availability of scoreAvailability in 3- dimensional array
    static boolean[][][] scoreAvailability = new boolean[][][]{
            {
                    {false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false},
                    {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false},
                    {false, true, false, false, false, true, false, true, false, false, false, true, false, false, true, false},
                    {false, true, false, true, true, true, false, true, true, true, false, true, false, false, true, false},
                    {false, true, true, true, false, true, false, false, false, true, true, true, true, true, true, false},
                    {false, false, false, false, false, true, true, true, true, true, true, false, true, false, false, false},
                    {false, true, true, true, true, true, false, false, false, false, true, false, true, false, false, false},
                    {true, true, false, false, false, true, false, false, false, false, true, false, true, true, true, true},
                    {false, true, true, true, true, true, false, false, false, false, true, true, true, false, true, false},
                    {false, true, false, false, false, true, false, false, false, false, true, false, false, false, true, false},
                    {false, true, false, true, true, true, true, true, true, true, true, true, true, false, true, false},
                    {false, true, false, true, false, false, false, true, false, true, false, false, false, false, true, false},
                    {true, true, false, true, false, true, true, true, false, true, true, true, true, true, true, true},
                    {false, true, false, true, false, true, false, false, false, false, true, false, false, false, true, false},
                    {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false},
                    {false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false},
            },
            {
                    {false, true, false, false, false, false, false, false, false, false, false, true, false, false, false, false},
                    {false, true, true, true, true, false, true, true, true, true, true, true, false, true, true, false},
                    {false, true, false, false, true, false, true, false, true, false, false, false, false, true, true, false},
                    {true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, true},
                    {false, true, true, true, false, false, true, true, true, false, false, true, true, false, false, false},
                    {false, true, false, true, true, true, true, false, true, false, false, false, true, false, true, false},
                    {true, true, false, true, true, false, true, true, true, true, true, true, true, false, true, true},
                    {false, true, false, true, false, false, false, true, false, false, false, false, true, true, true, false},
                    {false, false, true, true, true, false, true, true, false, false, false, false, true, false, true, false},
                    {true, true, true, false, true, true, true, true, false, false, false, false, true, true, true, true},
                    {false, true, true, false, false, false, false, true, false, false, false, false, true, false, false, false},
                    {false, false, true, false, true, true, true, true, true, true, true, true, true, true, true, false},
                    {false, true, true, true, true, false, true, true, false, true, false, false, true, false, true, false},
                    {false, false, false, false, true, false, true, true, false, true, true, false, true, false, true, false},
                    {false, true, true, true, true, true, true, false, false, false, true, true, true, true, true, false},
                    {false, true, false, false, false, false, false, false, false, false, false, true, false, false, false, false},

            },
            {
                    {false, false, true, false, false, false, false, true, false, false, false, false, true, false, false, false},
                    {true, true, true, true, true, true, true, true, false, false, true, true, true, true, true, true},
                    {false, false, false, true, false, false, true, false, false, false, true, false, false, false, true, false},
                    {false, false, false, true, false, false, true, true, true, true, true, true, true, true, true, false},
                    {true, true, true, true, true, false, true, false, true, false, false, false, false, false, true, true},
                    {false, true, false, false, true, false, true, true, true, true, true, true, true, true, true, false},
                    {false, true, false, false, true, false, false, true, false, false, false, false, false, true, false, false},
                    {false, true, true, true, true, true, true, true, true, true, true, false, false, true, false, false},
                    {false, true, false, false, false, false, true, false, false, false, true, true, true, true, false, false},
                    {false, true, false, false, false, false, true, false, false, false, false, false, false, true, false, false},
                    {false, true, false, false, false, false, true, true, true, true, true, true, true, true, true, false},
                    {false, true, false, false, false, false, true, false, true, false, true, false, true, false, true, false},
                    {true, true, true, true, true, true, true, false, true, true, true, true, true, false, true, true},
                    {false, true, false, false, true, false, true, false, false, false, true, false, false, false, true, false},
                    {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false},
                    {false, false, true, false, false, false, false, true, false, false, false, false, true, false, false, false},
            }
    };
    static int ghostSpawnpointX;
    static int ghostSpawnpointY;
    //</editor-fold>
    //stage-variables
    final int level;
    //declaration game objects
    private final PacMan pacMan;
    private final Ghosts ghostGreen;
    private final Ghosts ghostBlue;
    private final Ghosts ghostOrange;
    private final Ghosts ghostRed;
    private final PowerUps powerUp;
    private final PowerUpRandom powerUpRandom;
    private final ScoreDisplay scoreDisplay;
    private final LivesDisplay livesDisplay;
    int[] maximumScorePoints = new int[]{127, 130, 127};                //maximum available scorepoints in level
    int[] ghostSpawnArrayX = new int[]{7, 9, 3};
    int[] ghostSpawnArrayY = new int[]{7, 8, 9};
    Score[] scorePoint;

    //constructor
    public GameGUI(int level, GUI mainGUI) throws IOException {
        super();
        //calculation window-dimensions
        this.level = level;
        int stageWidth = fields[level][0].length;
        int stageHeight = fields[level].length;
        Dimension dFrame = new Dimension((stageWidth * fieldSize) + /*6,16*/6, (stageHeight * fieldSize) + /*29, 39*/29);

        //center the window
        Dimension dWindow = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dWindow.width - dFrame.width) / 2;
        int y = (dWindow.height - dFrame.height) / 2;

        //set window-propertys
        this.setSize(dFrame);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(x, y);
        this.setTitle("PacMan");
        this.setResizable(false);

        //layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, dFrame.width, dFrame.height);
        this.add(layeredPane);

        //stage panel
        String[] filepath = new String[]{"Grafic/Labyrinth.png", "Grafic/Labyrinth2.png", "Grafic/Labyrinth3.png"};     //filepaths labyrinth-pictures
        StagePane pStage = new StagePane(filepath[level]);
        pStage.drawStage();
        pStage.setSize(layeredPane.getSize());
        pStage.setLocation(0, 0);
        layeredPane.add(pStage, 1);

        //game objects start
        //score points
        scorePoint = new Score[maximumScorePoints[level]];
        for (int i = 0; i < maximumScorePoints[level]; i++) {
            scorePoint[i] = new Score();
            scorePoint[i].drawScorePoint();
            scorePoint[i].setSize(50, 50);
            layeredPane.add(scorePoint[i], 0);
        }
        int scorePointCounter = 0;
        for (int j = 0; j < 16; j++) {
            for (int k = 0; k < 16; k++) {
                if (fields[level][j][k] == 0) {
                    scorePoint[scorePointCounter].setLocation(k * fieldSize, j * fieldSize);
                    scorePointCounter++;
                }
            }
        }

        //score display
        scoreDisplay = new ScoreDisplay();
        scoreDisplay.setBounds(650, 0, 150, 50);
        layeredPane.add(scoreDisplay, 0);

        //lives display
        livesDisplay = new LivesDisplay();
        livesDisplay.setBounds(0, 650, 50, 150);
        layeredPane.add(livesDisplay, 0);

        //PacMan
        pacMan = new PacMan(this, scoreDisplay, livesDisplay, mainGUI);
        layeredPane.add(pacMan, 0);

        if (level == 0) {
            ghostSpawnpointX = ghostSpawnArrayX[0];
            ghostSpawnpointY = ghostSpawnArrayY[0];
        } else if (level == 1) {
            ghostSpawnpointX = ghostSpawnArrayX[1];
            ghostSpawnpointY = ghostSpawnArrayY[1];
        } else if (level == 2) {
            ghostSpawnpointX = ghostSpawnArrayX[2];
            ghostSpawnpointY = ghostSpawnArrayY[2];
        }

        //green ghost
        ghostGreen = new Ghosts(this, 0, pacMan, new int[]{ghostSpawnpointX, ghostSpawnpointY}, 100) {
            public int[] calculateTarget() {
                return pacMan.coordinates;
            }
        };
        ghostGreen.drawGhost();
        ghostGreen.setSize(50, 50);
        layeredPane.add(ghostGreen, 0);

        //blue ghost
        ghostBlue = new Ghosts(this, 1, pacMan, new int[]{ghostSpawnpointX + 1, ghostSpawnpointY}, 101) {
            public int[] calculateTarget() {
                switch (pacMan.directionPacMan) {
                    case up:
                        return new int[]{pacMan.coordinates[0], pacMan.coordinates[1] - 2};
                    case down:
                        return new int[]{pacMan.coordinates[0], pacMan.coordinates[1] + 2};
                    case left:
                        return new int[]{pacMan.coordinates[0] - 2, pacMan.coordinates[1]};
                    case right:
                        return new int[]{pacMan.coordinates[0] + 2, pacMan.coordinates[1]};
                    default:
                        return new int[]{7, 7};
                } // end of switch
            }
        };
        ghostBlue.drawGhost();
        ghostBlue.setSize(50, 50);
        layeredPane.add(ghostBlue, 0);

        //ghost orange
        ghostOrange = new Ghosts(this, 2, pacMan, new int[]{ghostSpawnpointX, ghostSpawnpointY + 1}, 106) {
            int[] calculateTarget() {
                switch (pacMan.directionPacMan) {
                    case up:
                        return new int[]{pacMan.coordinates[0], pacMan.coordinates[1] + 2};
                    case down:
                        return new int[]{pacMan.coordinates[0], pacMan.coordinates[1] - 2};
                    case left:
                        return new int[]{pacMan.coordinates[0] + 2, pacMan.coordinates[1]};
                    case right:
                        return new int[]{pacMan.coordinates[0] - 2, pacMan.coordinates[1]};
                    default:
                        return new int[]{7, 7};
                }
            }
        };
        ghostOrange.drawGhost();
        ghostOrange.setSize(50, 50);
        layeredPane.add(ghostOrange, 0);

        //ghost red
        ghostRed = new Ghosts(this, 3, pacMan, new int[]{ghostSpawnpointX + 1, ghostSpawnpointY + 1}, 104) {
            int[] calculateTarget() {
                return pacMan.coordinates;
            }
        };
        ghostRed.drawGhost();
        ghostRed.setSize(50, 50);
        layeredPane.add(ghostRed, 0);

        //power-up
        powerUp = new PowerUps(this, pacMan);
        layeredPane.add(powerUp, 0);

        powerUpRandom = new PowerUpRandom(this, pacMan);
        layeredPane.add(powerUpRandom, 0);
        //game objects end

        this.setVisible(true);
        addKeyListener(this);
    }

    public static int check(int xPos, int yPos, int level, int figure, boolean gotOutOfCage) {                                                //checks, whether a figure can be on the field (xPos|yPos)
        switch (figure) {
            case 0:
                if (isBorder(xPos, yPos)) {
                    return 1;
                } else if (isDoor(xPos, yPos, level) || isWall(xPos, yPos, level) || isGhostField(xPos, yPos, level)) {
                    return 2;
                } else {
                    return 0;
                }
            case 1:
                if (isBorder(xPos, yPos) || isWall(xPos, yPos, level) || isBeforeBorder(xPos, yPos) || (gotOutOfCage && isDoor(xPos, yPos, level))) {
                    return 2;
                } else {
                    return 0;
                }
            default:
                return -1;
        }
    }

    public static boolean isWall(int xPos, int yPos, int level) {                                                       //checks whether the field (xPos|yPos) is a wall
        return fields[level][yPos][xPos] == 1;
    }

    public static boolean isBorder(int xPos, int yPos) {                                                                //checks whether the field (xPos|yPos) is out of bounds
        return xPos == 16 || yPos == 16 || xPos == -1 || yPos == -1;
    }

    public static boolean isBeforeBorder(int xPos, int yPos) {
        return xPos == 15 || yPos == 15 || xPos == 0 || yPos == 0;
    }

    public static boolean isDoor(int xPos, int yPos, int level) {                                                       //checks whether the field (xPos|yPos) is the door
        return fields[level][yPos][xPos] == 3;
    }

    public static boolean isGhostField(int xPos, int yPos, int level) {                                                 //checks whether the field (xPos|yPos) is a ghost field
        return fields[level][yPos][xPos] == 2;
    }

    public static MovementResult move(JPanel object, int counter, direction directionObject, boolean directionChanged, direction newDirection, int[] coordinates, GameGUI game, int figure, boolean gotOutOfCage) {
        if (counter == -1) {
            counter = 5;
        }
        if (directionChanged) {
            if (newDirection == directionObject && figure == 0) {
                return new MovementResult(coordinates, counter, directionObject, true);
            } else {
                counter = 5;
            }
        }


        if (counter == 5) {
            switch (newDirection) {
                case up:
                    directionObject = GameGUI.direction.up;
                    break;
                case down:
                    directionObject = GameGUI.direction.down;
                    break;
                case left:
                    directionObject = GameGUI.direction.left;
                    break;
                case right:
                    directionObject = GameGUI.direction.right;
                    break;
            }

            switch (directionObject) {
                case up:
                    coordinates[1] = coordinates[1] - 1;
                    switch (GameGUI.check(coordinates[0], coordinates[1], game.level, figure, gotOutOfCage)) {
                        case 1:
                            coordinates[1] = 15;
                            break;
                        case 2:
                            coordinates[1] = coordinates[1] + 1;
                            counter = 0;
                            break;
                    }
                    break;
                case down:
                    coordinates[1] = coordinates[1] + 1;
                    switch (GameGUI.check(coordinates[0], coordinates[1], game.level, figure, gotOutOfCage)) {
                        case 1:
                            coordinates[1] = 0;

                            break;
                        case 2:
                            coordinates[1] = coordinates[1] - 1;
                            counter = 0;
                            break;
                    }
                    break;

                case left:
                    coordinates[0] = coordinates[0] - 1;
                    switch (GameGUI.check(coordinates[0], coordinates[1], game.level, figure, gotOutOfCage)) {
                        case 1:
                            coordinates[0] = 15;
                            break;
                        case 2:
                            coordinates[0] = coordinates[0] + 1;
                            counter = 0;
                            break;
                    }
                    break;
                case right:
                    coordinates[0] = coordinates[0] + 1;
                    switch (GameGUI.check(coordinates[0], coordinates[1], game.level, figure, gotOutOfCage)) {
                        case 1:
                            coordinates[0] = 0;
                            break;
                        case 2:
                            coordinates[0] = coordinates[0] - 1;
                            counter = 0;
                            break;
                    }
                    break;
            }
        }


        switch (directionObject) {
            case up:
                object.setLocation(coordinates[0] * (fieldSize), ((coordinates[1] * (fieldSize)) + (counter) * (fieldSize / 6)));
                if (counter == 0) {
                    if (GameGUI.isBorder(coordinates[0], coordinates[1] - 1)) {
                        break;
                    }
                    if (GameGUI.isWall(coordinates[0], coordinates[1] - 1, game.level)) {
                        //directionChanged = true;
                        return new MovementResult(coordinates, counter, directionObject, false);
                    }
                }

                break;
            case down:
                object.setLocation(coordinates[0] * (fieldSize), ((coordinates[1] * (fieldSize)) - (counter) * (fieldSize / 6)));
                if (counter == 0) {
                    if (GameGUI.isBorder(coordinates[0], coordinates[1] + 1)) {
                        break;
                    }
                    if (GameGUI.isWall(coordinates[0], coordinates[1] + 1, game.level)) {
                        return new MovementResult(coordinates, counter, directionObject, true);
                    }
                }
                break;
            case left:
                object.setLocation((coordinates[0] * (fieldSize) + (counter) * (fieldSize / 6)), coordinates[1] * (fieldSize));
                if (counter == 0) {
                    if (GameGUI.isBorder(coordinates[0] - 1, coordinates[1])) {
                        break;
                    }
                    if (GameGUI.isWall(coordinates[0] - 1, coordinates[1], game.level)) {
                        return new MovementResult(coordinates, counter, directionObject, true);
                    }
                }
                break;
            case right:
                object.setLocation((coordinates[0] * (fieldSize) - (counter) * (fieldSize / 6)), coordinates[1] * (fieldSize));
                if (counter == 0) {
                    if (GameGUI.isBorder(coordinates[0] + 1, coordinates[1])) {
                        break;
                    }
                    if (GameGUI.isWall(coordinates[0] + 1, coordinates[1], game.level)) {
                        return new MovementResult(coordinates, counter, directionObject, true);
                    }
                }
                break;
        }
        counter -= 1;
        if (figure == 1 && counter == 0) {
            counter = 5;
        }
        return new MovementResult(coordinates, counter, directionObject, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pacMan.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void frightenGhosts() {
        ghostGreen.setDirection(ghostGreen.reverseDirection());
        ghostBlue.setDirection(ghostBlue.reverseDirection());
        ghostOrange.setDirection(ghostOrange.reverseDirection());
        ghostRed.setDirection(ghostRed.reverseDirection());
    }

    public void reset() {
        ghostGreen.gotEaten();
        ghostBlue.gotEaten();
        ghostOrange.gotEaten();
        ghostRed.gotEaten();
    }

    public void stopAll() {
        ghostRed.step.stop();
        ghostOrange.step.stop();
        ghostBlue.step.stop();
        ghostGreen.step.stop();
        pacMan.step.stop();
        this.dispose();
    }

    enum direction {up, down, left, right, empty}                                                                       //creation of data type direction
}