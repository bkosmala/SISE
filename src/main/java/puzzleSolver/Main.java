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
 Nierozwi�zane problemy:
 1. Brak ogranicze� na zaj�to�� pami�ci w BFS i DFS.
 	W tym:
 	"Dodatkowo w przypadku strategii "w g��b" nale�y ustali� maksymaln� dozwolon� g��boko�� rekursji,
 	kt�ra nie mo�e by� mniejsza ni� 20 (warto�� t� mo�na wpisa� na sztywno do programu). 
 	W sytuacji, gdy program osi�gnie tak� g��boko�� nie znalaz�szy rozwi�zania,
 	powinien wykona� nawr�t." - co z tym? - zstosowali�my implementacj� ze stosem nie z rekurencj�.
 2. Dostosowa� problem do dzia�ania na niestandardowych ramkach (r�ne wymiary)
 
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
