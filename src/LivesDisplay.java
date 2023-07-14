import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LivesDisplay extends JPanel {
    private int lives = 3;
    private BufferedImage heart = ImageIO.read(new File(/*"Grafic/Heart.png"*/ "Grafic/Heart.png"));

    public LivesDisplay() throws IOException {
        super();
    }

    public void drawDisplay(int lives) {
        this.lives = lives;
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.setOpaque(false);
        super.paintComponent(g);
        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, 7, 50 * (2 - i) + 7, null);
        }
    }
}


