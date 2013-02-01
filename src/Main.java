import io.PuzzleReader;
import pojos.BinaryPuzzle;
import pojos.Puzzle;

/**
 * User: Rory
 * Date: 1/30/13
 * Time: 4:31 PM
 */

public class Main {

    public static void main(String[] args){
        PuzzleReader puzzleReader = new PuzzleReader(3);
        BinaryPuzzle binaryPuzzle = puzzleReader.readBinaryPuzzle();
        Puzzle puzzle = puzzleReader.readPuzzle();

        boolean[][] board = binaryPuzzle.getBoard();

//        for(boolean[] column : board){
//            for(boolean cell : column){
//
//            }
//        }
        System.out.println();
    }
}
