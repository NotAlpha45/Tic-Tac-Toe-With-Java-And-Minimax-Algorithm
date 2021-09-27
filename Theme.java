import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.*;
import java.awt.event.*;

public class Theme implements ActionListener {

    private JRadioButton themeButton;
    private Color backgroundColor, borderColor;
    private int x, y, width, height;
    private String themeName;
    private boolean isDefault = false;
    private Board board;
    private Sprite player1Sprite, player2Sprite;

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

    public void setPlayerSprite(String player1SpriteLocation, String player2SpriteLocation) {
        this.player1Sprite = new Sprite(player1SpriteLocation);
        this.player2Sprite = new Sprite(player2SpriteLocation);
        this.board.getPlayer().setMoveSprite(this.player1Sprite);
        this.board.getRandomAI().setMoveSprite(this.player2Sprite);
        this.board.getDefensiveAI().setMoveSprite(this.player2Sprite);
    }

    public void applyCustomColor(Color backgroundColor, Color borderColor) {
        this.themeButton.setForeground(borderColor);
        this.themeButton.setBackground(backgroundColor);
        this.board.setBackgroundColor(backgroundColor);
        this.board.setBorderColor(borderColor);

        this.board.repaint();
    }

    public void setDefaultColor(Color backgroundColor, Color borderColor) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public void actionPerformed(ActionEvent action) {

        applyCustomColor(this.backgroundColor, this.borderColor);
        this.board.getPlayer().setMoveSprite(this.player1Sprite);
        this.board.getRandomAI().setMoveSprite(this.player2Sprite);
        this.board.getDefensiveAI().setMoveSprite(this.player2Sprite);
    }

    public void addInGroup(ButtonGroup group) {
        group.add(this.themeButton);
    }

}
