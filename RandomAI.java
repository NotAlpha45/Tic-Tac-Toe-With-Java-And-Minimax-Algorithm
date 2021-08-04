import java.util.Random;

public class RandomAI extends Move {

    public RandomAI(Board board, String symbol) {
        super(board, symbol);
    }

    void makeMove() {
        Random rand = new Random();
        int movePosition;
        BoardCoordinates.remainingMoves--;
        while (true) {
            movePosition = rand.nextInt(9);
            // If a legitamate section is found, break.
            if (BoardCoordinates.sectionCheck[movePosition] == 0) {
                break;
            }
        }
        BoardCoordinates.sectionCheck[movePosition] = this.sectionIndicator;
        draw();
    }

}
