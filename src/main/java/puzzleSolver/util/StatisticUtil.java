package puzzleSolver.util;

import puzzleSolver.solver.Solver;

/**
 * Created by maciek on 01.05.17.
 */
public class StatisticUtil {

    public static void generateBasicReport(String url) {
        if (Solver.getMovesCount() == -1) {
            IOOperations.writeToFile(url, String.valueOf(-1));
        } else {
            IOOperations.writeToFile(url, String.valueOf(Solver.getMovesCount()), Solver.getMOVES().toUpperCase());
        }
    }

    public static void generateAdditionalStatisticsFile(String url) {
        IOOperations.writeToFile(url, Solver.getStatistics());
    }
}
