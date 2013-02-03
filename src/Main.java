import io.PuzzleReader;
import io.SolutionWriter;
import javafx.util.Pair;
import pojo.PuzzleState;
import puzzle.BinaryPuzzle;
import solver.BftsSolver;
import puzzle.PuzzleExpander;

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

        // Create a new PuzzleReader to read the file
        PuzzleReader puzzleReader = null;

        if(args.length != 1){
            System.out.println("Improper use.  Please ensure you provide this program with an argument specifying a puzzle file.");
            System.out.println("You may append the numbers '1-5' to the end of your launch parameters to select a local copy of a puzzle.");
            System.out.println("Alternatively, you may append the absolute file path to the puzzle file.");
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

            // Create a new solver
            BftsSolver solver = new BftsSolver();

            // Run the solver and store the resulting object
            PuzzleState finalState = solver.graphSearch(binaryPuzzle);

            // Write the solution to a file in the /solution/ directory
            SolutionWriter.writeSolution("puzzle" + puzzleReader.getPuzzleNumber() + "solution.txt",finalState);
        }
        else{
            System.out.println("Improper use.  Please ensure you provide this program with an argument specifying a puzzle file.");
            System.out.println("You may append the numbers '1-5' to the end of your launch parameters to select a local copy of a puzzle.");
            System.out.println("Alternatively, you may append the absolute file path to the puzzle file.");
            return;
        }
    }
}
