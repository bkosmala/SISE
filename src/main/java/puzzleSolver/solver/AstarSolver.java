package puzzleSolver.solver;

import puzzleSolver.Puzzle;
import puzzleSolver.heuristic.Heuristic;

/**
 * Created by maciek on 25.04.17.
 */
public class AstarSolver extends HeuristicSolver {

    private Integer[][] puzzle;

    public AstarSolver(Integer[][] puzzle, Heuristic heuristic) {
        super(heuristic);
        this.puzzle = puzzle;
    }

    @Override
    public void solvePuzzle(Puzzle unsolved) {

    }
}
