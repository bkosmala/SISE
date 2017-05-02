package puzzleSolver.solver;

import puzzleSolver.heuristic.HeuristicType;

/**
 * Created by maciek on 26.04.17.
 */
public class AstarSolverFactory {
    public static AstarSolver createAstarSolver(HeuristicType heuristicType, Integer[][] puzzle) {
        switch (heuristicType) {
            case HAMMING:
                return new AstarSolver(puzzle);
            case MANHATTAN:
                return new AstarSolver(puzzle);
            default:
                return null;            // TODO nie zwracac nulla rzucic wyjatkiem
        }
    }
}
