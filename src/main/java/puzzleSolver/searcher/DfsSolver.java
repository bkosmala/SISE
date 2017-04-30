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
   	
    	Puzzle puzzState = new Puzzle(this.puzzle);  	
    	MoveType nextMove;
    	
    	Stack<Puzzle> stack = new Stack<Puzzle>();
    	stack.push(puzzState);
    	
    	while(true)
    	{
    		puzzState = stack.peek();
    		nextMove = puzzState.getNextMoveDirection(searchOrder);
			System.out.println(nextMove.toString());

    	if(nextMove == MoveType.NONE)
    	{
			System.out.println("Stack pop");
			stack.pop();
    		continue;
    	}
    	else
    	{
    		Puzzle next = new Puzzle(puzzState);
			System.out.println(next.toString());	
    		if(!next.move(nextMove))
    		{
				System.out.println("Stack nothing - continue");
				System.out.println(next.toString());	
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
    	    		stack.push(next);
    	    		continue;
    			}
    		}
    	}
    	}    	
    }
}
