public class RandomAI extends Move {

    public RandomAI(Board board, String name) {
        super(board, name);
    }

    void makeMove() {
        BoardCoordinates.remainingMoves--;
        BoardCoordinates.selectRandomSpace(this.occupiedSpaceIndicator);
    }

}
