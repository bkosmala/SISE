package puzzleSolver.searcher;

import java.util.ArrayList;


import java.util.Stack;

import puzzleSolver.MoveType;
import puzzleSolver.MovingOrder;
import puzzleSolver.Puzzle;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver implements SearchStrategy {

    private MovingOrder searchOrder;
    private int puzzle[][];

    ArrayList<Puzzle> visitedState;
    Stack<Puzzle> stack;
    
    private int solutionLength = -1;
    private String path;
    private long startTime;
    private long endTime;
    private int maxRecursion;

    public DfsSolver(MovingOrder searchOrder, int[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
        this.visitedState = new ArrayList<Puzzle>();
        this.stack = new Stack<Puzzle>();
        this.maxRecursion = 0;
        System.out.println("");
        System.out.println(searchOrder.toString());
    }

    public void solvePuzzle() {
    	//TODO - obs³u¿yæ sytuacjê dla której program nie znalaz³ rozwi¹zania
    	//TODO - statystyki - liczby stanów przetworzonych i odwiedzonych
        this.startTime = System.currentTimeMillis();
    	 	
    	Puzzle puzzState = new Puzzle(this.puzzle);  	
    	MoveType nextMove;
    	
    	this.stack.push(puzzState);
    	
    	while(!this.stack.empty())
    	{
    		puzzState = this.stack.peek();
    		nextMove = puzzState.getNextMoveDirection(searchOrder);
			//System.out.println(nextMove.toString());

    	if(nextMove == MoveType.NONE)
    	{
			//System.out.println("Stack pop");
			//System.out.println(puzzState.toString());
			this.stack.pop();
    		continue;
    	}
    	else
    	{
    		Puzzle next = new Puzzle(puzzState);
			//System.out.println(next.toString());	
    		if(!next.move(nextMove))
    		{
				//System.out.println("Stack nothing - continue");
				//System.out.println(next.toString());	
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
    				if(this.addNotVisitedState(next))
    				{
        				//System.out.println("Stack push new state");	
        				if(this.stack.size()< 21)// ograniczenie na g³êbokoœæ poszukiwañ
        				{
        					if(this.stack.size()>this.maxRecursion) this.maxRecursion = this.stack.size();
        				//System.out.println(next.toString());
        				this.stack.push(next);
        				}
        				else
        				{
        					this.maxRecursion=this.stack.size();
        					//System.out.println("Searching depth limit reached");
        				}
    				}
    				else
    				{
        				//System.out.println("State already visited");	
        				//System.out.println(next.toString());
    				}
    			}
    		}
    	}
    	}  
    	/*
    	if(this.stack.empty())
    	{
    		//System.out.println("DFS: Stack empty. Nothing to do. ");
    	}*/
    	this.endTime = System.currentTimeMillis();
    }

    private boolean addNotVisitedState(Puzzle p) {
        for (Puzzle item : this.visitedState) {
            if (item.equals(p)) {
                return false;
            }
        }
        this.visitedState.add(p);
        return true;
    }
    
    public String[] getResults()
    {
    	String[] result = {"-1"};
    	
    	this.solutionLength = this.stack.size();
    	this.path = "";
    	Puzzle p;
    	if(!this.stack.empty())
    	{
    		for(int i = 0; i<this.stack.size();i++)
    		{
    			p = this.stack.elementAt(i);
    			if(p.getLastMove() != MoveType.NONE )
    			{
    			this.path += p.getLastMove().getMoveType();
    			}
    		}
    	}
    	
    	if(this.solutionLength > -1)
    	{
    		result = new String[2];
    		result[0] = Integer.toString(this.solutionLength);
    		result[1] = this.path;
    	}
    	
    	return result;
    }
    
    public String[] getStatistics()
    {
    	this.getResults(); 
    	
    	String[] result = new String[5];
    	
    	result[0] = Integer.toString(this.solutionLength);
    	result[1] = "";//?
    	result[2] = "";//?
    	result[3] = Integer.toString(this.maxRecursion);
    	result[4] = Long.toString(this.endTime - this.startTime);
    	
    	return result;
    }
}
