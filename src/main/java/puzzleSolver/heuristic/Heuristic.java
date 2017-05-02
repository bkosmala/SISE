package puzzleSolver.heuristic;

import puzzleSolver.Puzzle;

import java.util.Comparator;

/**
 * Created by maciek on 02.05.17.
 */
public interface Heuristic extends Comparator<Puzzle> {

    @Override
    int compare(Puzzle o1, Puzzle o2);
}
