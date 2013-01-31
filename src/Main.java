import pojos.Puzzle;

/**
 * User: Rory
 * Date: 1/30/13
 * Time: 4:31 PM
 */

public class Main {

    public static void main(String[] args){
        PuzzleReader puzzleReader = new PuzzleReader(3);
        Puzzle puzzle = puzzleReader.readPuzzle();
        System.out.println();
    }
}
