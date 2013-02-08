package puzzle;

import java.awt.*;

/**
 * User: Rory
 * Date: 2/2/13
 * Time: 3:20 PM
 */

public interface Puzzle {

    /**
     * A simple getter to obtain the board state
     * @return a 2D array representing the board
     */
    public int[][] getBoard();

    /**
     * A simple getter to obtain the players current coords
     * @return a Pair indicating the x,y location
     */
    public Point getMyCoord();

    /**
     * A simple getter to obtain the goal coords
     * @return a Pair indicating the x,y location
     */
    public Point getGoalCoord();
}
