package puzzleSolver.solver;

import com.google.common.collect.Lists;
import puzzleSolver.Puzzle;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver extends Solver {

    private static final int MAX_RECURSION_DEPTH = 20;

    private String searchOrder;

    //    private Stack<Puzzle> nextNodes = new Stack<>();
    private Set<Puzzle> visitedStates = new HashSet<>();
    private Puzzle goal;

    public DfsSolver(String searchOrder) {
        this.searchOrder = searchOrder;
    }

    public void solvePuzzle(Puzzle unsolved) {
        visitedStates.clear();
//        nextNodes.clear();
        long startTime = System.nanoTime();
        dfs(unsolved, MAX_RECURSION_DEPTH);
        long endTime = System.nanoTime();
        TIME_TO_SOLVE = (endTime - startTime) / 1000000.0;
        VISITED_STATES = visitedStates.size();
        if (goal != null) {
            MOVES = goal.getPath();
            MOVES_COUNT = MOVES.length();
        }
    }

    // todo poprawic - co jak stan o glebokosci maks jest taki sam jak stan rodzica rozwiazania!
    // poza tym chyba lepiej rekursja
    private void dfs(Puzzle puzzleState, int depth) {
        Deque<Puzzle> open = new ArrayDeque<>();
        open.add(puzzleState);
        visitedStates.add(puzzleState);

        while (!puzzleState.isGoalState() && !open.isEmpty()) {
            // nie da sie bez tego obyc?
            if (puzzleState.getPath().length() > MAX_DEPTH) {
                MAX_DEPTH += 1;
            }
            puzzleState = open.pop();
            if (puzzleState.getPath().length() < depth) {
                for (Puzzle succ : Lists.reverse(puzzleState.getNeighbours(searchOrder))) {
                    if (visitedStates.add(succ)) {
                        open.push(succ);
                    }
                }
            }
            COMPUTED_STATES++;
        }
        if (puzzleState.isGoalState()) {
            goal = puzzleState;
        }
    }

//    private void dfs(Puzzle puzzleState, int depth) {
//
//        if (MAX_DEPTH < MAX_RECURSION_DEPTH - depth) {
//            MAX_DEPTH = MAX_RECURSION_DEPTH - depth;
//        }
//        if (depth <= 0) {
//            return;
//        }
//        if (puzzleState.isGoalState()) {
//            goal = puzzleState;
////            System.out.println(puzzleState);
////            System.out.println(goal.getPath());
//            return;
//        }
//        if (goal != null) {
//            return;
//        }
//
//        COMPUTED_STATES += 1;
//        List<Puzzle> nextNodes = removeDuplicates(puzzleState.getNeighbours(searchOrder));
//        for (Puzzle state : nextNodes) {
//            if (state.isGoalState()) {
//                System.out.println(state);
//            }
//            dfs(state, depth - 1);
//            if (goal != null) {
//                return;
//            }
//            visitedStates.add(state);
//        }
//    }

    private List<Puzzle> removeDuplicates(List<Puzzle> puzzles) {
        // to moze byc czasochlonne, ale co tam todo sprawdzic czy nie lepiej trzymac parent w Puzzle
        return puzzles.stream()
                .filter(p -> !visitedStates.contains(p))
                .collect(Collectors.toList());
    }
}
