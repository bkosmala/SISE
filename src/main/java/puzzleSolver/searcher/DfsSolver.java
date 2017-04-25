package puzzleSolver.searcher;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver implements SearchStrategy {

    private String searchOrder;
    private Integer puzzle[][];

    public DfsSolver(String searchOrder, Integer[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
    }

    //todo wywalic pozniej
    public DfsSolver() {}

    public void solvePuzzle() {

    }
}
