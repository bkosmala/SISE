package puzzleSolver.solver;

/**
 * Created by maciek on 26.04.17.
 */
public class AstarSolverFactory {
    public static AstarSolver createAstarSolver(HeuristicType heuristicType, Integer[][] puzzle) {
        switch (heuristicType) {
            case HAMMING:
                return new AstarSolverHamming(puzzle);
            case MANHATTAN:
                return new AstarSolverManhattan(puzzle);
            default:
                return null;            // TODO nie zwracac nulla rzucic wyjatkiem
        }
    }
}
