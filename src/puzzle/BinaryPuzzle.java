package puzzle;

import javafx.util.Pair;

/**
 * User: Rory
 * Date: 1/30/13
 * Time: 5:14 PM
 */

/**
 * This class stores the puzzle using boolean to indicate each cell
 * It is more memory efficient than IntegerPuzzle.class, but must be handled properly
 */
public class BinaryPuzzle {

    private boolean[][] board;
    // todo: store this goalCoord somewhere else.. there's no need for it to be duplicated in thousands of instances
    private Pair<Integer,Integer> goalCoord;
    private Pair<Integer,Integer> myCoord;

    public BinaryPuzzle(boolean[][] board, Pair<Integer, Integer> goalCoord, Pair<Integer, Integer> myCoord) {
        this.board = board;
        this.goalCoord = goalCoord;
        this.myCoord = myCoord;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }

    public Pair<Integer, Integer> getGoalCoord() {
        return goalCoord;
    }

    public void setGoalCoord(Pair<Integer, Integer> goalCoord) {
        this.goalCoord = goalCoord;
    }

    public Pair<Integer, Integer> getMyCoord() {
        return myCoord;
    }

    public void setMyCoord(Pair<Integer, Integer> myCoord) {
        this.myCoord = myCoord;
    }
}
