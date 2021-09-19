import java.awt.*;

import javax.swing.JComponent;

public class Rectangle extends JComponent {
    int x, y, width, height, thickness = 1;
    Color borderColor = Color.BLACK, fillColor = Color.WHITE;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setRectColor(Color borderColor, Color fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setBorderThickness(int thickness) {
        this.thickness = thickness;
    }

    // paintComponent method of Jcomponent class overriden
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Casted into more efficient graphics_obj
        Graphics2D graphics_obj = (Graphics2D) g;

        graphics_obj.setColor(this.borderColor);
        graphics_obj.setStroke(new BasicStroke(this.thickness));
        graphics_obj.drawRect(this.x, this.y, this.width, this.height);

        graphics_obj.setColor(fillColor);
        graphics_obj.fillRect(this.x, this.y, this.width, this.height);
    }
}
