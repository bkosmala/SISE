package puzzleSolver.heuristic;

import puzzleSolver.Puzzle;

/**
 * Created by maciek on 02.05.17.
 */
public class Hamming implements Heuristic {

    @Override
    public int compare(Puzzle o1, Puzzle o2) {
        return this.calculateHeuristic(o1) - this.calculateHeuristic(o2);
    }

    @Override
    public int calculateHeuristic(Puzzle puzzle) {
        int result = 0;
        int value = 1;
        int[][] state = puzzle.getPuzzleArray();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                if (i == state.length - 1 && j == state[0].length - 1) {
                    break;
                }
                if (puzzle.getCoordsOfValue(value) != puzzle.getExpectedCoordsOfValue(value)) {
                    result += 1;
                }
                value++;
            }
        }
        return result;
    }
}
