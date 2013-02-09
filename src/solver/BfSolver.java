package solver;

import pojo.State;
import puzzle.Puzzle;
import puzzle.PuzzleExpander;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * User: Rory
 * Date: 2/2/13
 * Time: 3:24 PM
 */

/**
 * This class implements the Breadth First searches
 */
public class BfSolver {

    /**
     * Solves the puzzle using Breadth First Tree Search
     * @param problem the Puzzle to solve
     * @return the State that reaches the goal
     */
    public State treeSearch(Puzzle problem){
        // Create the initial state
        final State initialState = new State(problem, null);

        // Define the frontier
        LinkedList<State> frontier = new LinkedList<State>();

        // Expand the initial state, adding the elements to the frontier
        frontier.addAll(PuzzleExpander.expand(initialState));

        // Continue until we run out of nodes to test
        while(!frontier.isEmpty()){
            // Choose a node to expand on
            State chosenNode = frontier.getFirst();

            // Remove that node from the frontier
            frontier.removeFirst();

            // If the chosen node contains a goal state, then we return the corresponding solution
            if(chosenNode.isGoalState()){
                return chosenNode;
            }

            // Expand the chosen node
            ArrayList<State> expandedNodes = PuzzleExpander.expand(chosenNode);

            // Add resulting nodes to frontier
            frontier.addAll(expandedNodes);
        }
        // If we get this far, we've found no solution
        return null;
    }

    /**
     * Solves the puzzle using Breadth First Graph Search
     * @param problem the Puzzle to solve
     * @return the State that reaches the goal
     */
    public State graphSearch(Puzzle problem){
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

            // Expand the chosen node
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
