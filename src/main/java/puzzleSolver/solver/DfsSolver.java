package puzzleSolver.solver;

import puzzleSolver.Puzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver extends Solver {

    private static final int MAX_RECURSION_DEPTH = 20;

    private String searchOrder;
    private int puzzle[][];

    private List<Puzzle> nextNodes = new ArrayList<>();
    private Set<Puzzle> visitedStates = new HashSet<>();

    //temp
    private Puzzle goal;

    public DfsSolver(String searchOrder, int[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
    }

    public void solvePuzzle(Puzzle unsolved) {
        long startTime = System.currentTimeMillis();
        dfs(unsolved, MAX_RECURSION_DEPTH);
        long endTime = System.currentTimeMillis();
        TIME_TO_SOLVE = endTime - startTime;
        VISITED_STATES = visitedStates.size();
        MOVES = goal.getPath();
        MOVES_COUNT = MOVES.length();
    }

    private void dfs(Puzzle puzzleState, int depth) {
        if (depth < 0) {
            return;
        }
        if (MAX_DEPTH < MAX_RECURSION_DEPTH - depth) {
            MAX_DEPTH = MAX_RECURSION_DEPTH - depth;
        }
        if (puzzleState.isGoalState()) {
            System.out.println(puzzleState);
            goal = puzzleState;
            System.out.println(goal.getPath());
        }
        if (goal != null) {
            return;
        }
        COMPUTED_STATES += 1;
        nextNodes = puzzleState.getNeighbours(searchOrder);
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
        // to moze byc czasochlonne, ale co tam todo sprawdzic czy nie lepiej trzymac parent w Puzzle
        nextNodes = nextNodes.stream().filter(p -> !visitedStates.contains(p)).collect(Collectors.toList());
    }
}
