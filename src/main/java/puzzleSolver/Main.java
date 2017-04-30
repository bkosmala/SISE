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
/*******************************************
 Nierozwi¹zane problemy:
 1. Brak ograniczeñ na zajêtoœæ pamiêci w BFS i DFS.
 	W tym:
 	"Dodatkowo w przypadku strategii "w g³¹b" nale¿y ustaliæ maksymaln¹ dozwolon¹ g³êbokoœæ rekursji,
 	która nie mo¿e byæ mniejsza ni¿ 20 (wartoœæ tê mo¿na wpisaæ na sztywno do programu). 
 	W sytuacji, gdy program osi¹gnie tak¹ g³êbokoœæ nie znalaz³szy rozwi¹zania,
 	powinien wykonaæ nawrót." - co z tym? - zstosowaliœmy implementacjê ze stosem nie z rekurencj¹.
 2. Dostosowaæ problem do dzia³ania na niestandardowych ramkach (ró¿ne wymiary)
 
 ******************************************/
        int parametersLength = 5;

        if(args.length != parametersLength) {
            handleIncorrectInput("Niepoprawne parametry wykonania programu");
        }
        String acronimParam = args[1];
        System.out.println(acronimParam);

        //TODO do zmiany po zrobieniu wczytywania wygenerowanej ukladanki
        Integer[][] puzzle = new Integer[4][4];

        // wczytywanie z pliku
        int[][] input = IOOperations.readFromFile(args[2]);

        SearchStrategy puzzleSolver = null;
        Puzzle.init(input);

        if (args[0].equals("bfs")) {
            puzzleSolver = new BsfSolver();
        } else if (args[0].equals("dfs")) {
            puzzleSolver = new DfsSolver(MovingOrder.fromAcronim(acronimParam),input);
        } else if (args[0].equals("astr")) {
            puzzleSolver = AstarSolverFactory.createAstarSolver(HeuristicType.fromAcronim(acronimParam), puzzle);
        } else {
            handleIncorrectInput("Niepoprawny akronim strategii!");
        }

        System.out.println("Hello world!");

        long startTime = System.currentTimeMillis();
        puzzleSolver.solvePuzzle();
        long endTime = System.currentTimeMillis();
        String[] tabtest = {"6","KNKLIYJHJGFVB"};
        IOOperations.writeToFile(args[3], tabtest);

    }

    private static void handleIncorrectInput(String msg) {
        System.out.println(msg);
        System.exit(-1);
    }
}
