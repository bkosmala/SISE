package puzzleSolver.solver;

import puzzleSolver.Puzzle;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maciek on 25.04.17.
 */
public class BfsSolver extends Solver {

    private static final int MAX_RECURSION_DEPTH = 20;

    private String searchOrder;
//    private Integer[][] puzzle;

    private Set<Puzzle> visitedStates = new HashSet<>();
    private Queue<Puzzle> unvisitedStates = new LinkedList<>();

    private Puzzle goal;        // jezeli znajduje rozwiazanie to ustawiamy na ulozona ukladanke


    public BfsSolver(String searchOrder) {
        this.searchOrder = searchOrder;
    }

    // todo wywalic pozniej
    public BfsSolver() {}

    public void solvePuzzle(Puzzle unsolved) {

        long startTime = System.currentTimeMillis();
        bfs(unsolved, MAX_RECURSION_DEPTH);
        long endTime = System.currentTimeMillis();
        TIME_TO_SOLVE = endTime - startTime;
        VISITED_STATES = visitedStates.size();
    }

    private void bfs(Puzzle puzzleState, int maxDepth) {
        visitedStates.clear();
        unvisitedStates.clear();
        visitedStates.add(puzzleState);
        unvisitedStates.add(puzzleState);
        List<Puzzle> possibleMoves;
        int depthCounter = 0;

        while (!unvisitedStates.isEmpty()) {
            puzzleState = unvisitedStates.poll();
            COMPUTED_STATES += 1;
            if (puzzleState.isGoalState()) {
                goal = puzzleState;
                System.out.println(goal);
                MOVES = goal.getPath();
                MOVES_COUNT = MOVES.length();
                break;
            }
            if (puzzleState.getPath().length() <= maxDepth) {
                possibleMoves = puzzleState.getAncestors(searchOrder);
                unvisitedStates.addAll(removeDuplicates(possibleMoves));
                visitedStates.addAll(unvisitedStates);
            }
        }
    }

    // czasochlonna metoda
    private List<Puzzle> removeDuplicates(List<Puzzle> possibleMoves) {
        return possibleMoves.stream().filter(p -> !visitedStates.contains(p)).collect(Collectors.toList());
    }

    private void visitNode(Puzzle currentState) {
        unvisitedStates.remove(currentState);
        visitedStates.add(currentState);
    }
}
