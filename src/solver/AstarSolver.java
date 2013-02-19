package solver;

import pojo.Heuristic;
import pojo.State;
import puzzle.BinaryPuzzle;
import puzzle.PuzzleExpander;
import sorter.HeuristicSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * User: Rory
 * Date: 2/19/13
 * Time: 12:40 PM
 */

public class AstarSolver {
    /**
     * Solves the puzzle using A* Tree Search
     * @param problem the Puzzle to solve
     * @return the State that reaches the goal
     */
    public State treeSearch(BinaryPuzzle problem){
        // Create the initial state
        final State initialState = new State(problem, null);

        // Define the frontier
        LinkedList<State> frontier = new LinkedList<State>();

        // Expand the initial state, adding the elements to the frontier
        frontier.addAll(PuzzleExpander.expand(initialState));

        // Continue until we run out of nodes to test
        while(!frontier.isEmpty()){

            // Sort the array of new nodes by Manhattan distance plus the state's current depth
            Collections.sort(frontier, new HeuristicSorter(Heuristic.ManhattanPlusDepth));

            // Choose a node to expand on
            State chosenNode = frontier.getFirst();

            // Remove that node from the frontier
            frontier.removeFirst();

            // If the chosen node contains a goal state, then we return the corresponding solution
            if(chosenNode.isGoalState()){
                return chosenNode;
            }

            // Expand the chosen node, store as array
            ArrayList<State> expandedNodes = PuzzleExpander.expand(chosenNode);

            // Add resulting nodes to frontier
            frontier.addAll(expandedNodes);
        }
        // If we get this far, we've found no solution
        return null;
    }
}
