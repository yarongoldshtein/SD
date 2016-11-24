package a;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asus
 */
import java.util.*;
import java.io.*;

public class Dijkstra {
    /*
     Dijkstra's Shortest Path Algorithm
     The following program gives the shortest path from the source to all other nodes
     */

    private double[][] djMatrix;
    private int numVerts, numEdges;
    private double INFINITY = Double.POSITIVE_INFINITY;

    public Dijkstra(double[][] adjMatrix, int numVerts, int numEdges) {

        this.djMatrix = new double[numVerts][numVerts];
        this.numEdges = numEdges;
        this.numVerts = numVerts;
        //copy adjMatrix
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                djMatrix[i][j] = adjMatrix[i][j];
            }
        }//end copy adjMatrix

    }

    public double getDijkstra(int s) {
        /*shortestPathFinder method needs to static since non static method cannot 
         be referenced from a static method*/

        double distance[] = new double[numVerts];
        int previous[] = new int[numVerts];
        int visited[] = new int[numVerts];
        int count, nextNode = s, i, j;
        double minimumDistance;

        for (i = 0; i < numVerts; i++) {
            distance[i] = djMatrix[s][i];
            previous[i] = s;
            visited[i] = 0;
        }
        distance[s] = 0;
        visited[s] = 1;
        count = 1;
        while (count < numVerts-1 ) {
            minimumDistance = INFINITY;
            for (i = 0; i < numVerts; i++) {
                if (distance[i] < minimumDistance && (visited[i] == 0)) {
                    minimumDistance = distance[i];
                    nextNode = i;
                }
            }
            visited[nextNode] = 1;
            for (i = 0; i < numVerts; i++) {
                if (visited[i] == 0) {
                    if (minimumDistance + djMatrix[nextNode][i] < distance[i]) {
                        distance[i] = minimumDistance + djMatrix[nextNode][i];
                        previous[i] = nextNode;
                    }
                }
            }
            count++;
        }
        double max = 0, index = 0;
        for (i = 0; i < numVerts; i++) {
            if (distance[i] > max) {
                max = distance[i];
                index = i;
            }
        }
        //System.out.println("dijkstra round: \n"+Arrays.toString(distance));
        //System.out.println("Distance to " + index + " from source " + s + " is " + max);
        return max;
    }

}
