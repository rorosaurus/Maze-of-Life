package pojo;

import puzzle.Puzzle;

/**
 * User: Rory
 * Date: 1/31/13
 * Time: 9:52 AM
 */

/**
 * This class is the basic puzzle object
 * It stores the current "puzzle" and the parent puzzle
 * Other member variables mentioned in the Powerpoint slides are omitted for memory efficiency
 * since they are not needed the way I have structured this search
 */
public class State {

    // The layout of the board during this puzzle
    private Puzzle puzzle;

    // The puzzle that expanded into this puzzle
    private State parent;

    // The location that you moved to to obtain this puzzle
    //private Point action;

    // The cost to move to this puzzle from the parent
//    private int pathcost;

    /**
     * Constructor
     * @param puzzle the current puzzle of the puzzle
     * @param parent the parent State, to allow solution tracing
     */
    public State(Puzzle puzzle, State parent) {
        this.puzzle = puzzle;
        this.parent = parent;
    }


    // todo: verify this works for the goal... make sure the y's are right
    /**
     * Function to determine if the goal has been reached
     * @return a boolean indicating if the goal has been reached
     */
    public boolean isGoalState(){
        // Note: Implementation in PuzzleExpander ensures that any State that calls this function cannot have
        // placed the player in a position to die
        return puzzle.getGoalCoord().equals(puzzle.getMyCoord());
    }

    /**
     * Simple getter
     * @return the BinaryPuzzle associated with this puzzle
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**
     * Simple getter
     * @return the State that gave rise to this puzzle
     */
    public State getParent() {
        return parent;
    }
}
