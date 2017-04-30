package puzzleSolver.searcher;

import puzzleSolver.Puzzle;

import java.util.*;

/**
 * Created by maciek on 25.04.17.
 */
public class BfsSolver implements SearchStrategy {

    private String searchOrder;
//    private Integer[][] puzzle;

    private Set<Puzzle> visitedStates = new HashSet<>();
    private Queue<Puzzle> unvisitedStates = new LinkedList<>();


    public BfsSolver(String searchOrder) {
        this.searchOrder = searchOrder;
    }

    // todo wywalic pozniej
    public BfsSolver() {}

    public void solvePuzzle(Puzzle unsolved) {

        List<Puzzle> possibleAncestors = new ArrayList<>();
        Puzzle currentState;
        unvisitedStates.add(unsolved);
        while (!unvisitedStates.isEmpty()) {
            currentState = unvisitedStates.peek();
            possibleAncestors.addAll(currentState.getAncestors(searchOrder));
        }


        // wyjsc z tej metody gdy nie bedzie sie dalo zrobic zadnego przesuniecia lub gdy znaleziono rozwiazanie!

    }

    private void visitNode(Puzzle currentState) {
        unvisitedStates.remove(currentState);
        visitedStates.add(currentState);
    }
}
