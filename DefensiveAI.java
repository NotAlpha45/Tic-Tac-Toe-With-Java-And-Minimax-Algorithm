public class DefensiveAI extends Move {
    Player opponent;

    public DefensiveAI(Board board, String name) {
        super(board, name);
        this.opponent = this.board.getPlayer();
    }

    // Observers a section off the board to block the opponent's winning move.
    private int blockSection(int[] inlineSection) {
        int firstSection = inlineSection[0];
        int secondSection = inlineSection[1];
        int thirdSection = inlineSection[2];

        if (BoardCoordinates.sectionCheck[firstSection] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[secondSection] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[thirdSection] == BoardCoordinates.emptySpaceIndicator) {
            return thirdSection;
        } else if (BoardCoordinates.sectionCheck[firstSection] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[thirdSection] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[secondSection] == BoardCoordinates.emptySpaceIndicator) {
            return secondSection;
        } else if (BoardCoordinates.sectionCheck[thirdSection] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[secondSection] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[firstSection] == BoardCoordinates.emptySpaceIndicator) {
            return firstSection;
        } else {
            // If the opponent move cannot be blocked at that line.
            return -1;
        }
    }

    public void makeMove() {
        BoardCoordinates.remainingMoves--;
        int blockingMovePosition;

        for (int moveSection = 0; moveSection < BoardCoordinates.inlineSectionIndexes.length; moveSection++) {
            blockingMovePosition = blockSection(BoardCoordinates.inlineSectionIndexes[moveSection]);

            // If a valid move blocking position is found, make a move there.
            if (blockingMovePosition != -1) {
                BoardCoordinates.sectionCheck[blockingMovePosition] = this.occupiedSpaceIndicator;
                // Exit immediately after making the move.
                return;
            }
        }

        // If there is no move worth blocking, make a random move.
        BoardCoordinates.selectRandomSpace(this.occupiedSpaceIndicator);
    }

}
