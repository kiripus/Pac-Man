import javax.swing.*;
import java.awt.*;

public class ScoreDisplay extends JPanel {
    private int score;

    void drawDisplay(int score) {
        this.score = score;
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.setOpaque(false);
        super.paintComponent(g);
        g.setFont(new Font("", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 0, 30);
    }
}



