import javax.swing.*;
import java.awt.*;

/**
 * Window class
 */
public class Window extends JFrame {
   
    private Board boardSection;

    public Window(int width, int height, String title) {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        // How much the display panel will open on app launch
        setBounds(0, 0, width + 50, height + 50);

        this.boardSection = new Board(20, 20, width - 20, height - 20, 4);
        this.boardSection.setMidlineOffset(200);
        getContentPane().add(this.boardSection);
        // getContentPane().addMouseListener(new PlayerEventListener());

    }

    public Board getBoardSection() {
        return this.boardSection;
    }

    public void displayWindow(boolean visibility) {
        setVisible(visibility);
    }

    public void setBGColor(Color color) {
        getContentPane().setBackground(color);
    }
}