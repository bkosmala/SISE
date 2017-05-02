package puzzleSolver.heuristic;

import puzzleSolver.Puzzle;

/**
 * Created by maciek on 02.05.17.
 */
public class Manhattan implements Heuristic {

    @Override
    public int compare(Puzzle o1, Puzzle o2) {
        return this.calculateHeuristic(o1) - this.calculateHeuristic(o2);
    }

    @Override
    public int calculateHeuristic(Puzzle puzzle) {
        int result = 0;
        // ukladanka ma wartosci od 0 do n
        int value = 1;          // zero pomijamy
        int[][] state = puzzle.getPuzzleArray();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                // nie liczymy dla pustego miejsca - czyli dla zera
                if (i == state.length - 1 && j == state[0].length - 1){
                    break;
                }
                int[] coords = puzzle.getCoordsOfValue(value);
                int[] expectedCoords = puzzle.getExpectedCoordsOfValue(value);
                result += (Math.abs(coords[0] - expectedCoords[0]) + Math.abs(coords[1] - expectedCoords[1]));

                value++;
            }
        }
        return result;
    }
}
