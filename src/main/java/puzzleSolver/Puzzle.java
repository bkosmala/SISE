package puzzleSolver;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by maciek on 26.04.17.
 */
public class Puzzle {

    private int dimensionRows;
    private int dimensionColumns;
    private int[][] puzzleArray;
    private static int[][] goalState;
    private boolean isGoalState;

    private int zeroColumn;
    private int zeroRow;
    
    private MoveType current;
    
    public Puzzle(int[][] puzzleArray)
    {
    	this.puzzleArray = puzzleArray;
    	this.dimensionRows = puzzleArray.length;
    	this.dimensionColumns = puzzleArray[0].length;
    	
        for (int n = 0; n < this.dimensionRows; n++) {
            for (int j = 0; j < this.dimensionColumns; j++) {
            	if(this.puzzleArray[n][j] == 0)
            	{
            		this.zeroRow = n;
            		this.zeroColumn = j;
            		break;
            	}
            }
        }
        this.current = MoveType.NONE;
    }
    
    public Puzzle(Puzzle original)
    {
    	this.dimensionRows = original.dimensionRows;
    	this.dimensionColumns = original.dimensionColumns;
    	this.puzzleArray  = new int[original.dimensionRows][original.dimensionColumns];
    	int[][] oldPuzzleArray  = original.puzzleArray;
    	
        for (int n = 0; n < original.dimensionRows; n++) {
            for (int j = 0; j < original.dimensionColumns; j++) {
            	this.puzzleArray[n][j]=oldPuzzleArray[n][j];
            }
        }
        
        this.zeroRow = original.zeroRow;
        this.zeroColumn = original.zeroColumn;
        this.current = MoveType.NONE;
    }
    
    public static void init(int[][] puzzleArray)
    {
    	if(!Utils.isNullOrEmpty(puzzleArray)){
        	goalState  = new int[puzzleArray.length][puzzleArray[0].length];
        	
        	int counter = 1;
            for (int n = 0; n < goalState.length; n++) {

                for (int j = 0; j < goalState[n].length; j++) {
                    goalState[n][j]=counter;
                    counter++;
                }
            }	
            goalState[goalState.length-1][goalState[0].length-1] = 0; 
    	}
    }
    
    public boolean move(MoveType nextMove)
    {
    	switch(nextMove)
    	{
    	case NONE :
    		return false;
    	case LEFT :
    		return this.moveLeft();
    	case RIGHT :
    		return this.moveRight();
    	case UP :
    		return this.moveUp();
    	case DOWN :
    		return this.moveDown();
    	default: 
    		return false;
    	}	
    }

    
    public boolean moveLeft() {
        if (zeroColumn <= 0) {
            return false;
        }

        int temp = puzzleArray[zeroRow][zeroColumn - 1];
        puzzleArray[zeroRow][zeroColumn - 1] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroColumn -= 1;

        return true;
    }

    public boolean moveRight() {
        if (zeroColumn >= this.dimensionColumns - 1) {
            return false;
        }
        int temp = this.puzzleArray[zeroRow][zeroColumn + 1];
        this.puzzleArray[zeroRow][zeroColumn + 1] = 0;
        this.puzzleArray[zeroRow][zeroColumn] = temp;

        zeroColumn += 1;

        return true;
    }

    public boolean moveUp() {
        if (zeroRow <= 0) {
            return false;
        }
        int temp = puzzleArray[zeroRow - 1][zeroColumn];
        puzzleArray[zeroRow - 1][zeroColumn] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroRow -= 1;

        return true;
    }

    public boolean moveDown() {
        if (zeroRow >= this.dimensionRows - 1) {
            return false;
        }
        int temp = puzzleArray[zeroRow + 1][zeroColumn];
        puzzleArray[zeroRow + 1][zeroColumn] = 0;
        puzzleArray[zeroRow][zeroColumn] = temp;

        zeroRow += 1;

        return true;
    }

    public boolean isGoalState() {
        // sprawdzone - dzia³a
		return Arrays.deepEquals(puzzleArray, goalState);
    }

    @Override
    public String toString() {
        return Utils.toString(this.puzzleArray);
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Puzzle) {
            Puzzle state = (Puzzle) o;

            if (state.dimensionRows != this.dimensionRows || state.dimensionColumns != this.dimensionColumns) {
                return false;
            }

            for (int i = 0; i < this.dimensionRows; i++){
                for (int j = 0; j < this.dimensionColumns; j++){
                    if (state.puzzleArray[i][j] != puzzleArray[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

	public static int[][] getGoalState() {
		return goalState;
	}
	
	public MoveType getLastMove()
    {
    	return this.current;
    }
    
    public MoveType getNextMoveDirection(MovingOrder m)
    {
    	if(this.current == MoveType.NONE)
    	{
    		this.current = m.getSequence()[0];
    		return m.getSequence()[0];
    	}
    	else if (this.current == m.getSequence()[0])
    	{
    		this.current = m.getSequence()[1];
    		return m.getSequence()[1];
    	}
    	else if (this.current == m.getSequence()[1])
    	{
    		this.current = m.getSequence()[2];
    		return m.getSequence()[2];
    	}
    	else if (this.current == m.getSequence()[2])
    	{
    		this.current = m.getSequence()[3];
    		return m.getSequence()[3];
    	}
    	else
    	{
    		return MoveType.NONE;
    	}	
    }

	public int getDimensionRows() {
		return dimensionRows;
	}

	public int getDimensionColumns() {
		return dimensionColumns;
	}
    
}
