package puzzleSolver;

import puzzleSolver.solver.BfsSolver;
import puzzleSolver.solver.DfsSolver;
import puzzleSolver.solver.SearchStrategy;
import puzzleSolver.solver.Solver;
import puzzleSolver.solver.AstarSolverFactory;
import puzzleSolver.solver.HeuristicType;
import puzzleSolver.util.IOOperations;
import puzzleSolver.util.StatisticUtil;

/**
 * Created by maciek on 25.04.17.
 */
public class Main {
    private final static int PARAM_LENGTH = 5;

    public static void main(String[] args) {

        if(args.length != PARAM_LENGTH) {
            handleIncorrectInput("Niepoprawne parametry wykonania programu");
        }
        String acronimParam = args[1];
        String inputFilePath = args[2];
        String outputSolutionPath = args[3];
        String outputStatsPath = args[4];

        //TODO do zmiany po zrobieniu wczytywania wygenerowanej ukladanki
        Integer[][] puzzle = new Integer[4][4];

        // wczytywanie z pliku
        int[][] input = IOOperations.readFromFile(inputFilePath);
        Puzzle puzzleToSolve = new Puzzle(input, input[0].length, input.length);

        SearchStrategy puzzleSolver = null;

        if (args[0].equals("bfs")) {
            puzzleSolver = new BfsSolver();
        } else if (args[0].equals("dfs")) {
            puzzleSolver = new DfsSolver(acronimParam,input);
        } else if (args[0].equals("astr")) {
            puzzleSolver = AstarSolverFactory.createAstarSolver(HeuristicType.fromAcronim(acronimParam), puzzle);
        } else {
            handleIncorrectInput("Niepoprawny akronim strategii!");
        }

        System.out.println("Hello world!");

        puzzleSolver.solvePuzzle(puzzleToSolve);

        // generowanie plikow ze statystykami
        StatisticUtil.generateBasicReport(outputSolutionPath);
        StatisticUtil.generateAdditionalStatisticsFile(outputStatsPath);

        // drukowanie na konsole - todo Usunac jak nie potrzeba
        printInfo();

    }

    private static void handleIncorrectInput(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }

    private static void printInfo() {
        System.out.println("Ilosc ruchow " + Solver.getMovesCount());
        System.out.println("Ciag przesuniec: " + Solver.getMOVES());
        System.out.println("Czas: " + Solver.getTimeToSolve());
        System.out.println("maks głebokosc " + Solver.getMaxDepth());
        System.out.println("przetworzone " + Solver.getComputedStates());
        System.out.println("odwiedzone " + Solver.getVisitedStates());
    }
}
