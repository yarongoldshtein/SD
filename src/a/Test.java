/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author אליצור
 */
public class Test {

    private String filename;

    public Test(String name_of_file) {
        filename = name_of_file;
        runExec();
    }

    public void runExec() {
        BufferedReader execFile = fileIn(filename);
        String line;
        StringTokenizer st;
        int v1, v2, BL_Number, num_of_queries;
        int[] BLArr;

        try {
            line = execFile.readLine();
            st = new StringTokenizer(line);

            num_of_queries = Integer.parseInt(st.nextToken());

            for (int i = 0; i < num_of_queries - 1; i++) {
                
                v1 = Integer.parseInt(st.nextToken());
                v2 = Integer.parseInt(st.nextToken());
                BL_Number = Integer.parseInt(st.nextToken());
                BLArr = new int[BL_Number];
               
                for (int j = 0; j < BLArr.length; j++) {
                    BLArr[j] = Integer.parseInt(st.nextToken());
                }
                //return short path
            }
            //info

        } catch (IOException ex) {
            Logger.getLogger(Graph_algo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BufferedReader fileIn(String file) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnf) {
            System.out.println("File " + file + " not found.\n");
        } catch (IOException io) {
            System.out.println("File " + file + " has I/O problems.\n");
        }

        return br;
    }
}
