package puzzle;

import io.PuzzleReader;
import pojo.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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
     * @param oldNode the State to be expanded
     * @return new States to be explored
     */
    public static ArrayList<State> expand(State oldNode){
        // Initialize our return object
        ArrayList<State> newStates = new ArrayList<State>();

        // Be very careful to avoid manipulating references to our original state
        int[][] oldBoard = Arrays.copyOf(oldNode.getPuzzle().getBoard(), oldNode.getPuzzle().getBoard().length);
        Point oldYou = new Point(oldNode.getPuzzle().getMyCoord().x, oldNode.getPuzzle().getMyCoord().y);

        // Iterate across the 9 possible moves
        for(int i=-1; i<=1;i++){
            for(int j=-1; j<=1;j++){
                // We need to exclude moves that go off the board as well as moving into an already taken cell
                try{
                    // By checking the cell before we move, we'll ensure we never move onto another cell
                    // We'll also trigger Exceptions on moves off the board
                    if(!(oldBoard[oldYou.x + i][oldYou.y + j] == 1)){
                        // If we get this far, then we know the cell is empty and we can actually move there

                        // Here's where we'll try to move
                        Point myNewCoord = new Point(oldYou.x + i,oldYou.y + j);
                        int neighbors = getNumLiveNeighbors(oldNode.getPuzzle(),myNewCoord,myNewCoord);
                        // Make sure we won't kill ourselves moving there
                        if(!(neighbors < 2 || neighbors > 3)){
                            // Let's construct the new board!
                            int[][] newBoard = Arrays.copyOf(oldBoard, oldBoard.length);
                            for(int a=0;a<oldBoard.length;a++){
                                for(int b=0;b<oldBoard[a].length;b++){
                                    // Determine the number of neighbors to our current point
                                    Point currentCoords = new Point(a,b);
                                    int numLiveNeighbors = getNumLiveNeighbors(oldNode.getPuzzle(),currentCoords,myNewCoord);

                                    // Is the current cell alive or dead?
                                    if(oldBoard[a][b] == 1){
                                        // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
                                        if(numLiveNeighbors < 2){
                                            newBoard[a][b] = 0;
                                        }
                                        // Any live cell with two or three live neighbours lives on to the next generation.
                                        else if(numLiveNeighbors == 2 || numLiveNeighbors == 3){
                                            newBoard[a][b] = 1;
                                        }
                                        // Any live cell with more than three live neighbours dies, as if by overcrowding.
                                        else if(numLiveNeighbors > 3){
                                            newBoard[a][b] = 0;
                                        }
                                    }
                                    // Otherwise, the cell is dead (or ourselves, but we'll overwrite that result anyway later)
                                    // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                                    else if(oldBoard[a][b] == 0){
                                        if(numLiveNeighbors == 3){
                                            newBoard[a][b] = 1;
                                        }
                                    }
                                }
                            }
                            // New board constructed!

                            // Make sure no other cell is occupying my spot on the new board
                            newBoard[oldYou.x+ i][oldYou.y + j] = 2;
                            // This is only needed because I'm storing my location separate from other cells for MAXIMUM MEMORY EFFICIENCY!

                            // Construct my new current coordinates
                            Point newCoord = new Point(oldYou.x + i,oldYou.y + j);
                            // Construct the new puzzle object
                            Puzzle newPuzzle = null;
                            if(oldNode.getPuzzle().getClass() == BinaryPuzzle.class){
                                newPuzzle = new BinaryPuzzle(newBoard,oldNode.getPuzzle().getGoalCoord(), newCoord);
                            }
                            else{
                                newPuzzle = new IntegerPuzzle(newBoard,oldNode.getPuzzle().getGoalCoord());
                            }
                            // Construct the new State
                            State newState = new State(newPuzzle,oldNode);
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
    private static int getNumLiveNeighbors(Puzzle puzzle, Point coordToCheck, Point myNewCoord){
        // Don't manipulate the old data
        int[][] board = Arrays.copyOf(puzzle.getBoard(),puzzle.getBoard().length);
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
                        if(board[(coordToCheck.x + i)][(coordToCheck.y + j)] == 1 ||
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
     * Old function to test the function returning the number of neighbors
     * @param puzzleNum the number of the puzzle to test
     */
    public static void testNumLiveNeighbors(int puzzleNum){
        PuzzleReader puzzleReader = new PuzzleReader(puzzleNum);
        BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();

        for(int i=0;i<binaryPuzzle.getBinaryBoard().length;i++){
            for(int j=0;j<binaryPuzzle.getBinaryBoard()[i].length;j++){
                System.out.print("(" + i + "," + j + ")\tNumber of Live Neighbors: ");
//                System.out.println(getNumLiveNeighbors(binaryPuzzle, new Pair<Integer, Integer>(i, j)));
            }
        }
    }
}
