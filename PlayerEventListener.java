import java.awt.event.*;

import javax.swing.JOptionPane;

public class PlayerEventListener extends MouseAdapter {
    Player player;
    RandomAI randomAI = null;
    DefensiveAI defensiveAI = null;
    Board board;

    public PlayerEventListener(Player player, Board board) {
        this.player = player;
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
            playWithDefensiveAI(clickIndex, this.player, this.defensiveAI);

        } else {
            playWithRandomAI(clickIndex, this.player, this.randomAI);
        }

    }

    private void playWithDefensiveAI(int clickIndex, Player player, DefensiveAI defensiveAI) {
        if (canMakeMove(clickIndex, player, defensiveAI)) {

            player.makeMove(clickIndex);

            if (player.hasWon()) {
                JOptionPane.showMessageDialog(null, player.name + " Wins!", "Game over",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            else if (BoardCoordinates.remainingMoves > 1) {
                defensiveAI.makeMove();

                if (defensiveAI.hasWon()) {

                    JOptionPane.showMessageDialog(null, player.name + " Loses!", "Game over",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "It's a Tie!", "Game over", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private void playWithRandomAI(int clickIndex, Player player, RandomAI randomAI) {
        if (canMakeMove(clickIndex, player, randomAI)) {

            player.makeMove(clickIndex);

            if (BoardCoordinates.remainingMoves > 1) {
                randomAI.makeMove();
            }
        }
    }

    private boolean canMakeMove(int clickIndex, Player player, RandomAI randomAI) {
        return BoardCoordinates.sectionCheck[clickIndex] == BoardCoordinates.defaultSectionIndicator && !player.hasWon()
                && !randomAI.hasWon() && !Move.hasTied(player.hasWon(), randomAI.hasWon());
    }

    // Overload
    private boolean canMakeMove(int clickIndex, Player player, DefensiveAI defensiveAI) {
        return BoardCoordinates.sectionCheck[clickIndex] == BoardCoordinates.defaultSectionIndicator && !player.hasWon()
                && !defensiveAI.hasWon() && !Move.hasTied(player.hasWon(), defensiveAI.hasWon());
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
