import java.awt.*;

public class Move {

    // occupiedSpaceIndicator is the number that the move object will use to
    // indicate a
    // move made.
    protected int occupiedSpaceIndicator;

    String name;
    Board board;
    private Sprite playerSprite = null;

    public Move(Board board, String name) {
        this.board = board;
        this.name = name;

    }

    public void setMoveSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public void setOccupiedSpaceIndicator(int occupiedSpaceIndicator) {
        this.occupiedSpaceIndicator = occupiedSpaceIndicator;
    }

    public boolean hasWon() {
        for (int moveSection = 0; moveSection < BoardCoordinates.inlineSectionIndexes.length; moveSection++) {
            int firstSectionIndex = BoardCoordinates.inlineSectionIndexes[moveSection][0];
            int secondSectionIndex = BoardCoordinates.inlineSectionIndexes[moveSection][1];
            int thirdSectionIndex = BoardCoordinates.inlineSectionIndexes[moveSection][2];
            // If an inline section is occupied by the player, then he has won.
            if (BoardCoordinates.sectionCheck[firstSectionIndex] == this.occupiedSpaceIndicator
                    && BoardCoordinates.sectionCheck[secondSectionIndex] == this.occupiedSpaceIndicator
                    && BoardCoordinates.sectionCheck[thirdSectionIndex] == this.occupiedSpaceIndicator) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasTied(boolean playerWinState, boolean opponentWinState) {
        if (playerWinState == false && opponentWinState == false && BoardCoordinates.remainingMoves <= 0) {
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        for (int sectionIndex = 0; sectionIndex < BoardCoordinates.sectionCheck.length; sectionIndex++) {
            if (BoardCoordinates.sectionCheck[sectionIndex] == this.occupiedSpaceIndicator) {

                int xPos = BoardCoordinates.moveDrawingpositions[sectionIndex][0];
                int yPos = BoardCoordinates.moveDrawingpositions[sectionIndex][1] + 20;

                this.playerSprite.setPosition(xPos, yPos);
                this.playerSprite.paintComponent(g);
            }
        }

    }

}
