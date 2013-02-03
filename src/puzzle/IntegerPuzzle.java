package puzzle;

import javafx.util.Pair;

/**
 * User: Rory
 * Date: 1/31/13
 * Time: 9:43 AM
 */

public class IntegerPuzzle {
    private int[][] board;
    private Pair<Integer,Integer> goalCoord;

    public IntegerPuzzle(int[][] board, Pair<Integer, Integer> goalCoord) {
        this.board = board;
        this.goalCoord = goalCoord;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Pair<Integer, Integer> getGoalCoord() {
        return goalCoord;
    }

    public void setGoalCoord(Pair<Integer, Integer> goalCoord) {
        this.goalCoord = goalCoord;
    }
}
