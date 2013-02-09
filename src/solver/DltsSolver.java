package solver;

import pojo.State;
import puzzle.BinaryPuzzle;
import puzzle.PuzzleExpander;

import java.util.ArrayList;

/**
 * User: Rory
 * Date: 2/5/13
 * Time: 10:17 AM
 */

public class DltsSolver {

    /**
     * Solves the puzzle
     * @param problem the Puzzle to solve
     * @return the State that reaches the goal
     */
    public State graphSearch(BinaryPuzzle problem){
        // Create the initial state
        final State initialState = new State(problem, null);

        // Define the frontier
        ArrayList<State> frontier = new ArrayList<State>();

        // Define the explored states
        ArrayList<State> exploredStates = new ArrayList<State>();

        // Expand the initial state, adding the elements to the frontier
        frontier.addAll(PuzzleExpander.expand(initialState));

        // Continue until we run out of nodes to test
        while(!frontier.isEmpty()){
            // Choose a node to expand on
            State chosenNode = frontier.get(0);

            // Remove that node from the frontier
            frontier.remove(0);

            // If the chosen node contains a goal state, then we return the corresponding solution
            if(chosenNode.isGoalState()){
                return chosenNode;
            }

            // Add the chosen node to the explored set
            exploredStates.add(chosenNode);

            // Expand the chosen node
            ArrayList<State> expandedNodes = PuzzleExpander.expand(chosenNode);

            // Add resulting nodes to frontier only if they're not in the frontier or explored set
            for(State state : expandedNodes){
                if(!frontier.contains(state) && !exploredStates.contains(state)){
                    frontier.add(state);
                }
            }
        }
        // If we get this far, we've found no solution
        return null;
    }
}
