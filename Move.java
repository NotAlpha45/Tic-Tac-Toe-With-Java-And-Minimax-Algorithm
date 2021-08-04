import javax.swing.JLabel;

import java.awt.*;

public class Move {
    // sectionIndicator is the number that the move object will use to indicate a
    // move made.
    protected int sectionIndicator;

    String symbol;
    Board board;
    Color labelColor = Color.BLACK;

    public Move(Board board, String symbol) {
        this.board = board;
        this.symbol = symbol;

    }

    public void setColor(Color color) {
        this.labelColor = color;
    }

    public void setSectionIndicator(int sectionIndicator) {
        this.sectionIndicator = sectionIndicator;
    }

    private boolean isInline(int cell1, int cell2, int cell3) {
        if (cell1 == this.sectionIndicator && cell2 == this.sectionIndicator && cell3 == this.sectionIndicator) {
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

    public void draw() {
        // Repaint the board to make sure the moves are drawn properly
        this.board.repaint();
        for (int sectionIndex = 0; sectionIndex < BoardCoordinates.sectionCheck.length; sectionIndex++) {
            if (BoardCoordinates.sectionCheck[sectionIndex] == this.sectionIndicator) {
                JLabel moveLabel;
                moveLabel = new JLabel(this.symbol);
                moveLabel.setBounds(BoardCoordinates.moveDrawingpositions[sectionIndex][0],
                        BoardCoordinates.moveDrawingpositions[sectionIndex][1], 150, 140);
                moveLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 150));
                moveLabel.setForeground(this.labelColor);
                this.board.add(moveLabel);

            }
        }
    }
}
