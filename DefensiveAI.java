public class DefensiveAI extends Move {
    Player opponent;

    public DefensiveAI(Board board, String name, Player opponent) {
        super(board, name);
        this.opponent = opponent;
    }

    public void makeMove() {

        BoardCoordinates.remainingMoves --;
        // If any two consecutive position is occupied by the opponent, make move in the
        // next consecutive position. Otherwise, make a random move.
        int movePosition;
        if (BoardCoordinates.sectionCheck[0] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[1] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[2] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 2;
        }

        else if (BoardCoordinates.sectionCheck[1] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[2] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[0] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 0;
        }

        else if (BoardCoordinates.sectionCheck[3] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[4] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[5] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 5;
        }

        else if (BoardCoordinates.sectionCheck[4] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[5] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[3] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 3;
        }

        else if (BoardCoordinates.sectionCheck[6] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[7] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[8] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 8;
        }

        else if (BoardCoordinates.sectionCheck[7] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[8] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[6] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 6;
        }

        else if (BoardCoordinates.sectionCheck[0] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[3] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[6] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 6;
        }

        else if (BoardCoordinates.sectionCheck[3] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[6] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[0] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 0;
        }

        else if (BoardCoordinates.sectionCheck[1] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[4] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[7] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 7;
        }

        else if (BoardCoordinates.sectionCheck[7] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[4] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[1] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 1;
        }

        else if (BoardCoordinates.sectionCheck[2] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[5] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[8] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 8;
        }

        else if (BoardCoordinates.sectionCheck[5] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[8] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[2] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 2;
        }

        else if (BoardCoordinates.sectionCheck[0] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[4] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[8] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 8;
        }

        else if (BoardCoordinates.sectionCheck[8] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[4] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[0] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 0;
        }

        else if (BoardCoordinates.sectionCheck[2] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[4] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[6] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 6;
        }

        else if (BoardCoordinates.sectionCheck[6] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[4] == this.opponent.occupiedSpaceIndicator
                && BoardCoordinates.sectionCheck[2] == BoardCoordinates.emptySpaceIndicator) {
            movePosition = 2;
        } else {
            BoardCoordinates.selectRandomSpace(this.occupiedSpaceIndicator);
            return;
        }
        BoardCoordinates.sectionCheck[movePosition] = this.occupiedSpaceIndicator;
    }

}
