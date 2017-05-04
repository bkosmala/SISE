package puzzleSolver.solver;

import puzzleSolver.heuristic.Hamming;
import puzzleSolver.heuristic.HeuristicType;
import puzzleSolver.heuristic.Manhattan;

/**
 * Created by maciek on 26.04.17.
 */
public class AstarSolverFactory {
    public static AstarSolver createAstarSolver(HeuristicType heuristicType) {
        switch (heuristicType) {
            case HAMMING:
                return new AstarSolver(new Hamming());
            case MANHATTAN:
                return new AstarSolver(new Manhattan());
            default:
                return null;            // zla praktyka
        }
    }
}
