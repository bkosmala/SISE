package puzzleSolver.searcher.astar;

import puzzleSolver.searcher.SearchStrategy;

/**
 * Created by maciek on 25.04.17.
 */
abstract class AstarSolver implements SearchStrategy {

    private Integer[][] puzzle;

    public AstarSolver(Integer[][] puzzle) {
        this.puzzle = puzzle;
    }
    public String[] getResults()
    {
    	String[] result = {"-1"};
    	
    	return result;
    }
    public String[] getStatistics()
    {
    	
    	String[] result = new String[5];
    	return result;
    }
}
