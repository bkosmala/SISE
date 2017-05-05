package puzzleSolver.solver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciek on 01.05.17.
 */
public abstract class Solver implements SearchStrategy {

    // do statystyk
    protected static String MOVES;            // ciąg kolejnych ruchów
    protected static int MOVES_COUNT = -1;         // ilość ruchów do rozwiązania
    protected static int VISITED_STATES;      // liczba stanów odwiedzonych
    protected static int COMPUTED_STATES = 0;     // liczba stanów przetworzonych
    protected static int MAX_DEPTH = 0;           // maksymalna głębokość rekursji
    protected static double TIME_TO_SOLVE;      // czas poszukiwania rozwiązania

    public static String getMOVES() {
        return MOVES;
    }

    public static int getMovesCount() {
        return MOVES_COUNT;
    }

    public static int getVisitedStates() {
        return VISITED_STATES;
    }

    public static int getComputedStates() {
        return COMPUTED_STATES;
    }

    public static int getMaxDepth() {
        return MAX_DEPTH;
    }

    public static double getTimeToSolve() {
        return TIME_TO_SOLVE;
    }

    public static String[] getStatistics() {
        List<String> stats = new ArrayList<>();
        stats.add(String.valueOf(MOVES_COUNT));
        stats.add(String.valueOf(VISITED_STATES));
        stats.add(String.valueOf(COMPUTED_STATES));
        stats.add(String.valueOf(MAX_DEPTH));
        stats.add(String.valueOf((double)Math.round(TIME_TO_SOLVE * 1000d) / 1000d));
        return stats.toArray(new String[stats.size()]);
    }
}
