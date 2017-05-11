package puzzleSolver.solver;

import puzzleSolver.Puzzle;
import puzzleSolver.heuristic.Heuristic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maciek on 25.04.17.
 */
public class AstarSolver extends HeuristicSolver {

    private Set<Puzzle> visitedStates = new HashSet<>();
    private Queue<Puzzle> unvisitedStates = new PriorityQueue<>(heuristic);

    public AstarSolver(Heuristic heuristic) {
        super(heuristic);
    }

    @Override
    public void solvePuzzle(Puzzle unsolved) {
        long startTime = System.nanoTime();
        astar(unsolved);
        long endTime = System.nanoTime();
        TIME_TO_SOLVE = (endTime - startTime) / 1000000.0;
        if (goal != null) {
            MOVES = goal.getPath();
            MOVES_COUNT = MOVES.length();
        }
//        VISITED_STATES = visitedStates.size();
    }

    private void astar(Puzzle puzzle) {
        goal = null;
        visitedStates.clear();
        unvisitedStates.clear();
        unvisitedStates.add(puzzle);
        Puzzle currentState = puzzle;

        while (!unvisitedStates.isEmpty() && !currentState.isGoalState()) {
            currentState = unvisitedStates.poll();
            if (currentState.getDepth() > MAX_DEPTH) {
                MAX_DEPTH += 1;
            }
            for (Puzzle succ : currentState.getNeighbours()) {
                VISITED_STATES++;
                if (visitedStates.add(succ)) {
                    unvisitedStates.add(succ);
                }
            }
            COMPUTED_STATES++;
        }
        if (currentState.isGoalState()) {
            goal = currentState;
        }
    }

}
