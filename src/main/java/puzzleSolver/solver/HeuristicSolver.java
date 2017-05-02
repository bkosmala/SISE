package puzzleSolver.solver;

import puzzleSolver.heuristic.Heuristic;

/**
 * Created by maciek on 02.05.17.
 */
abstract class HeuristicSolver extends Solver {
    private Heuristic heuristic;

    public HeuristicSolver(Heuristic heuristic) {
        this.heuristic = heuristic;
    }
}
