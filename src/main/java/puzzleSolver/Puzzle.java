package puzzleSolver;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by maciek on 26.04.17.
 */
public class Puzzle {

    private int dimension;

    private int[][] puzzleArray;

    private static int[][] goalState;

    private boolean isGoalState;

    private int zeroColumn;
    private int zeroRow;

    public boolean moveLeft() {

        if (zeroColumn <= 0) {
            return false;
        }

        int temp = puzzleArray[zeroRow][zeroColumn - 1];
        puzzleArray[zeroRow][zeroColumn - 1] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroColumn -= zeroColumn;

        return true;
    }

    public boolean moveRight() {

        if (zeroColumn >= dimension - 1) {
            return false;
        }
        int temp = puzzleArray[zeroRow][zeroColumn + 1];
        puzzleArray[zeroRow][zeroColumn + 1] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroColumn += 1;

        return true;
    }

    public boolean moveUp() {

        if (zeroRow <= 0) {
            return false;
        }
        int temp = puzzleArray[zeroRow - 1][zeroColumn];
        puzzleArray[zeroRow - 1][zeroColumn] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroRow -= 1;

        return true;
    }

    public boolean moveDown() {

        if (zeroRow >= dimension - 1) {
            return false;
        }
        int temp = puzzleArray[zeroRow + 1][zeroColumn];
        puzzleArray[zeroRow + 1][zeroColumn] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroRow += 1;

        return true;
    }

    public boolean isGoalState() {
        // todo nie testowane
        return Arrays.deepEquals(puzzleArray, goalState);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        String newLine = System.lineSeparator();

        for (int n = 0; n < puzzleArray.length; n++) {

            for (int j = 0; j < puzzleArray[n].length; j++) {
                builder.append(puzzleArray[n][j] + " ");
            }
            builder.append(newLine);
        }
//        return Arrays.stream(puzzleArray)
//                .map(Arrays::toString)
//                .collect(Collectors.joining(System.lineSeparator()));

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Puzzle) {
            Puzzle state = (Puzzle) o;

            if (state.dimension != this.dimension) {
                return false;
            }

            for (int i = 0; i < dimension; i++){
                for (int j = 0; j < dimension; j++){
                    if (state.puzzleArray[i][j] != puzzleArray[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
