package puzzleSolver;

/**
 * Created by maciek on 26.04.17.
 */
public class Puzzle {

    private int dimension;

    private int[][] state;

    private static int[][] goalState;

    private boolean isGoalState;

    private int zeroColumn;
    private int zeroRow;

    public boolean moveLeft(){

        if (zeroColumn <= 0) {
            return false;
        }

        int temp = state[zeroRow][zeroColumn - 1];
        state[zeroRow][zeroColumn - 1] = 0;
        state[zeroRow][zeroColumn] = temp;

        zeroColumn -= zeroColumn;

        return true;
    }

    public boolean moveRight(){

        if (zeroColumn >= dimension - 1) {
            return false;
        }
        int temp = state[zeroRow][zeroColumn + 1];
        state[zeroRow][zeroColumn + 1] = 0;
        state[zeroRow][zeroColumn] = temp;

        zeroColumn += 1;

        return true;
    }

    public boolean moveUp(){

        if (zeroRow <= 0) {
            return false;
        }
        int temp = state[zeroRow - 1][zeroColumn];
        state[zeroRow - 1][zeroColumn] = 0;
        state[zeroRow][zeroColumn] = temp;

        zeroRow -= 1;

        return true;
    }

    public boolean moveDown(){

        if(zeroRow >= dimension - 1) {
            return false;
        }
        int temp = state[zeroRow + 1][zeroColumn];
        state[zeroRow + 1][zeroColumn] = 0;
        state[zeroRow][zeroColumn] = temp;

        zeroRow += 1;

        return true;
    }

}
