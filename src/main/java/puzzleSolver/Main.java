package puzzleSolver;

/**
 * Created by maciek on 25.04.17.
 */
public class Main {

    public static void main(String[] args) {

        int parametersLength = 1;

        if(args.length != parametersLength) {
            handleIncorrectInput("Niepoprawne parametry wykonania programu");
        }

        SearchStrategy searchStrategy;

        if (args[0].equals("bfs")) {
            searchStrategy = SearchStrategy.BFS;
        } else if (args[0].equals("dfs")) {
            searchStrategy = SearchStrategy.DFS;
        } else if (args[0].equals("astr")) {
            searchStrategy = SearchStrategy.A_STAR;
        } else {
            handleIncorrectInput("Niepoprawny akronim strategii!");
        }

        System.out.println("Hello world!");

        long startTime = System.currentTimeMillis();
        //wywolanie algorytmu tutaj
        long endTime = System.currentTimeMillis();

    }

    private static void handleIncorrectInput(String msg) {
        System.out.println(msg);
        System.exit(-1);
    }
}
