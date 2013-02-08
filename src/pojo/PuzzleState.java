package pojo;

import puzzle.BinaryPuzzle;
import puzzle.Puzzle;

/**
 * User: Rory
 * Date: 1/31/13
 * Time: 9:52 AM
 */

/**
 * This class is the basic state object
 * It stores the current "puzzle" and the parent state
 * Other member variables mentioned in the Powerpoint slides are omitted for memory efficiency
 * since they are not needed the way I have structured this search
 */
public class PuzzleState {

    // The layout of the board during this state
    private Puzzle state;

    // The state that expanded into this state
    private PuzzleState parent;

    // The location that you moved to to obtain this state
    //private Point action;

    // The cost to move to this state from the parent
    //private int pathcost;
    // todo: we can probably hardcode this for the time being, using references we should be fine memory-wise

    /**
     * Constructor
     * @param state the current state of the puzzle
     * @param parent the parent PuzzleState, to allow solution tracing
     */
    public PuzzleState(Puzzle state, PuzzleState parent) {
        this.state = state;
        this.parent = parent;
    }

    /**
     * Function to determine if the goal has been reached
     * @return a boolean indicating if the goal has been reached
     */
    public boolean isGoalState(){
        // Note: Implementation in PuzzleExpander ensures that any PuzzleState that calls this function cannot have
        // placed the player in a position to die
        return state.getGoalCoord().equals(state.getMyCoord());
    }

    /**
     * Simple getter
     * @return the BinaryPuzzle associated with this state
     */
    public Puzzle getState() {
        return state;
    }

    /**
     * Simple getter
     * @return the PuzzleState that gave rise to this state
     */
    public PuzzleState getParent() {
        return parent;
    }
}
