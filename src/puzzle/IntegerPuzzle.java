package puzzle;

import java.awt.*;

/**
 * User: Rory
 * Date: 1/31/13
 * Time: 9:43 AM
 */

// todo: consider using this later or trash it

/**
 * This class stores the board as a 2D array of int's
 * This uses a bit more space but saves on overall cpu time, normally
 */
public class IntegerPuzzle implements Puzzle{

    // a 2D int array storing the board
    private int[][] board;
    // A point storing the goal coords
    private Point goalCoord;  // TODO: move this elsewhere?
    // Keep/generate my current coord - it won't change in this state, generate it once for faster access
    private Point myCoord;

    /**
     * Constructor
     * @param board the board state for this puzzle
     * @param goalCoord the location of the goal
     */
    public IntegerPuzzle(int[][] board, Point goalCoord) {
        this.board = board;
        this.goalCoord = goalCoord;

        // TODO: test
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == 2){
                    myCoord = new Point(i,j);
                }
            }
        }
    }

    /**
     * Simple getter, implemented from the Puzzle interface
     * @return the board as a 2D int array
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Simple getter, implemented from the Puzzle interface
     * @return a Point indicating the player's current coords
     */
    public Point getMyCoord() {
        return myCoord;
    }

    /**
     * Simple getter, implemented from the Puzzle interface
     * @return a Point indicating the goal coords
     */
    public Point getGoalCoord() {
        return goalCoord;
    }
}
