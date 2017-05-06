package puzzleSolver;

import org.apache.commons.lang3.ArrayUtils;
import puzzleSolver.util.PuzzleUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by maciek on 26.04.17.
 */
public class Puzzle {

    private int rows;
    private int columns;

    private int[][] puzzleArray;

    private static int[][] goalState;

    private short zeroColumn;
    private short zeroRow;
    private String path;        //zapisywanie kolejnych ruchów

    public Puzzle(int[][] puzzle, int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        goalState = PuzzleUtil.getOrderedPuzzle(rows, columns);
        puzzleArray = puzzle;
        setZeroPosition();
        path = "";
    }

    public Puzzle(Puzzle original) {
        rows = original.rows;
        columns = original.columns;
        puzzleArray = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            puzzleArray[i] = ArrayUtils.clone(original.puzzleArray[i]);
        }
        zeroRow = original.zeroRow;
        zeroColumn = original.zeroColumn;
        path = original.path;
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
        newPuzzle.path += 'l';

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
        newPuzzle.path += 'r';

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
        newPuzzle.path += 'u';

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
        newPuzzle.path += 'd';

        return Optional.of(newPuzzle);
    }

    private boolean canMove(char direction) {
        char inverseDirection = 'z';
        switch (direction) {
            case 'r':
            case 'R':
                inverseDirection = 'l';
                break;
            case 'l':
            case 'L':
                inverseDirection = 'r';
                break;
            case 'u':
            case 'U':
                inverseDirection = 'd';
                break;
            case 'd':
            case 'D':
                inverseDirection = 'u';
                break;
            default:
                return true;
        }
        return !this.path.endsWith(String.valueOf(inverseDirection).toLowerCase());
    }

    private Optional<Puzzle> move(char direction) {
        if (!canMove(direction)) {
            return Optional.empty();
        }
        switch (direction) {
            case 'r':
            case 'R':
                return moveRight();
            case 'l':
            case 'L':
                return moveLeft();
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

    public boolean canMoveDown() { return zeroRow < rows - 1; }

    public boolean canMoveUp() {
        return zeroRow > 0;
    }

    public boolean canMoveRight() {
        return zeroColumn < columns - 1;
    }

    public boolean canMoveLeft() {
        return zeroColumn > 0;
    }

    public List<Puzzle> getNeighbours(String searchOrder) {
        List<Puzzle> newStates = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> move(searchOrder.charAt(i)).ifPresent(newStates::add));
        return newStates;
    }

    public List<Puzzle> getNeighbours() {
        List<Puzzle> newStates = new ArrayList<>();
        moveLeft().ifPresent(newStates::add);
        moveUp().ifPresent(newStates::add);
        moveRight().ifPresent(newStates::add);
        moveDown().ifPresent(newStates::add);
        return newStates;
    }

    public int[][] getPuzzleArray() {
        return puzzleArray;
    }

    public int[] getCoordsOfValue(int value) {
        int[] coords = new int[2];
        for (int i = 0; i < puzzleArray.length; i++) {
            for (int j = 0; j < puzzleArray[0].length; j++) {
                if (puzzleArray[i][j] == value) {
                    coords[0] = i;
                    coords[1] = j;
                    return coords;
                }
            }
        }
        return coords;
    }

    public int[] getExpectedCoordsOfValue(int value) {
        int[] coords = new int[2];
        for (int i = 0; i < goalState.length; i++) {
            for (int j = 0; j < goalState[0].length; j++) {
                if (goalState[i][j] == value) {
                    coords[0] = i;
                    coords[1] = j;
                    return coords;
                }
            }
        }
        return coords;
    }

    public static int[][] getGoalState() {
        return goalState;
    }

    public boolean isGoalState() {
        return Arrays.deepEquals(puzzleArray, goalState);
    }

    private void setZeroPosition() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (puzzleArray[i][j] == 0) {
                    zeroColumn = (short) j;
                    zeroRow = (short) i;
                }
            }
        }
    }

    public String getPath() {
        return path;
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

            if (state.rows != this.rows || state.columns != this.columns) {
                return false;
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
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
        int result = rows;
        result = 31 * result + columns;
        result = 31 * result + Arrays.deepHashCode(puzzleArray);
        result = 31 * result + (int) zeroColumn;
        result = 31 * result + (int) zeroRow;
        return result;
    }
}
