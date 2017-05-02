package puzzleSolver.solver;

import puzzleSolver.Puzzle;

/**
 * Created by maciek on 25.04.17.
 */
public class AstarSolver implements SearchStrategy {

    private Integer[][] puzzle;

    public AstarSolver(Integer[][] puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public void solvePuzzle(Puzzle unsolved) {

    }
}
