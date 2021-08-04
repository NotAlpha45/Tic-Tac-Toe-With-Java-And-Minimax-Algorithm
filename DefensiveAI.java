public class DefensiveAI extends Move {
    Player opponent;
    private int winScore = 1, tieScore = 0, loseScore = -1;
    private final int INITIAL_BEST_SCORE = 2;

    public DefensiveAI(Board board, String symbol, Player opponent) {
        super(board, symbol);
        this.opponent = opponent;
    }

    public void makeMove() {
        BoardCoordinates.remainingMoves--;
        int movePosition = 0;
        int bestScore = -INITIAL_BEST_SCORE;
        for (int checkIndex = 0; checkIndex < BoardCoordinates.sectionCheck.length; checkIndex++) {
            if (BoardCoordinates.sectionCheck[checkIndex] == BoardCoordinates.defaultSectionIndicator) {

                BoardCoordinates.sectionCheck[checkIndex] = this.sectionIndicator;
                
                // Calculate opponent's move first (which is a minimizing move) and then make
                // a move. The fist depth is 0.
                int currentScore = calculateBestMove(0, false);

                // Revert board back to original upon calculating move.
                BoardCoordinates.sectionCheck[checkIndex] = BoardCoordinates.defaultSectionIndicator;

                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    movePosition = checkIndex;
                }
            }
        }
        BoardCoordinates.sectionCheck[movePosition] = this.sectionIndicator;
        draw();
    }

    private int calculateBestMove(int calculationDepth, boolean isMaximizingTurn) {

        // If a winning move is found
        if (this.hasWon()) {
            return this.winScore;
        }
        if (this.opponent.hasWon()) {
            return this.loseScore;
        }
        // If a draw move is found
        if (Move.hasTied(this.hasWon(), this.opponent.hasWon())) {
            return this.tieScore;
        }

        if (isMaximizingTurn) {

            int bestScore = this.INITIAL_BEST_SCORE;
            for (int checkIndex = 0; checkIndex < BoardCoordinates.sectionCheck.length; checkIndex++) {
                if (BoardCoordinates.sectionCheck[checkIndex] == BoardCoordinates.defaultSectionIndicator) {

                    BoardCoordinates.sectionCheck[checkIndex] = this.sectionIndicator;
                    int currentScore = calculateBestMove(calculationDepth + 1, false);
                    BoardCoordinates.sectionCheck[checkIndex] = BoardCoordinates.defaultSectionIndicator;

                    bestScore = Math.max(bestScore, currentScore);
                }
            }
            return bestScore;

        } else {

            int bestScore = this.INITIAL_BEST_SCORE;
            for (int checkIndex = 0; checkIndex < BoardCoordinates.sectionCheck.length; checkIndex++) {
                if (BoardCoordinates.sectionCheck[checkIndex] == BoardCoordinates.defaultSectionIndicator) {

                    BoardCoordinates.sectionCheck[checkIndex] = this.opponent.sectionIndicator;
                    int currentScore = calculateBestMove(calculationDepth + 1, true);
                    BoardCoordinates.sectionCheck[checkIndex] = BoardCoordinates.defaultSectionIndicator;

                    bestScore = Math.min(bestScore, currentScore);

                }
            }
            return bestScore;
        }
    }
}
