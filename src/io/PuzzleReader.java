package io;

import javafx.util.Pair;
import pojos.BinaryPuzzle;
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
        return readPuzzleFile();
    }

    public BinaryPuzzle readBinaryPuzzle(){
        Puzzle puzzle = readPuzzleFile();
        int[][] board = puzzle.getBoard();

        boolean[][] newBoard = new boolean[board.length][board[0].length];
        Pair<Integer,Integer> goalCoord = puzzle.getGoalCoord();
        Pair<Integer,Integer> myCoord = null;

        for(int i=0; i<board.length;i++){
            for(int j=0; j<board[0].length;j++){
                if(board[i][j] == 2){
                    myCoord = new Pair<Integer, Integer>(i,j);
                }
                else if(board[i][j] == 1){
                    newBoard[i][j] = true;
                }
                else{
                    newBoard[i][j] = false;
                }
            }
        }

        return new BinaryPuzzle(newBoard,goalCoord,myCoord);
    }

    private Puzzle readPuzzleFile(){
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
                    // Read in the rest of the file, filling board and defining our location
                    int rowNum = 0;
                    while ((currentLine = br.readLine()) != null) {
                        String[] row = currentLine.split("");
                        for(int i=1; i<row.length;i++){
                            board[i-1][rowNum] = Integer.parseInt(row[i]);
                        }
                        rowNum++;
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
