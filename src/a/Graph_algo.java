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

public class Graph_algo {

    /*
     Graph_algo's Shortest Path Algorithm
     The following program gives the shortest path from the source to all other nodes
     */
    private double[][] djMatrix;
    private int numVerts, numEdges;
    private double INFINITY = Double.POSITIVE_INFINITY;
    private double[] dijkstraValues;
    private boolean triangle_inequality = true;

    public Graph_algo(double[][] adjMatrix, int numVerts, int numEdges) {
        this.dijkstraValues = new double[numVerts];
        this.djMatrix = new double[numVerts][numVerts];
        this.numEdges = numEdges;
        this.numVerts = numVerts;
        //copy adjMatrix
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                djMatrix[i][j] = adjMatrix[i][j];
            }
        }//end copy adjMatrix
        resetdijkstraValues();
    }

    public double getDijkstra(int s) {
        /*shortestPathFinder method needs to static since non static method cannot 
         be referenced from a static method*/

        if (s > numVerts || s < 0) {
            return -1;
        }
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
        while (count < numVerts - 1) {
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
                         if ((djMatrix[s][i] < INFINITY)&&(djMatrix[nextNode][i] < INFINITY)&&(djMatrix[nextNode][s] < INFINITY)) {
                            triangle_inequality = false;
                        }
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
        return max;
    }

    private void resetdijkstraValues() {
        for (int i = 0; i < dijkstraValues.length; i++) {
            dijkstraValues[i] = getDijkstra(i);
        }
    }

    public double getDiameter() {
        double max = 0;
        for (int i = 0; i < dijkstraValues.length; i++) {
            if (dijkstraValues[i] > max) {
                max = dijkstraValues[i];
            }
        }
        return max;
    }

    public double getRadius() {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < dijkstraValues.length; i++) {
            if (dijkstraValues[i] < min) {
                min = dijkstraValues[i];
            }
        }
        return min;
    }

    public double getShortestPath(int start, int end) {
        if (end > numVerts || end < 0 || start > numVerts || start < 0) {
            return -1;
        }
        double distance[] = new double[numVerts];
        int previous[] = new int[numVerts];
        int visited[] = new int[numVerts];
        int count, nextNode = start, i, j;
        double minimumDistance;

        for (i = 0; i < numVerts; i++) {
            distance[i] = djMatrix[start][i];
            previous[i] = start;
            visited[i] = 0;
        }
        distance[start] = 0;
        visited[start] = 1;
        count = 1;
        while (count < numVerts - 1) {
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
        return distance[end];
    }

    public String getShortestPathString(int start, int end) {
        if (end > numVerts || end < 0 || start > numVerts || start < 0) {
            return "Error: one or both of the verts are not exist";
        }
        double distance[] = new double[numVerts];
        int previous[] = new int[numVerts];
        int visited[] = new int[numVerts];
        int count, nextNode = start, i, j;
        double minimumDistance;

        for (i = 0; i < numVerts; i++) {
            distance[i] = djMatrix[start][i];
            previous[i] = start;
            visited[i] = 0;
        }
        distance[start] = 0;
        visited[start] = 1;
        count = 1;
        while (count < numVerts - 1) {
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
        String ans = "" + end;
        int path = end;
        while (path != start) {
            ans = previous[path] + "," + ans;
            path = previous[path];
        }
        return ans;
    }

    public double getShortestPathWithBL(int start, int end, int[] BL) {
        if (end > numVerts || end < 0 || start > numVerts || start < 0) {
            return -1;
        }
        double distance[] = new double[numVerts];
        int previous[] = new int[numVerts];
        int visited[] = new int[numVerts];
        int count, nextNode = start, i, j;
        double minimumDistance;

        for (i = 0; i < numVerts; i++) {
            distance[i] = djMatrix[start][i];
            previous[i] = start;
            if (arrContain(BL, i)) {
                visited[i] = 1;
            } else {
                visited[i] = 0;
            }
        }
        distance[start] = 0;
        visited[start] = 1;
        count = 1;
        while (count < numVerts - 1) {
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
        return distance[end];
    }

    public boolean arrContain(int[] arr, int i) {
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == i) {
                return true;
            }
        }
        return false;
    }

    public String isTriangle_inequality() {
        if (triangle_inequality) {
            return "TIE";
        } else {
            return "!TIE";
        }
    }
}
