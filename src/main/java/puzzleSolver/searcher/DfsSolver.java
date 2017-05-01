package puzzleSolver.searcher;

import puzzleSolver.Puzzle;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver implements SearchStrategy {

    private String searchOrder;
    private int puzzle[][];

    private Set<Puzzle> visitedStates = new HashSet<>();

    //temp
    private Puzzle goal;
    
    private int solutionLength;

    public DfsSolver(String searchOrder, int[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
    }

    public void solvePuzzle(Puzzle unsolved) {

    }

    private void dfs(Puzzle puzzleState, int depth) {
        if (depth < 0) {
            return;
        }
        if (goal != null) {
            return;
        }
        if (puzzleState.isGoalState()) {
            goal = puzzleState;
        }

        puzzleState.getAncestors(searchOrder);
    }
}
