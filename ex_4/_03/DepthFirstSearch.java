/*
 * 
 * Homework assignment 4
 * Data Structures , Spring 2016
 * 
 **********************/

/***************************************************************************************
*    Title: Algorithms Fourth Edition 
*    Author: Robert Sedgewick and Kevin Wayne
*    Date: 2011
*    Version: 4/E
*    Availability: algs4.cs.princeton.edu/41graph/DepthFirstSearch.java.html
*
*    � Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
*    (All the methods using this notation are regularly referred to in this book. )
***************************************************************************************/

import java.io.File;
import java.util.Scanner;

public class DepthFirstSearch {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;           // number of vertices connected to s

    /**
     * Computes the vertices in graph <tt>G</tt> that are
     * connected to the source vertex <tt>s</tt>.
     * @param G the graph
     * @param s the source vertex
     *  � Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    /** depth first search from v   /**
    * I made a slight change in the methods below
    * A new Bag of Integers (adj) will be declared and it will be assigned to the 
    * first Bag of each Vertex , since the second and third are the same  with the first one
    * If the first bag is Empty (Which means Monkey has eaten first Copy of the Vertex )
    * adj will be assigned to the second Bag
    * line : 50-54
    */
    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        Bag<Integer> adj=G.robust[v][0];  
        if(G.robust[v][0].isEmpty()){
        	adj=G.robust[v][1];
        }
        for (int w : adj) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, <tt>false</tt> otherwise
     *  � Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex <tt>s</tt>.
     * @return the number of vertices connected to the source vertex <tt>s</tt>
     *  � Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
     */
    public int count() {
        return count;
    }

    /**
     * Unit test the DFS from the book 
     */
    public static void main(String[] args) {
    	In in=new In("./input.txt");  //reading file
        Graph G = new Graph(in);  //reading graph from the file
        Out out = new Out("DFS.txt"); //writing file

        G.robustify(); //robustified graph 
        int s = in.readInt(); //source vertex
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        
        //marked Vertices 
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                out.print(v + " ");
        }

        out.println();
       
        if (search.count() != G.V()) out.println("NOT connected");
        else                         out.println("connected");
    }

}