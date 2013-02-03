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
        int puzzleNum = 5;

        // Create a new PuzzleReader to read the file
        PuzzleReader puzzleReader = new PuzzleReader(puzzleNum);

        // Create a new BinaryPuzzle to be solved
        BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();

        // Create a new solver
        BftsSolver solver = new BftsSolver();

        // Run the solver and store the resulting object
        PuzzleState finalState = solver.graphSearch(binaryPuzzle);

        // Write the solution to a file in the /solution/ directory
        SolutionWriter.writeSolution("puzzle" + puzzleNum + "solution.txt",finalState);
    }
}
