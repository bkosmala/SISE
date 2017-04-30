package puzzleSolver.searcher;

import java.util.ArrayList;


import java.util.Stack;

import puzzleSolver.MoveType;
import puzzleSolver.MovingOrder;
import puzzleSolver.Puzzle;

/**
 * Created by maciek on 25.04.17.
 */
public class DfsSolver implements SearchStrategy {

    private MovingOrder searchOrder;
    private int puzzle[][];

    ArrayList<Puzzle> visitedState;
    private int solutionLength;

    public DfsSolver(MovingOrder searchOrder, int[][] puzzle) {
        this.searchOrder = searchOrder;
        this.puzzle = puzzle;
        visitedState = new ArrayList<Puzzle>();
        System.out.println("");
        System.out.println(searchOrder.toString());
    }

    public void solvePuzzle() {
        //TODO - rozwa�y� problem ustawienie maksymalnej g��boko�ci rekursji
        //       (? - nie ma rekurencji) - czy potrzebne?
        //TODO - obs�u�y� sytuacj� dla kt�rej progrm nie znalaz� rozwi�zania
        //TODO - obs�u�y� potencjalny problem ze zbyt du�� ilo�ci� odwiedzonych stan�w
        //TODO - zapis wynik�w
        //TODO - zapis statystyk

        //to tests
        int counter = 0;

        Puzzle puzzState = new Puzzle(this.puzzle);
        MoveType nextMove;

        Stack<Puzzle> stack = new Stack<Puzzle>();
        stack.push(puzzState);

        while (!stack.empty()) {
            puzzState = stack.peek();
            nextMove = puzzState.getNextMoveDirection(searchOrder);
            System.out.println(nextMove.toString());

            if (nextMove == MoveType.NONE) {
                System.out.println("Stack pop");
                System.out.println(puzzState.toString());
                stack.pop();
                continue;
            } else {
                Puzzle next = new Puzzle(puzzState);
                System.out.println(next.toString());
                if (!next.move(nextMove)) {
                    System.out.println("Stack nothing - continue");
                    System.out.println(next.toString());
                    continue;
                } else {
                    if (next.isGoalState()) {
                        System.out.println("Rozwi�zane !");
                        System.out.println(next.toString());
                        break;
                    } else {
                        //counter++;
                        //if(counter>20){break;}
                        if (this.addNotVisitedState(next)) {
                            System.out.println("Stack push new state");
                            System.out.println(next.toString());
                            stack.push(next);
                        } else {
                            System.out.println("State already visited");
                            System.out.println(next.toString());
                        }
                    }
                }
            }

            if (stack.empty()) {
                System.out.println("DFS: Stack empty. Nothing to do. ");
            }
        }
    }

    private boolean addNotVisitedState(Puzzle p) {
        for (Puzzle item : this.visitedState) {
            if (item.equals(p)) {
                return false;
            }
        }
        this.visitedState.add(p);
        return true;
    }
}
