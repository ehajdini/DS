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
*    Availability: http://algs4.cs.princeton.edu/41graph/Graph.java.html
*
*    © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
*    (All the methods using this notation are regularly referred to in this book. )
***************************************************************************************/
/**
 * In addition , to graph implementation in task1 it will have toPlain() method 
 * which will transform the graph to the original one 
 */

import java.io.FileNotFoundException;
import java.util.Stack;

public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V; // number of vertices of the original graph , will notchange
	private int E; // number of edges
	private int vertices; // number of vertices of the robustified graph
	private Bag<Integer>[] adj; // an array of Bags
	Bag<Integer> robust[][]; // 2D array of Bags used to robustify the graph

	/**
	 * Firstly it will instantiate a 2D array of Bags with dimensions v*3 , 
	 * where in the first column the adjoint Bag of the first Node will be stored .
	 * In the second and third column the adjoint Bag of the replicated Node will be stored 	 
	 * Then a copy of the adj[] array will be created by calling newadj(...) method
	 * Then by using nested loops and referring to the newadj[] for each node , 
	 * robust[][] will be filled
	 * While iterating in the newadj[] array for each node , there will be two cases:
	 * 1)Where the node will be connected to nodes that have different values from it
	 * 2)Where the node will be connected to nodes with the same value
	 * 
	 * In the 1-st case , three connections (edges) will be added into the robust[i][j] Bag
	 * In the 2-nd case , two connections will be added into the robust[i][j] Bag
	 * 
	 * The number of edges will be incremented 
	 * The number of vertices of the robustfied graph will be tripled
	 * In the end reset method will be called (explained below)
	 */
	public void robustify() {
		robust = new Bag[V][3];
		int edges = 0;
		Bag<Integer>[] adj_new = new Bag[V];
		newadj(adj_new);
		for (int v = 0; v < V; v++) {
			adj_new[v].add(v);
		}

		for (int i = 0; i < V; i++) {
			for (int j = 0; j < 3; j++) {
				robust[i][j] = new Bag();
				for (int w : adj_new[i]) {
					if (w != i) {
						for (int k = 0; k < 3; k++) {
							robust[i][j].add(w);
							if (w < i)
								edges++;
						}
					} else {
							robust[i][j].add(w);						
						edges ++;
					}
				}
			}
		}
		int robustifiedV = V * 3;
		this.vertices = robustifiedV;
		this.adj = adj_new;
		this.E = edges;
		this.robust = robust;
		reset(adj_new);
	}

	/**
	 * This will be used by robustify () method It will take as a parameter an
	 * array of Bags , and it will make a copy of original adj[] array , by
	 * using "for each " loop
	 * 
	 * @param adj_new
	 */
	private void newadj(Bag<Integer>[] adj_new) {
		for (int v = 0; v < V; v++) {
			adj_new[v] = new Bag();
			for (int w : adj[v]) {
				adj_new[v].add(w);
			}
		}
	}

	/**
	 * This method is used by robustify()
	 * It will modify the adjnew[] array , by reading the values from 
	 * robust[][] array , and by adding also the replicated values 
	 * 
	 * @param adj_new
	 */
	public void reset(Bag[] adj_new) {
		for (int i = 0; i < V; i++) {
			adj_new[i] = new Bag();
		}
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < 3; j++) {
				for (int w : robust[i][j]) {
					adj_new[i].add(w);
				}
			}
		}
	}
	
	/**
	 * toPlain method will transform the robustified graph to a simple one
	 * It will be called before and after monkey performs his actions
	 * It take as parameter the output file .
	 * It will instantiate a new simple adjoint array of Bags, sadj[]
	 * It will iterate through the robust [][] 
	 * Firstly it will check if robust[v][j].isEmpty() , which means monkey has eaten 
	 * one of its nodes 
	 * If it is empty it will pass to the next bag which will provide the same info (it 
	 * has the same connections with the previous node)
	 * Then , while iterating through each bag of robust [][]  exist method will be called .
	 * A new node will be added to sadj[] bag :
	 * - if it wasn't before 
	 * - each node will not have connection to itself
	 * 
	 * In the end toFile() will be called , in order to write the graph to the file
	 */
	public void toPlain(Out out, Graph g){
	    Bag<Integer>[] sadj = new Bag[g.V];
	    int cnt = 0;
	    for(int v = 0 ; v < g.V ; v++){
	    	sadj[v] = new Bag();
	    for(int j = 0 ;j < 3 ; j++){
	    	if( !g.robust[v][j].isEmpty() )	    
	    	for(int w : g.robust[v][j]){
	    		if(!exist(w,sadj[v]) && v != w){
	    			sadj[v].add(w);
	    			if( v < w){
	    				cnt++;
	    			}
	    		}
	    	}
	    }
	    }
	   g.vertices = V;
	   g.adj = sadj;
	   g.E = cnt;
	   out.println(g.toFile(g));
	}

	/**
	 * This method will return true if 'w' is in sadj Bag
	 * and will return false otherwise
	 */
	   	private boolean exist(int w, Bag<Integer> sadj) {
	   		for(int v:sadj){
	   			if(v == w)return true;
	   		}
	   		return false;
	}

	/**
	 * Initializes an empty graph with V vertices and 0 edges. param V the
	 * number of vertices
	 *
	 * @param V
	 *            number of vertices
	 * @throws IllegalArgumentException
	 *             if V < 0 
	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public Graph(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	/**
	 * Initializes a graph from an input stream. The format is the number of
	 * vertices V, followed by the number of edges E, followed by E pairs of
	 * vertices, with each entry separated by whitespace.
	 *
	 * @param in
	 *            the input stream
	 * @throws IndexOutOfBoundsException
	 *             if the endpoints of any edge are not in prescribed range
	 * @throws IllegalArgumentException
	 *             if the number of vertices or edges is negative 
	 *	© Algorithms,4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public Graph(In in) {

		this(in.readInt());
		int E = in.readInt();
		if (E < 0)
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
		vertices = V();
	}

	/**
	 * Initializes a new graph that is a deep copy of G>.
	 *
	 * @param G the graph to copy 
	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public Graph(Graph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}

	/**
	 * Returns the number of vertices in this graph.
	 *
	 * @return the number of vertices in this graph
	 * 
	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in this graph.
	 *
	 * @return the number of edges in this graph
	 * 
	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public int E() {
		return E;
	}

	// throw an IndexOutOfBoundsException unless 0 <= v < V
	/**
	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	/**
	 * Adds the undirected edge v-w to this graph.
	 *
	 * @param v
	 *            one vertex in the edge
	 * @param w
	 *            the other vertex in the edge
	 * @throws IndexOutOfBoundsException
	 *             unless both 0 <= v < V and 0 <= w < V
	 * 
	 *© Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	/**
	 * Returns the vertices adjacent to vertex <tt>v</tt>.
	 *
	 * @param v
	 *            the vertex
	 * @return the vertices adjacent to vertex <tt>v</tt>, as an iterable
	 * @throws IndexOutOfBoundsException
	 *             unless 0 <= v < V
	 * 
	 *© Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/**
	 * Returns the degree of vertex <tt>v</tt>.
	 *
	 * @param v
	 *            the vertex
	 * @return the degree of vertex <tt>v</tt>
	 * @throws IndexOutOfBoundsException
	 *             unless 0 <= v < V 
	 *             © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	/**
	 * This method will be used to print the graph It is a slight modification
	 * of toString method (Book implementation) It will print all edges with
	 * respective vertices by using "for each" loop for every graph.adj[v] bag
	 */
	public String toFile(Graph graph) {
		StringBuilder s = new StringBuilder();
		s.append(graph.vertices + " vertices, " + graph.E() + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			for (int w : graph.adj[v]) {
				if (v <= w) {
					s.append(v + " " + w + " ");
					s.append(NEWLINE);
				}
			}

		}
		return s.toString();
	}


    /**
     * Unit tests the <tt>Graph</tt> data type.
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
    	In in=new In("./input.txt");
        Graph G = new Graph(in);
        Out out = new Out("toPlain.txt");

       G.robustify();
       G.toPlain(out,G); 
   
}
}
