package puzzleSolver.searcher;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver implements SearchStrategy {

    private String searchOrder;
    private int puzzle[][];
    
    private int solutionLength;

    public DfsSolver(String searchOrder, int[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
    }

    public void solvePuzzle() {

    }
}
