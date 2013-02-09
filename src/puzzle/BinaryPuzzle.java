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
public class BinaryPuzzle implements Puzzle{

    // 2D array of booleans to represent dead/alive cells on the board (excluding player)
    private boolean[][] board;

    // The coordinates of the player
    private Point myCoord;

    // The coordinates of the goal
    private Point goalCoord;
    // TODO: experiment with storing this elsewhere

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
     * Constructor
     * @param board a 2D array of ints to represent dead/alive cells on the board (excluding player)
     * @param goalCoord the coords of the goal
     * @param myCoord the coords of the player
     */
    public BinaryPuzzle(int[][] board, Point goalCoord, Point myCoord) {
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == 0){
                    newBoard[i][j] = false;
                }
                else if(board[i][j] == 1){
                    newBoard[i][j] = true;
                }
            }
        }
        this.board = newBoard;
        this.goalCoord = goalCoord;
        this.myCoord = myCoord;
    }

    /**
     * Simple getter
     * @return the board
     */
    public boolean[][] getBinaryBoard() {
        return board;
    }

    /**
     * Simple getter, implemented Puzzle method
     * @return the goal coords
     */
    public Point getGoalCoord() {
        return goalCoord;
    }

    /**
     * Simple getter, implemented Puzzle method
     * @return the player coords
     */
    public Point getMyCoord() {
        return myCoord;
    }

    /**
     * Implementation of Puzzle interface method
     * @return a 2D int array of the board
     */
    public int[][] getBoard() {
        int[][] result = new int[board.length][board[0].length];
        for(int i=0;i<board[0].length;i++){
            for(int j=0;j<board.length;j++){
                if(myCoord.equals(new Point(j,i))){
                    result[j][i] = 2;
                }
                else if(board[j][i]){
                    result[j][i] = 1;
                }
                else{
                    result[j][i] = 0;
                }
            }
        }
        return result;
    }
}
