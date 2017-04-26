package puzzleSolver;

/**
 * Created by maciek on 25.04.17.
 */
public class Main {

    public static void main(String[] args) {

        int parametersLength = 2;
        String acronimParam = args[1];

        if(args.length != parametersLength) {
            handleIncorrectInput("Niepoprawne parametry wykonania programu");
        }

        //TODO do zmiany po zrobieniu wczytywania wygenerowanej ukladanki
        Integer[][] puzzle = new Integer[4][4];

        SearchStrategy puzzleSolver = null;

        if (args[0].equals("bfs")) {
            puzzleSolver = new BsfSolver();
        } else if (args[0].equals("dfs")) {
            puzzleSolver = new DfsSolver();
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
