import java.awt.image.*;
import java.io.*;
import java.net.URISyntaxException;
import java.awt.*;
import javax.swing.JComponent;
import javax.imageio.ImageIO;

public class Sprite extends JComponent {
    private BufferedImage sprite;
    private int x, y, width = BoardCoordinates.spriteSize[0], height = BoardCoordinates.spriteSize[1];

    public Sprite(String location) {
        try {

            this.sprite = ImageIO.read(new File(getClass().getResource(location).toURI()));

        } catch (IOException | URISyntaxException e) {

            e.printStackTrace();
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.sprite, this.x, this.y, this.width, this.height, this);
    }
}
