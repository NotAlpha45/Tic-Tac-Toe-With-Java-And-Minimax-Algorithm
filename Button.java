import javax.swing.JComponent;
import java.awt.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.*;

public class Button extends JComponent implements ActionListener {
    private String label;
    private Board board;
    private JButton button;
    private int width, height, x, y;
    private boolean isEnabled = true;
    Color buttonColor, labelColor;

    public Button(String label, Board board) {
        this.board = board;
        this.label = label;
        this.button = new JButton(this.label);
    }

    public void setDefaultState(boolean isEnabled) {
        this.isEnabled = isEnabled;
        this.button.setEnabled(this.isEnabled);
    }

    public void setFont(String fontName, int fontSize, int fontType) {
        this.button.setFont(new Font(fontName, fontType, fontSize));
    }

    public void positionButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.button.setBounds(this.x, this.y, this.width, this.height);
        this.board.add(this.button);
        this.button.addActionListener(this);
    }

    public void setColor(Color buttonColor, Color labelColor) {
        this.buttonColor = buttonColor;
        this.labelColor = labelColor;
        this.button.setForeground(this.labelColor);
        this.button.setBackground(this.buttonColor);
    }

    // Action for buttonpress.
    public void actionPerformed(ActionEvent action) {
        System.out.println(action.getActionCommand());
    }

    public void addInGroup(ButtonGroup group) {
        group.add(this.button);
    }
}
