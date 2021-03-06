package solver;

import pojo.Heuristic;
import pojo.State;
import puzzle.BinaryPuzzle;
import puzzle.PuzzleExpander;
import sorter.HeuristicSorter;

import java.util.*;

/**
 * User: Rory
 * Date: 2/13/13
 * Time: 10:27 AM
 */

/**
 * This class implements Greedy Best First Searches
 */
public class GbfSolver {

    /**
     * Solves the puzzle using Greedy Best First Graph Search
     * @param problem the Puzzle to solve
     * @return the State that reaches the goal
     */
    public State graphSearch(BinaryPuzzle problem){
        // Create the initial state
        final State initialState = new State(problem, null);

        // Define the frontier
        LinkedList<State> frontier = new LinkedList<State>();

        // Define the explored states
        LinkedList<State> exploredStates = new LinkedList<State>();

        // Expand the initial state, adding the elements to the frontier
        frontier.addAll(PuzzleExpander.expand(initialState));

        // Continue until we run out of nodes to test
        while(!frontier.isEmpty()){

            // Sort the array of new nodes by Manhattan distance
            Collections.sort(frontier, new HeuristicSorter(Heuristic.Manhattan));

            // Choose a node to expand on
            State chosenNode = frontier.getFirst();

            // Remove that node from the frontier
            frontier.removeFirst();

            // If the chosen node contains a goal state, then we return the corresponding solution
            if(chosenNode.isGoalState()){
                return chosenNode;
            }

            // Add the chosen node to the explored set
            exploredStates.addLast(chosenNode);

            // Expand the chosen node, store as array
            ArrayList<State> expandedNodes = PuzzleExpander.expand(chosenNode);

            // Add resulting nodes to frontier only if they're not in the frontier or explored set
            for(State state : expandedNodes){
                if(!frontier.contains(state) && !exploredStates.contains(state)){
                    frontier.addLast(state);
                }
            }
        }
        // If we get this far, we've found no solution
        return null;
    }
}
