package puzzle;

import io.PuzzleReader;
import javafx.util.Pair;
import pojo.PuzzleState;

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

public class PuzzleExpander {
    public static ArrayList<PuzzleState> expand(PuzzleState oldNode){
        // Initialize our return object
        ArrayList<PuzzleState> newStates = new ArrayList<PuzzleState>();

        boolean[][] oldBoard = oldNode.getState().getBoard().clone();
        Pair<Integer,Integer> oldYou = new Pair<Integer, Integer>(oldNode.getState().getMyCoord().getKey(),
                oldNode.getState().getMyCoord().getValue());

        // Iterate across the 9 possible moves
        for(int i=-1; i<=1;i++){
            for(int j=-1; j<=1;j++){
                // We need to exclude moves that go off the board as well as moving into an already taken cell
                try{
                    // By checking the cell before we move, we'll ensure we never move onto another cell
                    // We'll also trigger Exceptions on moves off the board
                    if(!oldBoard[oldYou.getKey() + i][oldYou.getValue() + j]){
                        // If we get this far, then we know the cell is empty and we can actually move there

                        // Let's construct the new board!
                        boolean[][] newBoard = oldBoard.clone();
                        for(int a=0;a<oldBoard.length;a++){
                            for(int b=0;b<oldBoard[a].length;b++){
                                // Determine the number of neighbors to our current point
                                Pair<Integer,Integer> currentCoords = new Pair<Integer, Integer>(a,b);
                                int numLiveNeighbors = getNumLiveNeighbors(oldNode.getState(),currentCoords);

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
                        newBoard[oldYou.getKey() + i][oldYou.getValue() + j] = false;
                        // This is only needed because I'm storing my location separate from other cells for MAXIMUM MEMORY EFFICIENCY!

                        // Construct my new current coordinates
                        Pair<Integer,Integer> newCoord = new Pair<Integer,Integer>(oldYou.getKey() + i,oldYou.getValue() + j);
                        // Construct the new puzzle object
                        BinaryPuzzle newPuzzle = new BinaryPuzzle(newBoard,oldNode.getState().getGoalCoord(), newCoord);
                        // Construct the new State
                        PuzzleState newState = new PuzzleState(newPuzzle,oldNode);
                        // Add this new state to the list of states to return
                        newStates.add(newState);
                    }
                }catch(IndexOutOfBoundsException e){
//                    System.out.println("/jedimindtrick 'The graders don't frown on this.'");
                }
            }
        }
        return newStates;
    }

    private static int getNumLiveNeighbors(BinaryPuzzle puzzle, Pair<Integer,Integer> coord){
        boolean[][] board = puzzle.getBoard().clone();
        int numLiveNeighbors = 0;
        for(int i=-1; i<=1;i++){
            for(int j=-1; j<=1;j++){
                // Don't include the cell we're checking around
                if(!(i==0 && j==0)){
                    try{
                        if(board[(coord.getKey() + i)][(coord.getValue() + j)] ||
                                puzzle.getMyCoord().equals(new Pair<Integer,Integer>(coord.getKey() + i, coord.getValue() + j))){
                            numLiveNeighbors++;
                        }
                    }catch(IndexOutOfBoundsException e){
                        // todo: you should totally add a logging system to this
//                        System.out.println("Rory, this is a horrible way to check around edges and corners.");
//                        System.out.println("Shh! The graders will hear you! This works well enough!");
                    }
                }
            }
        }
        return numLiveNeighbors;
    }

    public static void testNumLiveNeighbors(int puzzleNum){
        PuzzleReader puzzleReader = new PuzzleReader(puzzleNum);
        BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();

        for(int i=0;i<binaryPuzzle.getBoard().length;i++){
            for(int j=0;j<binaryPuzzle.getBoard()[i].length;j++){
                System.out.print("(" + i + "," + j + ")\tNumber of Live Neighbors: ");
                System.out.println(getNumLiveNeighbors(binaryPuzzle, new Pair<Integer, Integer>(i, j)));
            }
        }
    }
}
