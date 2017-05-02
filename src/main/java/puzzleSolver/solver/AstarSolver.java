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

    private Integer[][] puzzle;

    private Set<Puzzle> visitedStates = new HashSet<>();
    private Queue<Puzzle> unvisitedStates = new LinkedList<>();

    public AstarSolver(Integer[][] puzzle, Heuristic heuristic) {
        super(heuristic);
        this.puzzle = puzzle;
    }

    @Override
    public void solvePuzzle(Puzzle unsolved) {
        long startTime = System.currentTimeMillis();
        astar(unsolved);
        long endTime = System.currentTimeMillis();
        TIME_TO_SOLVE = endTime - startTime;
        MOVES = goal.getPath();
        MOVES_COUNT = MOVES.length();
        VISITED_STATES = visitedStates.size();
    }

    private void astar(Puzzle puzzle) {
        goal = null;
        visitedStates.clear();
        unvisitedStates.clear();
        unvisitedStates.add(puzzle);
        Queue<Puzzle> newStates = new PriorityQueue<>(heuristic);
        Puzzle currentState;

        while (!unvisitedStates.isEmpty()) {
            currentState = unvisitedStates.poll();
            COMPUTED_STATES += 1;
            if (currentState.isGoalState()) {
                goal = currentState;
                System.out.println(goal);
                break;
            }
            visitedStates.add(currentState);
            newStates.clear();
            newStates.addAll(currentState.getNeighbours());
            unvisitedStates.addAll(removeDuplicates(newStates));
        }
    }

    private List<Puzzle> removeDuplicates(Queue<Puzzle> possibleMoves) {
        return possibleMoves.stream().filter(p -> !visitedStates.contains(p)).collect(Collectors.toList());
    }
}
