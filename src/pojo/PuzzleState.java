package pojo;

import javafx.util.Pair;
import puzzle.BinaryPuzzle;

/**
 * User: Rory
 * Date: 1/31/13
 * Time: 9:52 AM
 */

public class PuzzleState {

    // The layout of the board during this state
    private BinaryPuzzle state;

    // The state that expanded into this state
    private PuzzleState parent;

    // The location that you moved to to obtain this state
    // todo: not necessary?  same as state.getMyCoord()?
//    private Pair<Integer,Integer> action;

    // The cost to move to this state from the parent
    // todo: also not necessary?  in this problem all costs should be 1 between states.
//    private int pathcost;

//    public PuzzleState(BinaryPuzzle state, PuzzleState parent, Pair<Integer, Integer> action, int pathcost) {
//        this.state = state;
//        this.parent = parent;
//        this.action = action;
//        this.pathcost = pathcost;
//    }

    public PuzzleState(BinaryPuzzle state, PuzzleState parent) {
        this.state = state;
        this.parent = parent;
    }

    public boolean isGoalState(){
        // todo: ensure that you do not die in this state?
        return state.getGoalCoord().equals(state.getMyCoord());
    }

    public BinaryPuzzle getState() {
        return state;
    }

    public PuzzleState getParent() {
        return parent;
    }
}
