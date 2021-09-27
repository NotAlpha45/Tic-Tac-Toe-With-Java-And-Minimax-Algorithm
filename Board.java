import java.awt.*;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Board extends JComponent {
    private int x, y, width, height, thickness, midLineOffset = 0;
    private Color borderColor = Color.BLACK, backgroundColor = Color.WHITE;
    private ButtonGroup themeGroup = new ButtonGroup(), aiGroup = new ButtonGroup();
    private JLabel themeLabel;
    private Button randomAIButton, defensiveAIButton;
    private Player player1 = null;
    private RandomAI randomAI = null;
    private DefensiveAI defensiveAI = null;
    private PlayerEventListener eventHandler;
    private Theme themeForest = null, themeClassic = null, themeHighContrast = null;

    public Board(int x, int y, int width, int height, int thickness) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        setBounds(x, y, width, height);

        setLayout(null);
        addPlayer();
        addComponents();

    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public void setMidlineOffset(int value) {
        this.midLineOffset = value;
    }

    public Player getPlayer() {
        return this.player1;
    }

    public RandomAI getRandomAI() {
        return this.randomAI;
    }

    public DefensiveAI getDefensiveAI() {
        return this.defensiveAI;
    }

    public PlayerEventListener getEventHandler() {
        return this.eventHandler;
    }

    private void addComponents() {

        this.themeClassic = new Theme("Classic", this);
        this.themeClassic.setDefaultState(true);
        this.themeClassic.positionButton(600, 100, 100, 40);
        this.themeClassic.setFont("SansSerif", 20, Font.PLAIN);
        this.themeClassic.setDefaultColor(Color.WHITE, Color.BLACK);

        this.themeClassic.addInGroup(themeGroup);

        this.themeForest = new Theme("Forest", this);
        this.themeForest.setDefaultState(false);
        this.themeForest.positionButton(600, 140, 100, 40);
        this.themeForest.setFont("SansSerif", 20, Font.PLAIN);
        this.themeForest.setDefaultColor(new Color(75, 219, 39), new Color(31, 89, 16));
        this.themeForest.addInGroup(themeGroup);

        this.themeHighContrast = new Theme("High Contrast", this);
        this.themeHighContrast.setDefaultState(false);
        this.themeHighContrast.positionButton(600, 180, 160, 40);
        this.themeHighContrast.setFont("SansSerif", 20, Font.PLAIN);
        this.themeHighContrast.setDefaultColor(Color.DARK_GRAY, Color.LIGHT_GRAY);

        this.themeHighContrast.addInGroup(themeGroup);

        this.randomAIButton = new Button(this.randomAI.name, this);
        this.randomAIButton.positionButton(600, 280, 160, 50);
        this.randomAIButton.setColor(this.borderColor, this.backgroundColor);
        this.randomAIButton.setFont("SansSerif", 22, Font.PLAIN);
        this.randomAIButton.addInGroup(this.aiGroup);

        this.defensiveAIButton = new Button(this.defensiveAI.name, this);
        this.defensiveAIButton.positionButton(600, 340, 160, 50);
        this.defensiveAIButton.setColor(this.borderColor, this.backgroundColor);
        this.defensiveAIButton.setFont("SansSerif", 22, Font.PLAIN);
        this.defensiveAIButton.addInGroup(this.aiGroup);

        this.themeLabel = new JLabel("Themes");
        this.themeLabel.setBounds(600, 40, 100, 60);
        this.themeLabel.setForeground(Color.BLACK);
        this.themeLabel.setFont(new Font("Serif", Font.BOLD, 30));

        add(themeLabel);

        this.themeForest.setPlayerSprite("assets/spriteApple.png", "assets/spriteBanana.png");
        this.themeHighContrast.setPlayerSprite("assets/spriteWhiteRectangle.png", "assets/spriteBlackRectangle.png");
        this.themeClassic.setPlayerSprite("assets/spriteX.png", "assets/spriteO.png");

    }

    public void addPlayer() {
        this.player1 = new Player(this, "Human");
        this.player1.setOccupiedSpaceIndicator(1);

        this.randomAI = new RandomAI(this, "Random AI");
        this.randomAI.setOccupiedSpaceIndicator(2);

        this.defensiveAI = new DefensiveAI(this, "Defensive AI");
        this.defensiveAI.setOccupiedSpaceIndicator(2);

        this.eventHandler = new PlayerEventListener(this);

        addMouseListener(this.eventHandler);

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        this.themeClassic.applyCustomColor(this.backgroundColor, this.borderColor);
        this.themeForest.applyCustomColor(this.backgroundColor, this.borderColor);
        this.themeHighContrast.applyCustomColor(this.backgroundColor, this.borderColor);

        this.defensiveAIButton.setColor(this.borderColor, this.backgroundColor);
        this.randomAIButton.setColor(this.borderColor, this.backgroundColor);

        this.themeLabel.setForeground(this.borderColor);

        borderMaker(g);
        crosslineMaker(g);
        moveDrawer(g);

    }

    private void moveDrawer(Graphics g) {

        this.player1.draw(g);
        if (this.randomAI != null) {
            this.randomAI.draw(g);

        } else if (this.defensiveAI != null) {
            this.defensiveAI.draw(g);
        }

    }

    private void crosslineMaker(Graphics g) {
        // Setting up the 4 intersecting lines
        Rectangle crosslineY1 = new Rectangle(10, 440);
        Rectangle crosslineY2 = new Rectangle(10, 440);
        Rectangle crosslineX1 = new Rectangle(440, 10);
        Rectangle crosslineX2 = new Rectangle(440, 10);

        crosslineX1.setPosition(60, 180);
        crosslineX2.setPosition(60, 320);
        crosslineY1.setPosition(200, 40);
        crosslineY2.setPosition(350, 40);

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

        Rectangle borderRectangle = new Rectangle(width, height);
        Rectangle midLine = new Rectangle(0, height);

        borderRectangle.setPosition(x, y);
        midLine.setPosition(width - midLineOffset, y);

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
 * 
 * Findings on color changing -> Must be done in paintComponent method.
 */