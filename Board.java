import java.awt.*;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Board extends JComponent {
    private int x, y, width, height, thickness, midLineOffset = 0;
    Color borderColor = Color.BLACK, backgroundColor = Color.WHITE, textColor = Color.BLACK;
    private ButtonGroup themeGroup = new ButtonGroup(), aiGroup = new ButtonGroup();
    private JLabel themeLabel;
    private Button randomAIButton, defensiveAIButton;

    public Board(int x, int y, int width, int height, int thickness) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        setBounds(x, y, width, height);

        setLayout(null);
        addComponents();
        addPlayer();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public void setTextColor(Color color) {
        this.textColor = color;
    }

    public void setMidlineOffset(int value) {
        this.midLineOffset = value;
    }

    private void addComponents() {

        Theme themeClassic = new Theme("Classic", this);
        themeClassic.setDefaultState(true);
        themeClassic.positionButton(600, 100, 100, 40);
        themeClassic.setFont("SansSerif", 20, Font.PLAIN);
        themeClassic.setDefaultColor(Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE);
        themeClassic.addInGroup(themeGroup);

        Theme themeForest = new Theme("Forest", this);
        themeForest.setDefaultState(false);
        themeForest.positionButton(600, 140, 100, 40);
        themeForest.setFont("SansSerif", 20, Font.PLAIN);
        themeForest.setDefaultColor(new Color(49, 159, 32), Color.YELLOW, Color.YELLOW, new Color(49, 159, 32));
        themeForest.addInGroup(themeGroup);

        Theme themeHighContrast = new Theme("High Contrast", this);
        themeHighContrast.setDefaultState(false);
        themeHighContrast.positionButton(600, 180, 160, 40);
        themeHighContrast.setFont("SansSerif", 20, Font.PLAIN);
        themeHighContrast.setDefaultColor(Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK);
        themeHighContrast.addInGroup(themeGroup);

        themeClassic.applyCustomColor(Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE);
        themeForest.applyCustomColor(Color.WHITE, Color.BLACK, Color.YELLOW, new Color(49, 159, 32));
        themeHighContrast.applyCustomColor(Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK);

        this.randomAIButton = new Button("Random AI", this);
        this.randomAIButton.positionButton(600, 280, 160, 50);
        this.randomAIButton.setColor(this.borderColor, this.backgroundColor);
        this.randomAIButton.setFont("SansSerif", 22, Font.PLAIN);
        this.randomAIButton.addInGroup(this.aiGroup);

        this.defensiveAIButton = new Button("Defensive AI", this);
        this.defensiveAIButton.positionButton(600, 340, 160, 50);
        this.defensiveAIButton.setColor(this.borderColor, this.backgroundColor);
        this.defensiveAIButton.setFont("SansSerif", 22, Font.PLAIN);
        this.defensiveAIButton.addInGroup(this.aiGroup);

        this.themeLabel = new JLabel("Themes");
        this.themeLabel.setBounds(600, 40, 100, 60);
        this.themeLabel.setForeground(Color.BLACK);
        this.themeLabel.setFont(new Font("Serif", Font.BOLD, 30));
        add(themeLabel);

    }

    public void addPlayer() {
        Player player1 = new Player("Human", this, "X");
        player1.setSectionIndicator(1);
        player1.setColor(this.borderColor);

        RandomAI randomAI = new RandomAI(this, "O");
        randomAI.setSectionIndicator(2);
        randomAI.setColor(this.borderColor);

        DefensiveAI defensiveAI = new DefensiveAI(this, "O", player1);
        defensiveAI.setSectionIndicator(2);
        defensiveAI.setColor(this.borderColor);

        PlayerEventListener eventHandler = new PlayerEventListener(player1, this);
        eventHandler.setDefensiveAI(defensiveAI);
        addMouseListener(eventHandler);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.defensiveAIButton.setColor(this.borderColor, this.backgroundColor);
        this.randomAIButton.setColor(this.borderColor, this.backgroundColor);
        this.themeLabel.setForeground(this.borderColor);

        borderMaker(g);
        crosslineMaker(g);
    }

    private void crosslineMaker(Graphics g) {
        // Setting up the 4 intersecting lines
        Rectangle crosslineY1 = new Rectangle(200, 40, 10, 440);
        Rectangle crosslineY2 = new Rectangle(350, 40, 10, 440);
        Rectangle crosslineX1 = new Rectangle(60, 180, 440, 10);
        Rectangle crosslineX2 = new Rectangle(60, 320, 440, 10);

        crosslineX1.setFillColor(this.borderColor);
        crosslineX1.setBorderColor(this.borderColor);
        crosslineX2.setFillColor(this.borderColor);
        crosslineX2.setBorderColor(this.borderColor);
        crosslineY1.setFillColor(this.borderColor);
        crosslineY1.setBorderColor(this.borderColor);
        crosslineY2.setFillColor(this.borderColor);
        crosslineY2.setBorderColor(this.borderColor);

        crosslineX1.paintComponent(g);
        crosslineX2.paintComponent(g);
        crosslineY1.paintComponent(g);
        crosslineY2.paintComponent(g);
    }

    private void borderMaker(Graphics g) {

        Rectangle borderRectangle = new Rectangle(x, y, width, height);
        Rectangle midLine = new Rectangle(width - midLineOffset, y, 0, height);
        borderRectangle.setBorderThickness(this.thickness);
        midLine.setBorderThickness(this.thickness);

        borderRectangle.setBorderColor(this.borderColor);
        borderRectangle.setFillColor(this.backgroundColor);

        midLine.setBorderColor(this.borderColor);
        midLine.setFillColor(this.borderColor);
        borderRectangle.paintComponent(g);
        midLine.paintComponent(g);
    }
}
/**
 * Findings on placing a button along with drawings: -> The drawing (board
 * layer) and button must be under the same class (Here it's Board) which should
 * extend Jcomponent and have a null layout (setLayout(null)). In the overriden
 * paintComponent method, we need to draw the drawing.
 */