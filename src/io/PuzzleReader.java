package io;

import puzzle.BinaryPuzzle;
import puzzle.IntegerPuzzle;

import java.awt.*;
import java.io.*;

/**
 * User: Rory
 * Date: 1/30/13
 * Time: 4:35 PM
 */

/**
 * This class handles the reading in of the puzzle files
 * It can return one of two types of IntegerPuzzle objects, one is more convenient to work with
 * The other, the BinaryPuzzle, attempts to reduce memory usage by optimizing how the board is stored
 */
public class PuzzleReader {
    // A string representing the absolute path to the file
    String puzzlePath;

    /**
     * This constructor defines the absolute path to the puzzle
     * @param puzzlePath a String representing the absolute path to the puzzle file
     */
    public PuzzleReader(String puzzlePath) {
        this.puzzlePath = puzzlePath;
    }

    /**
     * This constructor constructs the path to the local puzzle file based on the int provided
     * @param puzzleNumber an int representing which puzzle should be used
     */
    public PuzzleReader(int puzzleNumber){
        // Get the running directory and append the puzzles directory to it
        // NOTE: Java handles conversion of slashes properly and automatically between different environments
        this.puzzlePath = System.getProperty("user.dir")+"/puzzles/";
        // Form the puzzle filename
        this.puzzlePath += "puzzle"+puzzleNumber+".txt";
    }

    /**
     * Simple function to give us the puzzle number
     * @return an int representing the puzzle number
     */
    public int getPuzzleNumber(){
        int result = 0;
        try{
            result = Integer.parseInt(puzzlePath.substring(puzzlePath.length()-5,puzzlePath.length()-4));
        }catch(Exception e){
            System.err.println("Error forming puzzle number.");
        }
        return result;
    }

    /**
     * Simple getter to obtain the current PuzzlePath
     * @return a String representing the path to the puzzle
     */
    public String getPuzzlePath() {
        return puzzlePath;
    }

    /**
     * Simple setter to set the current PuzzlePath
     * @param puzzlePath a String representing the path to the puzzle
     */
    public void setPuzzlePath(String puzzlePath) {
        this.puzzlePath = puzzlePath;
    }

    /**
     * This function reads the current puzzle file and builds the IntegerPuzzle object
     * @return a IntegerPuzzle object, constructed based on the previously defined puzzle file
     */
    public IntegerPuzzle readPuzzle(){
        return readPuzzleFile();
    }

    /**
     * This function reads the current puzzle file and builds the BinaryPuzzle object
     * @return a BinaryPuzzle object, constructed from the previously defined puzzle file
     */
    public BinaryPuzzle readBinaryPuzzle(){
        // Construct the BinaryPuzzle by converting the IntegerPuzzle object
        IntegerPuzzle integerPuzzle = readPuzzleFile();
        int[][] board = integerPuzzle.getBoard();

        // Initialize variables to be used to construct the BinaryPuzzle
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        Point goalCoord = integerPuzzle.getGoalCoord();
        Point myCoord = null;

        for(int i=0; i<board.length;i++){
            for(int j=0; j<board[0].length;j++){
                // Special case for "our" cell
                if(board[i][j] == 2){
                    myCoord = new Point(i,j);
                }
                // All alive cells are set to true
                else if(board[i][j] == 1){
                    newBoard[i][j] = true;
                }
                // All other cells are set to false
                else{
                    newBoard[i][j] = false;
                }
            }
        }

        return new BinaryPuzzle(newBoard,goalCoord,myCoord);
    }

    /**
     * This function handles the dirty work of converting the specified file into our POJO
     * @return a IntegerPuzzle object containing all the information from the file
     */
    private IntegerPuzzle readPuzzleFile(){
        // Initialize variables that need to persist
        BufferedReader br = null;
        int[][] board = new int[0][];
        Point goalCoord = null;

        // Begin reading from file
        try {
            String currentLine;
            br = new BufferedReader(new FileReader(puzzlePath));

            // Safely read first line
            if ((currentLine = br.readLine()) != null) {
                // First we read the width and height, separate them
                String[] puzzleSize = currentLine.split(" ", 2);
                // Construct our board array by parsing the strings we read
                int width = Integer.parseInt(puzzleSize[0]);
                int height = Integer.parseInt(puzzleSize[1]);
                board = new int[width][height];

                // Look for goal coords
                if ((currentLine = br.readLine()) != null) {
                    // First we read the width and height, separate them
                    String[] sGoalCoord = currentLine.split(" ", 2);
                    goalCoord = new Point(Integer.parseInt(sGoalCoord[0]),Integer.parseInt(sGoalCoord[1]));

                    // Read in the rest of the file, filling board and defining our location
                    int rowNum = 0;
                    while ((currentLine = br.readLine()) != null) {
                        // TODO: Do this using just string indexes. Why didn't you do that in the first place, numbskull?
                        String[] row = currentLine.split("");
                        // Start at one because .split("") gives us an empty array[0]
                        for(int i=1; i<row.length;i++){
                            // Use the correct indices for the board, though
                            board[i-1][height - 1 - rowNum] = Integer.parseInt(row[i]);
                        }
                        // Increment our row number in anticipation of the new currentLine
                        rowNum++;
                    }
                }
                else{
                    throw new Exception("File improperly formatted.");
                }
            }
            else{
                throw new Exception("File improperly formatted.");
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

        return new IntegerPuzzle(board,goalCoord);
    }
}
