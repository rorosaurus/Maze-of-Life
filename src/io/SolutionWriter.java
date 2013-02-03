package io;

import javafx.util.Pair;
import pojo.PuzzleState;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * User: Rory
 * Date: 2/2/13
 * Time: 7:42 PM
 */

public class SolutionWriter {

    public static void writeSolution(String filename, PuzzleState finalState){
        String path = System.getProperty("user.dir")+"/solutions/";
        String filePath = path + filename;

        ArrayList<PuzzleState> solution = new ArrayList<PuzzleState>();

        PuzzleState currentState = finalState;
        solution.add(0,currentState);
        // todo: check to ensure the finalState isn't null and stuff
        while(currentState.getParent() != null){
            currentState = currentState.getParent();
            solution.add(0,currentState);
        }

        try{
            File file = new File(filePath);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fstream = new FileWriter(file.getAbsoluteFile());
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("GOAL FOUND!\n");
            out.write("\n");
            for(PuzzleState state : solution){
                out.write(state.getState().getMyCoord().toString() + "\n");
            }
            out.write("\n");
            for(PuzzleState state : solution){
                boolean[][] board = state.getState().getBoard();
                Pair<Integer,Integer> myCoord = state.getState().getMyCoord();
                for(int i=0;i<board[0].length;i++){
                    String output = "";
                    for(int j=0;j<board.length;j++){
                        if(myCoord.equals(new Pair<Integer, Integer>(j,i))){
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
                out.write("\n");
            }
            out.close();
        }catch (Exception e){
            // todo: handle
            System.out.println();
        }
    }
}
