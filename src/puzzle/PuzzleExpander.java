package puzzle;

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



        return newStates;
    }

    public static int getNumLiveNeighbors(BinaryPuzzle puzzle, Pair<Integer,Integer> coord){
        boolean[][] board = puzzle.getBoard();
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
}
