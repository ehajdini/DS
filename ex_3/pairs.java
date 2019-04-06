/*
 * Egla Hajdini
 * 
 * Homework assignment 3
 * Data Structures , Spring 2016
 * 
 **********************/

/**
 * pairs is a class which I have created in order to generate a
 * pair of two random numbers , that monkey will swap.
 * It contains two nodes , a constructor and respective getters and setters
 * getX(),getY(),setX(),setY()
 */

public class pairs {

	Node x;
	Node y;
	
	//constructor
	public pairs(Node x, Node y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Node getX() {
		return x;
	}
	public void setX(Node x) {
		this.x = x;
	}
	public Node getY() {
		return y;
	}
	public void setY(Node y) {
		this.y = y;
	}

}
