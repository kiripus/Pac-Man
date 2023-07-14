import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Score extends JPanel {
    public void drawScorePoint() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.setOpaque(false);
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("Grafic/Punkt.png")), 20, 20, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



