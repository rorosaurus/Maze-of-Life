package puzzle;

import java.awt.*;

/**
 * User: Rory
 * Date: 1/31/13
 * Time: 9:43 AM
 */

// TODO: prepare this class for future use
public class IntegerPuzzle {
    private int[][] board;
    private Point goalCoord;

    public IntegerPuzzle(int[][] board, Point goalCoord) {
        this.board = board;
        this.goalCoord = goalCoord;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Point getGoalCoord() {
        return goalCoord;
    }

    public void setGoalCoord(Point goalCoord) {
        this.goalCoord = goalCoord;
    }
}
