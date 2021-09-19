import java.awt.event.*;

import javax.swing.JOptionPane;

public class PlayerEventListener extends MouseAdapter {
    private RandomAI randomAI = null;
    private DefensiveAI defensiveAI = null;
    private Board board;

    public PlayerEventListener(Board board) {
        this.board = board;
    }

    public void setRandomAI(RandomAI randomAI) {
        this.randomAI = randomAI;
        // So that two AI's aren't initialized at the same time.
        this.defensiveAI = null;
    }

    public void setDefensiveAI(DefensiveAI defensiveAI) {
        this.defensiveAI = defensiveAI;
        this.randomAI = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        int clickIndex = checkClickPosition(e.getX(), e.getY());

        if (this.randomAI == null) {

            playWithAI(clickIndex, this.board.getPlayer(), this.defensiveAI);

        } else {

            playWithAI(clickIndex, this.board.getPlayer(), this.randomAI);
        }

    }

    private void playWithAI(int clickIndex, Player player, RandomAI ai) {
        if (canMakeMove(clickIndex, player, ai)) {

            player.makeMove(clickIndex);

            if (player.hasWon()) {
                JOptionPane.showMessageDialog(null, player.name + " Wins!", "Game over",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            else if (BoardCoordinates.remainingMoves > 1) {
                ai.makeMove();
                if (ai.hasWon()) {
                    JOptionPane.showMessageDialog(null, player.name + " Loses!", "Game over",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "It's a Tie!", "Game over", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void playWithAI(int clickIndex, Player player, DefensiveAI ai) {
        if (canMakeMove(clickIndex, player, ai)) {

            player.makeMove(clickIndex);

            if (player.hasWon()) {
                JOptionPane.showMessageDialog(null, player.name + " Wins!", "Game over",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            else if (BoardCoordinates.remainingMoves > 1) {
                ai.makeMove();
                if (ai.hasWon()) {
                    JOptionPane.showMessageDialog(null, player.name + " Loses!", "Game over",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "It's a Tie!", "Game over", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean canMakeMove(int clickIndex, Player player, RandomAI randomAI) {
        return BoardCoordinates.sectionCheck[clickIndex] == BoardCoordinates.emptySpaceIndicator && !player.hasWon()
                && !randomAI.hasWon()
                && !Move.hasTied(player.hasWon(), randomAI.hasWon() && BoardCoordinates.remainingMoves > 0);
    }

    // Overload
    private boolean canMakeMove(int clickIndex, Player player, DefensiveAI defensiveAI) {
        return BoardCoordinates.sectionCheck[clickIndex] == BoardCoordinates.emptySpaceIndicator && !player.hasWon()
                && !defensiveAI.hasWon()
                && !Move.hasTied(player.hasWon(), defensiveAI.hasWon() && BoardCoordinates.remainingMoves > 0);
    }

    private int checkClickPosition(int x, int y) {
        for (int rangeIndex = 0; rangeIndex < BoardCoordinates.sectionRanges.length; rangeIndex++) {
            if (BoardCoordinates.sectionRanges[rangeIndex][0] <= x && x <= BoardCoordinates.sectionRanges[rangeIndex][2]
                    && BoardCoordinates.sectionRanges[rangeIndex][1] <= y
                    && y <= BoardCoordinates.sectionRanges[rangeIndex][3]) {
                return rangeIndex;
            }
        }
        return 0;
    }

}
