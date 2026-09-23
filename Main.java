
import javax.swing.*;
import java.awt.*;


public  class  Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shuter");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setResizable(false);
        frame.setVisible(true);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        panel.requestFocusInWindow();


    }
}