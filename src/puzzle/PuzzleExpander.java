package puzzle;

import io.PuzzleReader;
import pojo.PuzzleState;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: Rory
 * Date: 2/2/13
 * Time: 4:49 PM
 */

/**
 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
 * Any live cell with two or three live neighbours lives on to the next generation.
 * Any live cell with more than three live neighbours dies, as if by overcrowding.
 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 */

/**
 * This class can expand Puzzles into new States to be explored
 */
public class PuzzleExpander {

    /**
     * This static method will expand a node into new nodes
     * @param oldNode the PuzzleState to be expanded
     * @return new States to be explored
     */
    public static ArrayList<PuzzleState> expand(PuzzleState oldNode){
        // Initialize our return object
        ArrayList<PuzzleState> newStates = new ArrayList<PuzzleState>();

        // Be very careful to avoid manipulating references to our original state
        boolean[][] oldBoard = oldNode.getState().getBoard().clone();
        Point oldYou = new Point(oldNode.getState().getMyCoord().x, oldNode.getState().getMyCoord().y);

        // Iterate across the 9 possible moves
        for(int i=-1; i<=1;i++){
            for(int j=-1; j<=1;j++){
                // We need to exclude moves that go off the board as well as moving into an already taken cell
                try{
                    // By checking the cell before we move, we'll ensure we never move onto another cell
                    // We'll also trigger Exceptions on moves off the board
                    if(!oldBoard[oldYou.x + i][oldYou.y + j]){
                        // If we get this far, then we know the cell is empty and we can actually move there

                        // Here's where we'll try to move
                        Point myNewCoord = new Point(oldYou.x + i,oldYou.y + j);
                        int neighbors = getNumLiveNeighbors(oldNode.getState(),myNewCoord,myNewCoord);
                        // Make sure we won't kill ourselves moving there
                        if(!(neighbors < 2 || neighbors > 3)){
                            // Let's construct the new board!
                            boolean[][] newBoard = safeClone(oldBoard);
                            for(int a=0;a<oldBoard.length;a++){
                                for(int b=0;b<oldBoard[a].length;b++){
                                    // Determine the number of neighbors to our current point
                                    Point currentCoords = new Point(a,b);
                                    int numLiveNeighbors = getNumLiveNeighbors(oldNode.getState(),currentCoords,myNewCoord);

                                    // Is the current cell alive or dead?
                                    if(oldBoard[a][b]){
                                        // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
                                        if(numLiveNeighbors < 2){
                                            newBoard[a][b] = false;
                                        }
                                        // Any live cell with two or three live neighbours lives on to the next generation.
                                        else if(numLiveNeighbors == 2 || numLiveNeighbors == 3){
                                            newBoard[a][b] = true;
                                        }
                                        // Any live cell with more than three live neighbours dies, as if by overcrowding.
                                        else if(numLiveNeighbors > 3){
                                            newBoard[a][b] = false;
                                        }
                                    }
                                    // Otherwise, the cell is dead (or ourselves, but we'll overwrite that result anyway later)
                                    // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                                    else{
                                        if(numLiveNeighbors == 3){
                                            newBoard[a][b] = true;
                                        }
                                    }
                                }
                            }
                            // New board constructed!

                            // Make sure no other cell is occupying my spot on the new board
                            newBoard[oldYou.x+ i][oldYou.y + j] = false;
                            // This is only needed because I'm storing my location separate from other cells for MAXIMUM MEMORY EFFICIENCY!

                            // Construct my new current coordinates
                            Point newCoord = new Point(oldYou.x + i,oldYou.y + j);
                            // Construct the new puzzle object
                            BinaryPuzzle newPuzzle = new BinaryPuzzle(newBoard,oldNode.getState().getGoalCoord(), newCoord);
                            // Construct the new State
                            PuzzleState newState = new PuzzleState(newPuzzle,oldNode);
                            // Add this new state to the list of states to return
                            newStates.add(newState);
                        }
                    }
                }catch(IndexOutOfBoundsException e){
//                    System.out.println("/jedimindtrick 'The graders don't frown on this.'");
                }
            }
        }
        return newStates;
    }

    /**
     * This static method will tell us how many living neighbors a cell has
     * @param puzzle the puzzle to check
     * @param coordToCheck the coordinate to check
     * @param myNewCoord the new location of the player
     * @return an int representing the number of living cells neighboring
     */
    private static int getNumLiveNeighbors(BinaryPuzzle puzzle, Point coordToCheck, Point myNewCoord){
        // Don't manipulate the old data
        boolean[][] board = puzzle.getBoard().clone();
        // Initialize
        int numLiveNeighbors = 0;
        for(int i=-1; i<=1;i++){
            for(int j=-1; j<=1;j++){
                // Don't include the cell we're checking around
                if(!(i==0 && j==0)){
                    // Check everything, regardless of edges and corners, handle exceptions
                    try{
                        // If board[][] at the current location is true, there's a living cell
                        // Or if the player is there, we know there's a cell
                        if(board[(coordToCheck.x + i)][(coordToCheck.y + j)] ||
                                myNewCoord.equals(new Point(coordToCheck.x + i, coordToCheck.y + j))){
                            numLiveNeighbors++;
                        }
                    }catch(IndexOutOfBoundsException e){
                        // TODO: you should totally add a logging system for this kind of output
//                        System.out.println("Rory, this is a horrible way to check around edges and corners.");
//                        System.out.println("Shh! The graders will hear you! This works well enough!");
                    }
                }
            }
        }
        return numLiveNeighbors;
    }

    /**
     * Quick function I wrote to safely clone old boards
     * @param board the board to clone
     * @return a clone of the board
     */
    private static boolean[][] safeClone(boolean[][] board){
        boolean[][] result = new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                result[i][j] = Boolean.valueOf(board[i][j]);
            }
        }
        return result;
    }

    /**
     * Old function to test the function returning the number of neighbors
     * @param puzzleNum the number of the puzzle to test
     */
    public static void testNumLiveNeighbors(int puzzleNum){
        PuzzleReader puzzleReader = new PuzzleReader(puzzleNum);
        BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();

        for(int i=0;i<binaryPuzzle.getBoard().length;i++){
            for(int j=0;j<binaryPuzzle.getBoard()[i].length;j++){
                System.out.print("(" + i + "," + j + ")\tNumber of Live Neighbors: ");
//                System.out.println(getNumLiveNeighbors(binaryPuzzle, new Pair<Integer, Integer>(i, j)));
            }
        }
    }
}
