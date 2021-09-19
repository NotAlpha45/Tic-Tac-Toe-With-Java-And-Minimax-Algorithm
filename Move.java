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

    private boolean isInline(int cell1, int cell2, int cell3) {
        if (cell1 == this.occupiedSpaceIndicator && cell2 == this.occupiedSpaceIndicator
                && cell3 == this.occupiedSpaceIndicator) {
            return true;
        }
        return false;

    }

    public boolean hasWon() {
        // The six straight lines
        if (isInline(BoardCoordinates.sectionCheck[0], BoardCoordinates.sectionCheck[1],
                BoardCoordinates.sectionCheck[2])) {
            return true;
        } else if (isInline(BoardCoordinates.sectionCheck[3], BoardCoordinates.sectionCheck[4],
                BoardCoordinates.sectionCheck[5])) {
            return true;
        } else if (isInline(BoardCoordinates.sectionCheck[6], BoardCoordinates.sectionCheck[7],
                BoardCoordinates.sectionCheck[8])) {
            return true;
        } else if (isInline(BoardCoordinates.sectionCheck[0], BoardCoordinates.sectionCheck[3],
                BoardCoordinates.sectionCheck[6])) {
            return true;
        } else if (isInline(BoardCoordinates.sectionCheck[1], BoardCoordinates.sectionCheck[4],
                BoardCoordinates.sectionCheck[7])) {
            return true;
        } else if (isInline(BoardCoordinates.sectionCheck[2], BoardCoordinates.sectionCheck[5],
                BoardCoordinates.sectionCheck[8])) {
            return true;
        }
        // The two crosslines
        else if (isInline(BoardCoordinates.sectionCheck[0], BoardCoordinates.sectionCheck[4],
                BoardCoordinates.sectionCheck[8])) {
            return true;
        } else if (isInline(BoardCoordinates.sectionCheck[2], BoardCoordinates.sectionCheck[4],
                BoardCoordinates.sectionCheck[6])) {
            return true;
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
