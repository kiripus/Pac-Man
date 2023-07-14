import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    //global definition Variables (window)
    private int numberLevel;
    private String[] comboBoxList = {"Level 1", "Level 2", "Level 3"};
    private JComboBox<String> cb_level = new JComboBox<>(comboBoxList);
    GameGUI game;

    //Constructor
    GUI() {
        //window creation
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Color mainColor = new Color(147, 147, 147);
        Color secondaryColor = new Color(242, 148, 255);
        //<editor-fold desc="Container">
        Container cp = this.getContentPane();
        cp.setLayout(null);
        //</editor-fold>

        //<editor-fold desc="window">
        Dimension dDesktop = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dFrame = new Dimension(406,529);
        int x = (dDesktop.width - dFrame.width) / 2;
        int y = (dDesktop.height - dFrame.height) / 2;
        this.setSize(dFrame);
        this.setTitle("PacMan Menu");
        this.setLocation(x, y);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //</editor-fold>

        //<editor-fold desc="btn_start">
        JButton btn_start = new JButton();
        btn_start.setBounds(50, 160, 120, 50);
        btn_start.setText("Start");
        btn_start.setBackground(mainColor);
        btn_start.setFont(new Font("Dialog", Font.BOLD, 20));
        btn_start.setBorder(new LineBorder(secondaryColor, 2));
        btn_start.addActionListener(e -> btn_start_ActionPerformed());        //Normal: public void name (int x) {x+2;}     Lambda: (int x) -> x+2;
        cp.add(btn_start);
        //</editor-fold>

        //<editor-fold desc="cb_level">
        cb_level.setBounds(230, 160, 120, 50);
        cb_level.setBackground(mainColor);
        cb_level.setBorder(new LineBorder(secondaryColor, 2));
        cp.add(cb_level);
        //</editor-fold>

        //<editor-fold desc="image">
        ImageIcon imgPacMan = new ImageIcon(String.valueOf(new File("Grafic/StartGUI.png")));
        JLabel image = new JLabel(imgPacMan);
        image.setBounds(0, 0, 400, 500);
        cp.add(image);
        //</editor-fold>

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    private void btn_start_ActionPerformed() {
        //start game, close window
        //this.dispose();
        this.setVisible(false);
        try {
            game = new GameGUI(selectLevel(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int selectLevel() {
        //level selection
        String nameLevel = (String) cb_level.getSelectedItem();
        assert nameLevel != null;
        switch (nameLevel) {
            case "Level 1" -> numberLevel = 0;
            case "Level 2" -> numberLevel = 1;
            case "Level 3" -> numberLevel = 2;
        }
        return numberLevel;
    }

    public void restart(){
        game = null;
        this.setVisible(true);
    }
}