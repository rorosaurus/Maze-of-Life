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

public class Main {

    public static void main(String[] args){
        int puzzleNum = 1;

        PuzzleReader puzzleReader = new PuzzleReader(puzzleNum);

        BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();

        BftsSolver solver = new BftsSolver();

        PuzzleState finalState = solver.graphSearch(binaryPuzzle);

        SolutionWriter.writeSolution("puzzle" + puzzleNum + "solution.txt",finalState);
    }
}
