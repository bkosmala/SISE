package puzzleSolver.searcher;

import puzzleSolver.Puzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver implements SearchStrategy {

    private String searchOrder;
    private int puzzle[][];

    private List<Puzzle> nextNodes = new ArrayList<>();
    private Set<Puzzle> visitedStates = new HashSet<>();

    //temp
    private Puzzle goal;
    
    private int solutionLength;

    public DfsSolver(String searchOrder, int[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
    }

    public void solvePuzzle(Puzzle unsolved) {
        dfs(unsolved, 10);
    }

    private void dfs(Puzzle puzzleState, int depth) {
        if (depth < 0) {
            return;
        }
        if (puzzleState.isGoalState()) {
            System.out.println(puzzleState);
            goal = puzzleState;
        }
        if (goal != null) {
            return;
        }

        nextNodes = puzzleState.getAncestors(searchOrder);
        for (Puzzle state : nextNodes) {
            visitedStates.add(state);
            dfs(state, depth - 1);
            if (goal != null) {
                return;
            }
        }
    }
}
