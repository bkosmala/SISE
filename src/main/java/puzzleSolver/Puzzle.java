package puzzleSolver;

import org.apache.commons.lang3.ArrayUtils;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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

    public Puzzle(int[][] puzzle, int width, int height) {
        dimension = width;
        puzzleArray = puzzle;
        setZerPosition();
    }

    public Puzzle(Puzzle original) {
        dimension = original.dimension;
        puzzleArray = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            puzzleArray[i] = ArrayUtils.clone(original.puzzleArray[i]);
        }
        zeroRow = original.zeroRow;
        zeroColumn = original.zeroColumn;
    }

    public Optional<Puzzle> moveLeft() {

        if (!canMoveLeft()) {
            return Optional.empty();
        }
        Puzzle newPuzzle = new Puzzle(this);

        int temp = newPuzzle.puzzleArray[zeroRow][zeroColumn - 1];
        newPuzzle.puzzleArray[zeroRow][zeroColumn - 1] = 0;
        newPuzzle.puzzleArray[zeroRow][zeroColumn] = temp;

        newPuzzle.zeroColumn -= 1;

        return Optional.of(newPuzzle);
    }

    public Optional<Puzzle> moveRight() {

        if (!canMoveRight()) {
            return Optional.empty();
        }
        Puzzle newPuzzle = new Puzzle(this);

        int temp = newPuzzle.puzzleArray[zeroRow][zeroColumn + 1];
        newPuzzle.puzzleArray[zeroRow][zeroColumn + 1] = 0;
        newPuzzle.puzzleArray[zeroRow][zeroColumn] = temp;

        newPuzzle.zeroColumn += 1;

        return Optional.of(newPuzzle);
    }

    public Optional<Puzzle> moveUp() {

        if (!canMoveUp()) {
            return Optional.empty();
        }
        Puzzle newPuzzle = new Puzzle(this);

        int temp = newPuzzle.puzzleArray[zeroRow - 1][zeroColumn];
        newPuzzle.puzzleArray[zeroRow - 1][zeroColumn] = 0;
        newPuzzle.puzzleArray[zeroRow][zeroColumn] = temp;

        newPuzzle.zeroRow -= 1;

        return Optional.of(newPuzzle);
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

    private Optional<Puzzle> move(char direction) {
        switch (direction) {
            case 'r':
            case 'R':
                return moveRight();
            case 'l':
            case 'L':
                return  moveLeft();
            case 'u':
            case 'U':
                return moveUp();
            case 'd':
            case 'D':
                return moveDown();
            default:
                return Optional.empty();
        }
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

    public List<Puzzle> getAncestors(String searchOrder) {
        List<Puzzle> newStates = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> move(searchOrder.charAt(i)).ifPresent(newStates::add));
        return newStates;
    }


    public boolean isGoalState() {
        return Arrays.deepEquals(puzzleArray, goalState);
    }

    private void setZerPosition() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (puzzleArray[i][j] == 0) {
                    zeroColumn = (short) j;
                    zeroRow = (short) i;
                }
            }
        }
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
