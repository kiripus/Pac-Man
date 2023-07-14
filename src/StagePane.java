import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StagePane extends JPanel {
    File Stage;

    public StagePane(String filepath) {
        super();
        Stage = new File(filepath);
    }

    public void drawStage() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(Stage), 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



