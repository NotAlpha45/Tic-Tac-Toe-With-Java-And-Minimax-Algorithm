import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.*;

public class Theme extends JComponent implements ActionListener {
    private JRadioButton themeButton;
    Color backgroundColor, borderColor, textColor, textBackgroundColor;
    private int x, y, width, height;
    private String themeName;
    private boolean isDefault = false;
    private Board board;

    public Theme(String themeName, Board board) {
        this.themeName = themeName;
        this.board = board;
    }

    public void setDefaultState(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setFont(String fontName, int fontSize, int fontType) {
        this.themeButton.setFont(new Font(fontName, fontType, fontSize));
    }

    public void positionButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.themeButton = new JRadioButton(themeName, isDefault);
        this.themeButton.setBounds(this.x, this.y, this.width, this.height);
        this.board.add(this.themeButton);
        this.themeButton.addActionListener(this);
    }
    
    public void applyCustomColor(Color backgroundColor, Color borderColor, Color textColor, Color textBgColor) {
        this.themeButton.setForeground(textColor);
        this.themeButton.setBackground(textBgColor);
        this.board.setBackgroundColor(backgroundColor);
        this.board.setBorderColor(borderColor);
        this.board.setTextColor(textColor);
    }

    public void setDefaultColor(Color backgroundColor, Color borderColor, Color textColor, Color textBgColor) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
        this.textBackgroundColor = textBgColor;

    }

    public void actionPerformed(ActionEvent action) {
        applyCustomColor(this.backgroundColor, this.borderColor, this.textColor, this.textBackgroundColor);
        board.repaint();
    }

    public void addInGroup(ButtonGroup group) {
        group.add(this.themeButton);
    }
}
