/*
 * Egla Hajdini
 * 
 * Homework assignment 3
 * Data Structures , Spring 2016
 * 
 **********************/

/**
 * Node is a helper class , used to implement the BST
 */

public class Node {
	 public int value;         // associated data
     Node left;  			  // left and right subtrees
     Node right;
     public Node(){}			
     
     /*Constructor */
     public Node(int value) {
         this.value = value;
     }
}
