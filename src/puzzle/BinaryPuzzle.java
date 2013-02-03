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

    // 2D array of booleans to represent dead/alive cells on the board (excluding player)
    private boolean[][] board;

    // The coordinates of the player
    private Pair<Integer,Integer> myCoord;

    // The coordinates of the goal
    private Pair<Integer,Integer> goalCoord;
    // TODO: store this goalCoord somewhere else.. there's no need for it to be duplicated in thousands of instances

    /**
     * Constructor
     * @param board a 2D array of booleans to represent dead/alive cells on the board (excluding player)
     * @param goalCoord the coords of the goal
     * @param myCoord the coords of the player
     */
    public BinaryPuzzle(boolean[][] board, Pair<Integer, Integer> goalCoord, Pair<Integer, Integer> myCoord) {
        this.board = board;
        this.goalCoord = goalCoord;
        this.myCoord = myCoord;
    }

    /**
     * Simple getter
     * @return the board
     */
    public boolean[][] getBoard() {
        return board;
    }

    /**
     * Simple getter
     * @return the goal coords
     */
    public Pair<Integer, Integer> getGoalCoord() {
        return goalCoord;
    }

    /**
     * Simple getter
     * @return the player coords
     */
    public Pair<Integer, Integer> getMyCoord() {
        return myCoord;
    }
}
