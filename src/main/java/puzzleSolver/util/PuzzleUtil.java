package puzzleSolver.util;

/**
 * Created by maciek on 05.05.17.
 */
public class PuzzleUtil {

    public static int[][] getOrderedPuzzle(int rows, int columns) {
        int[][] orderedPuzzle = new int[rows][columns];
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == rows - 1 && j == columns - 1) {
                    break;
                }
                orderedPuzzle[i][j] = counter++;
            }
        }
        return  orderedPuzzle;
    }

}
