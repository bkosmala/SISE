package puzzleSolver.solver;

import puzzleSolver.heuristic.Hamming;
import puzzleSolver.heuristic.HeuristicType;
import puzzleSolver.heuristic.Manhattan;

/**
 * Created by maciek on 26.04.17.
 */
public class AstarSolverFactory {
    public static AstarSolver createAstarSolver(HeuristicType heuristicType, Integer[][] puzzle) {
        switch (heuristicType) {
            case HAMMING:
                return new AstarSolver(puzzle, new Hamming());
            case MANHATTAN:
                return new AstarSolver(puzzle, new Manhattan());
            default:
                return null;            // TODO nie zwracac nulla rzucic wyjatkiem
        }
    }
}
