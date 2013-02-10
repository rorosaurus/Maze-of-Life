import io.PuzzleReader;
import io.SolutionWriter;
import pojo.State;
import puzzle.BinaryPuzzle;
import puzzle.PuzzleExpander;
import solver.BfSolver;
import solver.DfSolver;

import java.awt.*;

/**
 * User: Rory
 * Date: 1/30/13
 * Time: 4:31 PM
 */

/**
 * Why did the programmer quit his job?
 * ...
 * Because he didn't get arrays!
 */
public class Main {

    /**
     * main function, entry point
     * @param args command line arguments passed in
     */
    public static void main(String[] args){

        // TODO: add support for specifying solver type?  maybe parse a string with a switch?

        // Create a new PuzzleReader to read the file
        PuzzleReader puzzleReader = null;

        if(args.length != 1){
            invalidArgs();
            return;
        }
        else{
            try{
                if(Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) <= 5){
                    puzzleReader = new PuzzleReader(Integer.parseInt(args[0]));
                }
            }catch(Exception e){
                System.err.println("arg[0] is not a puzzle number, assuming absolute file path.");
                puzzleReader = new PuzzleReader(args[0]);
            }
        }

        if(puzzleReader != null){
            // Create a new BinaryPuzzle to be solved
            BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();

//            PuzzleExpander.testNumLiveNeighbors(1);

            // Create a new solver
            DfSolver solver = new DfSolver();

            // Run the solver and store the resulting object
            State finalState = solver.idDfts(binaryPuzzle);

            // Write the solution to a file in the /solution/ directory
            SolutionWriter.writeSolution("puzzle" + puzzleReader.getPuzzleNumber() + "solution.txt",finalState);
        }
        else{
            invalidArgs();
            return;
        }
    }

    /**
     * Basic outputs for invalid arguments
     */
    private static void invalidArgs(){
        System.out.println("Improper use.  Please ensure you provide this program with an argument specifying a puzzle file.");
        System.out.println("You may append the numbers '1-5' to the end of your launch parameters to select a local copy of a puzzle.");
        System.out.println("Alternatively, you may append the absolute file path to the puzzle file.");
    }
}
