public class BoardCoordinates {

        // sectionCheck is an indicator of where moves were made.
        static int defaultSectionIndicator = 0;
        static int sectionCheck[] = { defaultSectionIndicator, defaultSectionIndicator, defaultSectionIndicator,
                        defaultSectionIndicator, defaultSectionIndicator, defaultSectionIndicator,
                        defaultSectionIndicator, defaultSectionIndicator, defaultSectionIndicator };
        // The positions where player move should be drawn
        static int moveDrawingpositions[][] = { { 60, 40 }, { 220, 40 }, { 380, 40 }, { 60, 190 }, { 220, 190 },
                        { 380, 190 }, { 60, 340 }, { 220, 340 }, { 380, 340 } };
        // Coordinate range of each sections.
        static int sectionRanges[][] = { { 60, 40, 210, 180 }, { 220, 40, 370, 180 }, { 380, 40, 530, 180 },
                        { 60, 190, 210, 330 }, { 220, 190, 370, 330 }, { 380, 190, 530, 330 }, { 60, 340, 210, 480 },
                        { 220, 340, 370, 480 }, { 380, 340, 530, 480 } };
        static int remainingMoves = BoardCoordinates.sectionCheck.length;
}
