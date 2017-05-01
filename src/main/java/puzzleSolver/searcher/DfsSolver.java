package puzzleSolver.searcher;

import puzzleSolver.Puzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            System.out.println(goal.getPath());
        }
        if (goal != null) {
            return;
        }

        nextNodes = puzzleState.getAncestors(searchOrder);
        removeDuplicates();     // nie odwiedzamy odwiedzonych stanow
        for (Puzzle state : nextNodes) {
            visitedStates.add(state);
            dfs(state, depth - 1);
            if (goal != null) {
                return;
            }
        }
    }

    private void removeDuplicates() {
        // to moze byc czasochlonne, ale co tam
        nextNodes = nextNodes.stream().filter(p -> !visitedStates.contains(p)).collect(Collectors.toList());
    }
}
