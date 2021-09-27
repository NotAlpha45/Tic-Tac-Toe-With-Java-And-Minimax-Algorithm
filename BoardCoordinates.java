import java.util.ArrayList;
import java.util.Collections;

public class BoardCoordinates {
        /*
         * The board and it's section indices look like this:
         *                  |   |
         *              __0_|_1_|_2__
         *                  |   |
         *              __3_|_4_|_5__
         *                  |   |
         *                6 | 7 | 8   
         */                 

        static final int emptySpaceIndicator = 0;

        // sectionCheck is an indicator of where moves were made.
        static int sectionCheck[] = { emptySpaceIndicator, emptySpaceIndicator, emptySpaceIndicator,
                        emptySpaceIndicator, emptySpaceIndicator, emptySpaceIndicator, emptySpaceIndicator,
                        emptySpaceIndicator, emptySpaceIndicator };

        // The positions where player move should be drawn
        static final int moveDrawingpositions[][] = { { 60, 30 }, { 220, 30 }, { 370, 30 }, { 60, 180 }, { 220, 180 },
                        { 370, 180 }, { 60, 320 }, { 220, 320 }, { 370, 320 } };

        // Coordinate range of each sections.
        static final int sectionRanges[][] = { { 60, 40, 210, 180 }, { 220, 40, 370, 180 }, { 380, 40, 530, 180 },
                        { 60, 190, 210, 330 }, { 220, 190, 370, 330 }, { 380, 190, 530, 330 }, { 60, 340, 210, 480 },
                        { 220, 340, 370, 480 }, { 380, 340, 530, 480 } };

        // Indices for the sections that are if inline would be a game winner. It can
        // also be utilzed to block
        // a winning move of the opponent.
        static final int inlineSectionIndexes[][] = {
                        // The vertical lines
                        { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                        // The horizontal lines
                        { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
                        // The diagonal lines
                        { 0, 4, 8 }, { 2, 4, 6 } };

        static int remainingMoves = BoardCoordinates.sectionCheck.length;
        
        // Width and height of sprite to be drawn on the board.
        static final int spriteSize[] = { 120, 110 };

        public static void selectRandomSpace(int occupiedSpaceIndicator) {
                int movePosition;

                ArrayList<Integer> validMovePositions = new ArrayList<Integer>();

                // Insert the validMovePositions in an arraylist
                for (int position = 0; position < BoardCoordinates.sectionCheck.length; position++) {
                        if (BoardCoordinates.sectionCheck[position] == BoardCoordinates.emptySpaceIndicator) {
                                validMovePositions.add(position);
                        }
                }

                // Shuffle the valid move positions to make it radom
                Collections.shuffle(validMovePositions);
                movePosition = validMovePositions.get(0);

                BoardCoordinates.sectionCheck[movePosition] = occupiedSpaceIndicator;
        }
}
