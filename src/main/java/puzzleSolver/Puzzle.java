package puzzleSolver;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by maciek on 26.04.17.
 */
public class Puzzle {

    // todo maja być dwa wymiary - beda mogly byc rozne
    private int dimension;

    private int[][] puzzleArray;

    private static int[][] goalState = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};

    private short zeroColumn;
    private short zeroRow;

    public Puzzle(Puzzle original) {
        dimension = original.dimension;
        puzzleArray = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            puzzleArray[i] = ArrayUtils.clone(original.puzzleArray[i]);
        }
    }

    public boolean moveLeft() {

        if (!canMoveLeft()) {
            return false;
        }

        int temp = puzzleArray[zeroRow][zeroColumn - 1];
        puzzleArray[zeroRow][zeroColumn - 1] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroColumn -= zeroColumn;

        return true;
    }

    public boolean moveRight() {

        if (!canMoveRight()) {
            return false;
        }
        int temp = puzzleArray[zeroRow][zeroColumn + 1];
        puzzleArray[zeroRow][zeroColumn + 1] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroColumn += 1;

        return true;
    }

    public boolean moveUp() {

        if (!canMoveUp()) {
            return false;
        }
        int temp = puzzleArray[zeroRow - 1][zeroColumn];
        puzzleArray[zeroRow - 1][zeroColumn] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroRow -= 1;

        return true;
    }

    public Optional<Puzzle> moveDown() {

        if (!canMoveDown()) {
            return Optional.empty();
        }
        Puzzle newPuzzle = new Puzzle(this);        // kopia

        int temp = newPuzzle.puzzleArray[zeroRow + 1][zeroColumn];
        newPuzzle.puzzleArray[zeroRow + 1][zeroColumn] = 0;
        newPuzzle.puzzleArray[zeroRow][zeroColumn] = temp;

        newPuzzle.zeroRow += 1;

        return Optional.of(newPuzzle);
    }

    public boolean canMoveDown() {
        return zeroRow < dimension - 1;
    }

    public boolean canMoveUp() {
        return zeroRow > 0;
    }

    public boolean canMoveRight() {
        return zeroColumn < dimension - 1;
    }

    public boolean canMoveLeft() {
        return zeroColumn > 0;
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

    public List<Puzzle> getAncestors(String searchOrder) {
        if (canMoveX(searchOrder.charAt(0)))
    }
}
