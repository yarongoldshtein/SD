/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yaron
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Graph g = new Graph("G0.txt");
            g.display();
            System.out.println(g.dijkstra.getRadius());
            System.out.println(g.dijkstra.getDiameter());
            g.endRunFile();
            System.out.println("runTime = "+g.runTime());

            System.out.println(g.dijkstra.getShortestPath(0, 1));
            System.out.println(g.dijkstra.getShortestPathString(0, 2));
            int[] arr = {4};
            System.out.println(g.dijkstra.getShortestPathWithBL(0, 1,arr));
        } catch (IOException ex) {
            Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
