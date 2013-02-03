import io.PuzzleReader;
import javafx.util.Pair;
import pojo.PuzzleState;
import puzzle.BinaryPuzzle;
import puzzle.IntegerPuzzle;
import solver.BftsSolver;
import puzzle.PuzzleExpander;

/**
 * User: Rory
 * Date: 1/30/13
 * Time: 4:31 PM
 */

public class Main {

    public static void main(String[] args){
        PuzzleReader puzzleReader = new PuzzleReader(3);

        BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();

        BftsSolver solver = new BftsSolver();

        PuzzleState finalState = solver.graphSearch(binaryPuzzle);

        System.out.println();
        // todo: construct and output solution
    }

    public static void testNumLiveNeighbors(int puzzleNum){
        PuzzleReader puzzleReader = new PuzzleReader(puzzleNum);

        BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();

        for(int i=0;i<binaryPuzzle.getBoard().length;i++){
            for(int j=0;j<binaryPuzzle.getBoard()[i].length;j++){
                System.out.print("(" + i + "," + j + ")\tNumber of Live Neighbors: ");
                System.out.println(PuzzleExpander.getNumLiveNeighbors(binaryPuzzle,new Pair<Integer, Integer>(i,j)));
            }
        }
    }
}
