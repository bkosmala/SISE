package puzzleSolver.searcher;

import java.util.Arrays;
import javafx.util.Pair;
import java.util.Stack;

import puzzleSolver.MovingOrder;
import puzzleSolver.Puzzle;
import puzzleSolver.Utils;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver implements SearchStrategy {

    private MovingOrder searchOrder;
    private int puzzle[][];
    
    private int solutionLength;

    public DfsSolver(MovingOrder searchOrder, int[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
        System.out.println("");
        System.out.println(searchOrder.toString());
    }

    public void solvePuzzle() {
    	  	
    	Puzzle root = new Puzzle(this.puzzle);  	
    	Pair<Puzzle, String> puzzState = new Pair(root,"");
    	
    	Stack<Pair<Puzzle, String>> stack = new Stack<Pair<Puzzle, String>>();
    	stack.push(puzzState);
    	
    	//test
    	Puzzle next = new Puzzle(root);
    	
    	System.out.println(Utils.toString(this.puzzle));
    	System.out.println(Utils.toString(Puzzle.getGoalState()));
    	
    	// deepEquals test
    	//System.out.println(Arrays.deepEquals(Puzzle.getGoalState(), Puzzle.getGoalState()));
    	//System.out.println(Arrays.deepEquals(this.puzzle, Puzzle.getGoalState()));
    	
    	

    }
}
