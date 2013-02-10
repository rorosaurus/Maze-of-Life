package solver;

import pojo.State;
import puzzle.BinaryPuzzle;
import puzzle.PuzzleExpander;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * User: Rory
 * Date: 2/4/13
 * Time: 10:16 AM
 */

/**
 * This class implements Depth First searches
 */
public class DfSolver {

    /**
     * Iterative-Deepening - Depth First Tree Search (ID-DFTS)
     * @return the Solution Puzzle, or null if no solution found
     */
    public State idDfts(BinaryPuzzle problem){
        // for i = 0 to ∞ do
        for(int depth=0;depth<Integer.MAX_VALUE;depth++){

            // result ← DLS(problem,i)
            State result = dlts(problem,depth);

            // if result ≠ cutoff then return result
            if(result != null){
                return result;
            }
        }
        return null;
    }

    /**
     * Depth Limited Tree Search
     * @param problem the puzzle to solve
     * @param maxDepth the maximum depth to go to
     * @return the State object storing the solution
     */
    public State dlts(BinaryPuzzle problem, int maxDepth){
        // Create the initial state
        final State initialState = new State(problem, null);

        // Define the frontier
        LinkedList<State> frontier = new LinkedList<State>();

        // Expand the initial state, adding the elements to the frontier
        ArrayList<State> initialNodes = PuzzleExpander.expand(initialState);

        // Add resulting nodes to frontier
        for(State node : initialNodes){
            // Only add the node to the frontier if it is not deeper than the maxDepth
            if(!(node.getDepth() > maxDepth)){
                frontier.addFirst(node);
            }
        }

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
            for(State node : expandedNodes){
                // Only add the node to the frontier if it is not deeper than the maxDepth
                if(!(node.getDepth() > maxDepth)){
                    frontier.addFirst(node);
                }
            }
        }
        // If we get this far, we've found no solution
        return null;
    }

    /**
     * Depth First Tree Search
     * @param problem the Puzzle to solve
     * @return a State object storing the solution
     */
    public State dfts(BinaryPuzzle problem){
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
            for(State node : expandedNodes){
                frontier.addFirst(node);
            }
        }
        // If we get this far, we've found no solution
        return null;
    }

    /**
     * Depth First Graph Search
     * @param problem the Puzzle to solve
     * @return a State object storing the solution
     */
    public State dfgs(BinaryPuzzle problem){
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
                    frontier.addFirst(state);
                }
            }
        }
        // If we get this far, we've found no solution
        return null;
    }
}
