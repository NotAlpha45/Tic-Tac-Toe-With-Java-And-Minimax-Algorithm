public class Player extends Move {
    String name;

    public Player(String name, Board board, String symbol) {
        super(board, symbol);
        this.name = name;
    }

    public void makeMove(int clickIndex) {

        BoardCoordinates.remainingMoves--;
        BoardCoordinates.sectionCheck[clickIndex] = this.sectionIndicator;
        this.draw();

    }
}
