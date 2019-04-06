/*
 * Egla Hajdini
 * 
 * Homework assignment 2
 * Data Structures , Spring 2016
 * 
 **********************/
/***************************************************************************************
 * VList is a linked list used to store Variables 
 * This implementation uses a nested class of linked-list nodes whose elements are
 * Variables 
 * 
 *	It supports main methods such as adding an element in the list and checking whether it 
 *	is empty or not.
 *	In addition it contains other methods :
 *	exist(char) --> checks whether the variable is in the list using iterator
 *	getVar(char) --> will return the value of the variable using iterator
 *	replace(char,int) --> will update the value of the variable
 * 
***************************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class VList implements Iterable {

	private Node head;

	/**
	 * Nested class of linked-list Nodes; Each node holds a Variable and points
	 * to the next Variable
	 */
	private class Node {
		Node next;
		Variable data;
	}

	/**
	 * Creates an empty VList();
	 */
	public VList() {
		head = null;
	}

	/**
	 * Checks whether the list is empty or not
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Will add a Variable in the linked list , if the list is empty the Variable
	 * will be placed in the first node otherwise it will be added in the end
	 */
	public void add(Variable var) {
		Node last = new Node();
		last.data = var;
		last.next = null;

		if (isEmpty()) {
			head = last;
		} else {
			Node temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = last;
		}
	}

/**
 * getVar is a method used to return the value of Variable 
 * Iterate through the list till the element is found and then return its value
 * If the elements doesn't exist in the list 
 * @undefinedVariableException will be thrown
 */
	public int getVar(Character ch) throws undefinedVariableException {
		int value = -1;
		Iterator it = this.iterator();
		while (it.hasNext()) {
			Variable v = (Variable) it.next();
			int k = (int) v.getValue();
			Character var_name = (char) v.getName();
			if (ch.equals(var_name)){
				value = k;
				break;
			}
		}
		if (!exists(ch))
				throw new undefinedVariableException("Undefined Variable Exception is thrown !");
			
		return value;
	}
	
	/**
	 * Exist is a method which is used to determine whether
	 * a Variable is previously defined in the list 
	 * It will return true is the Variable is found
	 * and false otherwise
	 */

	public boolean exists(Character ch) {
		Iterator it = this.iterator();
		while (it.hasNext()) {
			Variable v = (Variable) it.next();
			Character var_name = (char) v.getName();
			if (ch.equals(var_name))
				return true;
		}
		return false;
	}

	/**
	 * This method is used to update the value of a Variable
	 * It will start from the beginning of a list till the Variable is found
	 */
	public void replace(char variable, int value) {
		Node temp = head;
		if (head.data.name == variable)
			head.data.value = value;
		while (temp.next != null) {
			temp = temp.next;
			if (temp.data.name == variable) {
				temp.data.value = value;
				break;
			}
		}
	}

	 /**
     * Returns an iterator to the VList used to iterate through Variables
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	public Iterator iterator() {
		return new ListIterator();
	}


	public class ListIterator implements Iterator<Variable> {
		public Node current = head;

		/**
		 * Returns true if the iteration has more elements 
		 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		// the iterator, doesn't implement remove() since it's optional
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/**
		 * The next method will return an item of Variable type 
		 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
		 */
		@Override
		public Variable next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Variable item = current.data;
			current = current.next;
			return item;
		}
	}
}
