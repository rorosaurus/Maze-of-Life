package io;

import pojo.State;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * User: Rory
 * Date: 2/2/13
 * Time: 7:42 PM
 */

/**
 * This class handles the writing of the solution file
 * NOTE: Writer does not handle creation of folders
 */
public class SolutionWriter {

    /**
     * This static method write the solution file
     * @param filename the name of the file
     * @param finalState the State that reach the goal
     */
    public static void writeSolution(String filename, State finalState){
        // Form the absolute file path
        String path = System.getProperty("user.dir")+"/solutions/";
        String filePath = path + filename;

        // Construct a list of solutions for easy parsing
        ArrayList<State> solution = new ArrayList<State>();
        if(finalState != null){
            State currentState = finalState;
            solution.add(0,currentState);
            while(currentState.getParent() != null){
                currentState = currentState.getParent();
                solution.add(0,currentState);
            }
        }

        try{
            // Create the file and necessary directories
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            // TODO: ensure writer can write to solutions folder (or create it)

            FileWriter fstream = new FileWriter(file.getAbsoluteFile());
            BufferedWriter out = new BufferedWriter(fstream);

            if(finalState != null){
                // We found a solution, output to file
                out.write("GOAL FOUND!\n");

                // Output the solution
                out.write("\nSolution\n\n");
                // I use a different coordinate system internally
                out.write("(" + solution.get(0).getPuzzle().getMyCoord().x + "," +
                                solution.get(0).getPuzzle().getMyCoord().y + ") ");
                for(int i=1;i<solution.size();i++){
                    State state = solution.get(i);
                    out.write("-> (" + state.getPuzzle().getMyCoord().x + "," +
                                        state.getPuzzle().getMyCoord().y + ")\n");
                    if(i < solution.size()-1){
                        out.write("(" + state.getPuzzle().getMyCoord().x + "," +
                                        state.getPuzzle().getMyCoord().y + ") ");
                    }
                }

                // Output the board states
                out.write("\nBoard States\n");
                for(State state : solution){
                    out.write("\n");
                    boolean[][] board = state.getPuzzle().getBinaryBoard();
                    Point myCoord = state.getPuzzle().getMyCoord();
                    for(int i=board[0].length-1;i>=0;i--){
                        String output = "";
                        for(int j=0;j<board.length;j++){
                            if(myCoord.equals(new Point(j,i))){
                                output += "2";
                            }
                            else if(board[j][i]){
                                output += "1";
                            }
                            else{
                                output += "0";
                            }
                        }
                        out.write(output + "\n");
                    }
                }

                // Output the path cost
                out.write("\nPath Cost\n\n");
                out.write(Integer.toString(solution.size()-1));
            }
            else{
                out.write("GOAL NOT FOUND!");
            }
            out.close();
            // Catch and handle exceptions
        }catch (Exception e){
            System.out.println("There was an error writing the file.  Please ensure you have appropriate permissions.");
        }
    }
}
