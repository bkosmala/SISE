package puzzleSolver.solver;

import puzzleSolver.Puzzle;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maciek on 25.04.17.
 */
public class BfsSolver extends Solver {

    // nie ma takiego wymagania, ale mimo to ograniczamy, zeby kiedys zakonczyc poszukiwanie
    private static final int MAX_RECURSION_DEPTH = 20;

    private String searchOrder;

    private Set<Puzzle> visitedStates = new HashSet<>();
    private Queue<Puzzle> unvisitedStates = new LinkedList<>();

    public BfsSolver(String searchOrder) {
        this.searchOrder = searchOrder;
    }

    public void solvePuzzle(Puzzle unsolved) {

        long startTime = System.nanoTime();
        bfs(unsolved, MAX_RECURSION_DEPTH);
        long endTime = System.nanoTime();
        TIME_TO_SOLVE = (endTime - startTime) / 1000000.0;
//        VISITED_STATES = visitedStates.size();
        if (goal != null) {
            MOVES = goal.getPath();
            MOVES_COUNT = MOVES.length();
        }
    }

    private void bfs(Puzzle puzzleState, int maxDepth) {
        visitedStates.clear();
        unvisitedStates.clear();
        visitedStates.add(puzzleState);
        unvisitedStates.add(puzzleState);
        List<Puzzle> possibleMoves;

        while (!unvisitedStates.isEmpty()) {
            puzzleState = unvisitedStates.poll();
            COMPUTED_STATES += 1;
            if (puzzleState.isGoalState()) {
                goal = puzzleState;
                break;
            }
            if (puzzleState.getDepth() > MAX_DEPTH) {
                MAX_DEPTH += 1;
            }
            if (puzzleState.getPath().length() <= maxDepth) {
                possibleMoves = puzzleState.getNeighbours(searchOrder);
                for (Puzzle succ : possibleMoves) {
                    VISITED_STATES++;
                    if (visitedStates.add(succ)) {
                        unvisitedStates.add(succ);
                    }
                }
            }
        }
    }

}
