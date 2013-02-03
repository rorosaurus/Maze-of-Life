package solver;

import pojo.PuzzleState;
import puzzle.BinaryPuzzle;
import puzzle.PuzzleExpander;

import java.util.ArrayList;

/**
 * User: Rory
 * Date: 2/2/13
 * Time: 3:24 PM
 */

public class BftsSolver {

    public PuzzleState graphSearch(BinaryPuzzle problem){
        // Create the initial state
        final PuzzleState initialState = new PuzzleState(problem, null);

        // Define the frontier
        ArrayList<PuzzleState> frontier = new ArrayList<PuzzleState>();

        // Define the explored states
        ArrayList<PuzzleState> exploredStates = new ArrayList<PuzzleState>();

        // Expand the initial state, adding the elements to the frontier
        frontier.addAll(PuzzleExpander.expand(initialState));

        // Continue until we run out of nodes to test
        while(!frontier.isEmpty()){
            // Chose a node to expand on
            PuzzleState chosenNode = frontier.get(0);

            // Remove that node from the frontier
            frontier.remove(0);

            // If the chosen node contains a goal state, then we return the corresponding solution
            if(chosenNode.isGoalState()){
                // todo: verify we can pull the solution from this single node
                return chosenNode;
            }

            // Add the chosen node to the explored set
            exploredStates.add(chosenNode);

            // Expand the chosen node
            ArrayList<PuzzleState> expandedNodes = PuzzleExpander.expand(chosenNode);

            // Add resulting nodes to frontier only if they're not in the frontier or explored set
            for(PuzzleState state : expandedNodes){
                if(!frontier.contains(state) && !exploredStates.contains(state)){
                    frontier.add(state);
                }
            }
        }
        // todo: return fail if we get this far
        return null;
    }
}
