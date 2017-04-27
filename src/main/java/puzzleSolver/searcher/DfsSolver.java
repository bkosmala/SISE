package puzzleSolver.searcher;

import java.util.Arrays;
import javafx.util.Pair;

import puzzleSolver.Puzzle;
import puzzleSolver.Utils;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver implements SearchStrategy {

    private String searchOrder;
    private int puzzle[][];
    
    private int solutionLength;

    public DfsSolver(String searchOrder, int[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
    }

    public void solvePuzzle() {
    	  	
    	Puzzle root = new Puzzle(this.puzzle);  	
    	Pair<Puzzle, String> puzzState = new Pair(root,"D");
    	
    	System.out.println(Utils.toString(this.puzzle));
    	System.out.println(Utils.toString(Puzzle.getGoalState()));
    	
    	// deepEquals test
    	//System.out.println(Arrays.deepEquals(Puzzle.getGoalState(), Puzzle.getGoalState()));
    	//System.out.println(Arrays.deepEquals(this.puzzle, Puzzle.getGoalState()));
    	
    	

    }
}
