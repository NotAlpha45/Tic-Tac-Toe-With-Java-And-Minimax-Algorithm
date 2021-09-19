public class Player extends Move {

    public Player(Board board, String name) {
        super(board, name);
    }

    public void makeMove(int clickIndex) {

        BoardCoordinates.remainingMoves--;
        BoardCoordinates.sectionCheck[clickIndex] = this.occupiedSpaceIndicator;

    }
}
