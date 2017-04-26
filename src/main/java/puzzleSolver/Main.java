package puzzleSolver;

import puzzleSolver.searcher.BsfSolver;
import puzzleSolver.searcher.DfsSolver;
import puzzleSolver.searcher.SearchStrategy;
import puzzleSolver.searcher.astar.AstarSolverFactory;
import puzzleSolver.searcher.astar.HeuristicType;

/**
 * Created by maciek on 25.04.17.
 */
public class Main {

    public static void main(String[] args) {

        int parametersLength = 3;

        if(args.length != parametersLength) {
        	
            handleIncorrectInput("Niepoprawne parametry wykonania programu");
        }
        String acronimParam = args[1];

        //TODO do zmiany po zrobieniu wczytywania wygenerowanej ukladanki
        Integer[][] puzzle = new Integer[4][4];

        // wczytywanie z pliku
        int[][] input = IOOperations.wczytajZPliku(args[2]);

        SearchStrategy puzzleSolver = null;

        if (args[0].equals("bfs")) {
            puzzleSolver = new BsfSolver();
        } else if (args[0].equals("dfs")) {
            puzzleSolver = new DfsSolver("",input);
        } else if (args[0].equals("astr")) {
            puzzleSolver = AstarSolverFactory.createAstarSolver(HeuristicType.fromAcronim(acronimParam), puzzle);
        } else {
            handleIncorrectInput("Niepoprawny akronim strategii!");
        }

        System.out.println("Hello world!");

        long startTime = System.currentTimeMillis();
        puzzleSolver.solvePuzzle();
        long endTime = System.currentTimeMillis();

    }

    private static void handleIncorrectInput(String msg) {
        System.out.println(msg);
        System.exit(-1);
    }
}
