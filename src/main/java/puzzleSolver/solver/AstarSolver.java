package puzzleSolver.solver;

import puzzleSolver.Puzzle;
import puzzleSolver.heuristic.Heuristic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maciek on 25.04.17.
 */
public class AstarSolver extends HeuristicSolver {

    private Puzzle goal;

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
        MOVES = goal.getPath();
        MOVES_COUNT = MOVES.length();
        VISITED_STATES = visitedStates.size();
    }

    private void astar(Puzzle puzzle) {
        goal = null;
        visitedStates.clear();
        unvisitedStates.clear();
        unvisitedStates.add(puzzle);
        Puzzle currentState;

        while (!unvisitedStates.isEmpty()) {
            currentState = unvisitedStates.poll();
            COMPUTED_STATES += 1;
            if (currentState.isGoalState()) {
                goal = currentState;
//                System.out.println(goal);
                break;
            }
            visitedStates.add(currentState);
            unvisitedStates.addAll(removeDuplicates(currentState.getNeighbours()));
        }
    }

    private List<Puzzle> removeDuplicates(List<Puzzle> possibleMoves) {
        return possibleMoves.stream().filter(p -> !visitedStates.contains(p)).collect(Collectors.toList());
    }
}
