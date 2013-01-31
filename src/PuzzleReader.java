import javafx.util.Pair;
import pojos.Puzzle;

import java.io.*;

/**
 * User: Rory
 * Date: 1/30/13
 * Time: 4:35 PM
 */

public class PuzzleReader {
    String puzzlePath;

    public PuzzleReader(String puzzlePath) {
        this.puzzlePath = puzzlePath;
    }

    public PuzzleReader(int puzzleNumber){
        // Get the running directory and append the puzzles directory to it
        // NOTE: Java handles conversion of slashes properly and automatically between different environments
        this.puzzlePath = System.getProperty("user.dir")+"/puzzles/";
        // Form the puzzle filename
        this.puzzlePath += "puzzle"+puzzleNumber+".txt";
    }

    public Puzzle readPuzzle(){
        BufferedReader br = null;
        int[][] board = new int[0][];
        Pair<Integer, Integer> goalCoord = null;
        try {
            String currentLine;
            br = new BufferedReader(new FileReader(puzzlePath));

            if ((currentLine = br.readLine()) != null) {
                // First we read the width and height, separate them
                String[] puzzleSize = currentLine.split(" ", 2);
                // Construct our board array by parsing the strings we read
                board = new int[Integer.parseInt(puzzleSize[0])][Integer.parseInt(puzzleSize[1])];
                // Look for goal coords
                if ((currentLine = br.readLine()) != null) {
                    // First we read the width and height, separate them
                    String[] sGoalCoord = currentLine.split(" ", 2);
                    goalCoord = new Pair<Integer, Integer>(Integer.parseInt(sGoalCoord[0]),Integer.parseInt(sGoalCoord[1]));
                    // Read in the rest of the file
                    while ((currentLine = br.readLine()) != null) {

                    }
                }
                else{
                    //todo: handle
                }
            }
            else{
                // todo: handle
            }
        // Catch appropriate exceptions
        } catch (Exception e) {
            // If an exception is thrown here, the file does not exist or is improperly formatted.  Inform the user.
            System.out.println("File does not exist or is improperly formatted.");
            return null;
        // Finally, we need to handle cleanup
        } finally {
            try {
                // Close the file
                if (br != null){
                    br.close();
                }
            // Catch applicable exceptions
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return new Puzzle(board,goalCoord);
    }
}
