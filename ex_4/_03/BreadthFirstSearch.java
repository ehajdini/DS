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
*    Availability: algs4.cs.princeton.edu/41graph/BreadthFirstPaths.java.html
*
*    � Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
*    (All the methods using this notation are regularly referred to in this book. )
***************************************************************************************/
import java.io.File;
public class BreadthFirstSearch {
    static Out out=new Out("BFS.txt");  //writing file
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path

    /**
     * Computes the shortest path between the source vertex <tt>s</tt>
     * and every other vertex in the graph <tt>G</tt>.
     * @param G the graph
     * @param s the source vertex
     * � Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
     */
    public BreadthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);

        assert check(G, s);
    }

    /**
     * Computes the shortest path between any one of the source vertices in <tt>sources</tt>
     * and every other vertex in graph <tt>G</tt>.
     * @param G the graph
     * @param sources the source vertices
     * � Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
     */
    public BreadthFirstSearch(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        bfs(G, sources);
    }


    /**
     * I made a slight change in the methods below
     * A new Bag of Integers (adj) will be declared and it will be assigned to the 
     * first Bag of each Vertex , since the second and third are the same  with the first one
     * If the first bag is Empty (Which means Monkey has eaten first Copy of the Vertex )
     * adj will be assigned to the second Bag
     * 
     * line 79-83  , 104-108 , 167-171 (the same change)
     */ 
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
        distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            Bag<Integer> adj=G.robust[v][0];  
            if(G.robust[v][0].isEmpty()){
            	adj=G.robust[v][1];
            }
            for (int w : adj) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    // breadth-first search from multiple sources
    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            Bag<Integer> adj=G.robust[v][0];  
            if(G.robust[v][0].isEmpty()){
            	adj=G.robust[v][1];
            }
            for (int w : adj) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> (or sources) and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, and <tt>false</tt> otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path between the source vertex <tt>s</tt>
     * (or sources) and vertex <tt>v</tt>?
     * @param v the vertex
     * @return the number of edges in a shortest path
     */
    public int distTo(int v) {
        return distTo[v];
    }

    /**
     * Returns a shortest path between the source vertex <tt>s</tt> (or sources)
     * and <tt>v</tt>, or <tt>null</tt> if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }


    // check optimality conditions for single source
    private boolean check(Graph G, int s) {

        // check that the distance of s = 0
        if (distTo[s] != 0) {
            out.println("distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }

        // check that for each edge v-w dist[w] <= dist[v] + 1
        // provided v is reachable from s
        for (int v = 0; v < G.V(); v++) {
        	 Bag<Integer> adj=G.robust[v][0];  
             if(G.robust[v][0].isEmpty()){
             	adj=G.robust[v][1];
             }
             for (int w : adj) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    out.println("edge " + v + "-" + w);
                    out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    out.println("edge " + v + "-" + w);
                    out.println("distTo[" + v + "] = " + distTo[v]);
                    out.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }
            }
        }

        // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
        // provided v is reachable from s
        for (int w = 0; w < G.V(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1) {
                out.println("shortest path edge " + v + "-" + w);
                out.println("distTo[" + v + "] = " + distTo[v]);
                out.println("distTo[" + w + "] = " + distTo[w]);
                return false;
            }
        }

        return true;
    }

    /**
     * Unit test the BFS from the book 
     */
    public static void main(String[] args) {
    	In in=new In("./input.txt"); //reading from file
        Graph G = new Graph(in);
   
        int s = in.readInt(); //source vertex
        G.robustify();  //robustified graph 
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, s); 

        
        //Prints the paths from the Source to all vertices 
        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                out.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) out.print(x);
                    else        out.print("-" + x);
                }
                out.println();
            }

            else {
                out.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }


}
