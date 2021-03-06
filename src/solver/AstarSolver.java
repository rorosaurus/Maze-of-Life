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
        // Keep track of max frontier size
        int maxFrontierSize = 0;

        // Create the initial state
        final State initialState = new State(problem, null);

        // Define the frontier
        LinkedList<State> frontier = new LinkedList<State>();

        // Expand the initial state, adding the elements to the frontier
        frontier.addAll(PuzzleExpander.expand(initialState));

        // Update max frontier size
        if(frontier.size() > maxFrontierSize) maxFrontierSize = frontier.size();

        // Continue until we run out of nodes to test
        while(!frontier.isEmpty()){

            // Sort the array of new nodes by Manhattan distance plus the state's current depth
            Collections.sort(frontier, new HeuristicSorter(Heuristic.ChebyshevPlusDepth));

            // Choose a node to expand on
            State chosenNode = frontier.getFirst();

            // Remove that node from the frontier
            frontier.removeFirst();

            // If the chosen node contains a goal state, then we return the corresponding solution
            if(chosenNode.isGoalState()){
                // Output max frontier size
                System.out.println("Maximum number of states stored in the frontier: " + maxFrontierSize);
                return chosenNode;
            }

            // Expand the chosen node, store as array
            ArrayList<State> expandedNodes = PuzzleExpander.expand(chosenNode);

            // Add resulting nodes to frontier
            frontier.addAll(expandedNodes);

            // Update max frontier size
            if(frontier.size() > maxFrontierSize) maxFrontierSize = frontier.size();
        }
        // If we get this far, we've found no solution
        return null;
    }

    /**
     * Iterative-Deepening - A* Tree Search (ID-A*TS)
     * @return the Solution Puzzle, or null if no solution found
     */
    public State idts(BinaryPuzzle problem){
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
        // Keep track of max frontier size
        int maxFrontierSize = 0;

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

        // Update max frontier size
        if(frontier.size() > maxFrontierSize) maxFrontierSize = frontier.size();

        // Continue until we run out of nodes to test
        while(!frontier.isEmpty()){

            // Sort the array of new nodes by Manhattan distance plus the state's current depth
            Collections.sort(frontier, new HeuristicSorter(Heuristic.Chebyshev));

            // Choose a node to expand on
            State chosenNode = frontier.getFirst();

            // Remove that node from the frontier
            frontier.removeFirst();

            // If the chosen node contains a goal state, then we return the corresponding solution
            if(chosenNode.isGoalState()){
                // Output max frontier size
                System.out.println("Maximum number of states stored in the frontier: " + maxFrontierSize);
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

            // Update max frontier size
            if(frontier.size() > maxFrontierSize) maxFrontierSize = frontier.size();
        }
        // If we get this far, we've found no solution
        return null;
    }
}
