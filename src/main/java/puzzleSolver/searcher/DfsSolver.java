package puzzleSolver.searcher;

import java.util.Arrays;
import javafx.util.Pair;
import java.util.Stack;

import puzzleSolver.MoveType;
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
    	Pair<Puzzle, MovingOrder> puzzState = new Pair(root,searchOrder);
    	MoveType nextMove;
    	MovingOrder nextOrder;
    	
    	Stack<Pair<Puzzle, MovingOrder>> stack = new Stack<Pair<Puzzle, MovingOrder>>();
    	stack.push(puzzState);
    	
    	while(true)
    	{
    		puzzState = stack.pop();
    		nextOrder=puzzState.getValue();
    		nextMove = nextOrder.nextMove();
			System.out.println(nextMove.toString());

    	if(nextMove == MoveType.NONE)
    	{
			System.out.println("Stack pop");
    		continue;
    	}
    	else
    	{
    		Puzzle next = new Puzzle(puzzState.getKey());
    		if(!next.move(nextMove))
    		{
				System.out.println("Stack push the same state again");
				System.out.println(next.toString());	
	    		puzzState = new Pair(puzzState.getKey(),nextOrder);
				stack.push(puzzState);
				continue;
    		}
    		else
    		{
    			if(next.isGoalState())
    			{
    				System.out.println("Rozwi¹zane !");		
    				System.out.println(next.toString());			
    				break;
    			}
    			else
    			{	
    				System.out.println("Stack push new state");	
    				System.out.println(next.toString());
    	    		puzzState = new Pair(puzzState.getKey(),nextOrder);
    	    		stack.push(puzzState);
    	    		puzzState = new Pair(next,searchOrder);
    	    		stack.push(puzzState);
    	    		continue;
    			}
    		}
    	}
    	}
    	
    	//System.out.println(Utils.toString(this.puzzle));
    	//System.out.println(Utils.toString(Puzzle.getGoalState()));
    	
    	// deepEquals test
    	//System.out.println(Arrays.deepEquals(Puzzle.getGoalState(), Puzzle.getGoalState()));
    	//System.out.println(Arrays.deepEquals(this.puzzle, Puzzle.getGoalState()));
    	
    }
}
