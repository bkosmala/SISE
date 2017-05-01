package puzzleSolver.solver;

/**
 * Created by maciek on 25.04.17.
 */
abstract class AstarSolver implements SearchStrategy {

    private Integer[][] puzzle;

    public AstarSolver(Integer[][] puzzle) {
        this.puzzle = puzzle;
    }
}
