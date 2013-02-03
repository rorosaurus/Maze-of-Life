package puzzle;

import java.awt.*;

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
    private Point myCoord;

    // The coordinates of the goal
    private Point goalCoord;
    // TODO: store this goalCoord somewhere else.. there's no need for it to be duplicated in thousands of instances

    /**
     * Constructor
     * @param board a 2D array of booleans to represent dead/alive cells on the board (excluding player)
     * @param goalCoord the coords of the goal
     * @param myCoord the coords of the player
     */
    public BinaryPuzzle(boolean[][] board, Point goalCoord, Point myCoord) {
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
    public Point getGoalCoord() {
        return goalCoord;
    }

    /**
     * Simple getter
     * @return the player coords
     */
    public Point getMyCoord() {
        return myCoord;
    }
}
