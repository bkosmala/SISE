package puzzleSolver.searcher;

/**
 * Created by maciek on 25.04.17.
 */
public class BsfSolver implements SearchStrategy {

    private String searchOrder;
    private Integer[][] puzzle;

    public BsfSolver(String searchOrder, Integer[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
    }

    // todo wywalic pozniej
    public BsfSolver() {}

    public void solvePuzzle() {

    }
}
