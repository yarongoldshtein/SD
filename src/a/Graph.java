package a;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yaron
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graph {

    /**
     * class definitions
     */
    private static final String INF = "&";
    private static final double INFINITY = Double.POSITIVE_INFINITY;

    /**
     * class variables
     */
    private int numVerts; //  num of vertex
    private int numEdges; //  num of Edges
    private double[][] adjMatrix;
    private BufferedReader GraphStream;
    public double current_edge_weight;
    private int[] next;
    Graph_algo dijkstra;
    private long startTime;
    private long endTime;

    /**
     * default constructor included for completeness
     */
    public Graph() {
        new Graph(10);
    }

    /**
     * constructor; initializes new Graph to all INFINITY
     *
     * @param numVs the number of vertices in the Graph
     */
    public Graph(int numVs) {
        numVerts = numVs;
        adjMatrix = new double[numVerts][numVerts];

        this.resetGraph();

        // initializes neighbor array
        next = new int[numVerts];
        for (int i = 0; i < numVerts; i++) {
            next[i] = -1;
        }
    }

    /**
     * constructor; inputs Graph located in file
     *
     * @throws IOException if file is empty or cannot be opened
     */
    public Graph(String filename) throws IOException {
        startRunFile();
        GraphStream = fileIn(filename); //BufferReader
        inputGraph();
        // initializes neighbor array
        next = new int[numVerts];
        dijkstra = new Graph_algo(adjMatrix, numVerts, numEdges);///// needs to be edited!!

       // resetnext();

    }

    /**
     * resets adjacency matrix to all INFINITY
     */
    public void resetGraph() {
        for (int row = 0; row < numVerts; row++) {
            for (int col = 0; col < numVerts; col++) {
                adjMatrix[row][col] = INFINITY;
            }
            adjMatrix[row][row] = 0;
        }
    }

    /**
     * opens a buffered reader using input filename
     *
     * @param file the input filename
     * @return a buffered reader
     */
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

    /**
     * inputs the text Graph into an adjacency matrix
     */
    private void inputGraph() {
        int row, col;
        String line, current;
        StringTokenizer st;
        double weight;

        try {
            // get number of vertices from first line
            line = GraphStream.readLine();
            st = new StringTokenizer(line);
            current = st.nextToken();
            numVerts = Integer.parseInt(current);

            // get number of edegs from first line
            line = GraphStream.readLine();
            st = new StringTokenizer(line);
            current = st.nextToken();
            numEdges = Integer.parseInt(current);

            adjMatrix = new double[numVerts][numVerts];

            for (int i = 0; i < numEdges; i++) {
                line = GraphStream.readLine();
                st = new StringTokenizer(line);
                row = Integer.parseInt(st.nextToken());
                col = Integer.parseInt(st.nextToken());
                weight = Double.parseDouble(st.nextToken());
                adjMatrix[row][col] = weight;
                adjMatrix[col][row] = weight;
            }
            for (int i = 0; i < numVerts; i++) {
                for (int j = 0; j < numVerts; j++) {
                    if (i == j) {
                        adjMatrix[i][j] = 0.0;
                    } else if (adjMatrix[i][j] == 0.0) {
                        adjMatrix[i][j] = INFINITY;
                    }
                }
            }
        } catch (IOException io) {
            System.out.println("File has I/O problems.\n");
        } catch (NumberFormatException nfe) {
            System.out.println(" File must contain only integers.\n");
        } finally {
            try {
                if (GraphStream != null) {
                    GraphStream.close();
                }
            } catch (IOException io2) {
                System.out.println("Unable to close file.\n");
            }
        }
    }


    /**
     * prints adjacency matrix to screen
     */
    public void display() {
        for (int row = 0; row < numVerts; row++) {
            for (int col = 0; col < numVerts; col++) // prints symbol instead of 999999999
            {
                if (adjMatrix[row][col] > INFINITY - 0.01 * INFINITY) {
                    System.out.print("& ");
                } else {
                    System.out.print("" + adjMatrix[row][col] + '\t');
                }
            }

            System.out.println();
        }

        System.out.println("\n");
    }

    /**
     * accessor for class variable numVerts
     *
     * @return number of vertices in Graph
     */
    public int getvertex() {
        return numVerts;
    }


    public void resetnext() {
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                if ((adjMatrix[i][j] != 0.0) && (adjMatrix[i][j] != INFINITY)) {
                    next[i]++;
                }
            }

        }
    }// end method resetnext()

    public void startRunFile() {
        startTime = System.currentTimeMillis();
    }

    public void endRunFile() {
        endTime = System.currentTimeMillis();
    }

    public long runTime() {
        return endTime - startTime;
    }

    public int getEdges() {
        return numEdges;
    }
    
}
