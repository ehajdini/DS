/*
 * 
 * Homework assignment 2
 * Data Structures , Spring 2016
 * 
 **********************/

/***************************************************************************************
*    Title: Algorithms Fourth Edition 
*    Author: Robert Sedgewick and Kevin Wayne
*    Date: 2011
*    Version: 4/E
*    Availability: http://algs4.cs.princeton.edu/13stacks/
*
*    © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne 
*    (All the methods using this notation are regularly referred to in this book. )
***************************************************************************************/

/**
 * The CStack class represents a Stack of integers 
 * It supports the main methods of an usual Stack , 
 * pushing an item , popping it out , checking whether it is empty or not , 
 * and returning Stack's size .
 * It also has a method called check() that will be called in Calculator class
 * This implementation uses a singly-linked list with a nested class of
 * linked-list nodes .
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class CStack {
	private Node first; // The top of the Stack
	private int N;     // Number of items of the Stack

	/**
	 * Nested class of linked-list Nodes; Each node holds a Variable and points
	 * to the next Variable
	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	private class Node {
		Node next;
		int item;
	}

	 /**
     * Create an empty stack.
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	CStack() 
	{
		first = null;
		N = 0;
	}

	
	 /**
     * Push the item into the stack.
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	void push(int i) 
	{
		Node prevFirst = first;
		first = new Node();
		first.item = i;
		first.next = prevFirst;
		N++;
	}

	 /**
     * Delete and return the most recently added item to the stack
     * Firstly it will check whether the Stack is empty 
     * If the Stack is empty and it will try to pop out a value ,
     * it will throw malformedPostfixException
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 * @throws malformedPostfixException 
     */
	int pop() throws malformedPostfixException 
	{
		if (this.isEmpty()) 
		{
				throw new malformedPostfixException("Malformed Postfix Exception is thrown !");
			
		}
		int item = first.item;
		first = first.next;
		N--;
		return item;
	}

	 /**
     * Check whether the stack is empty
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	boolean isEmpty() 
	{
		return first == null;
	}

	 /**
     * Return the number of items in the stack.
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	int size() 
	{
		return N;
	}
	
	/**
	 * After checking all characters of the string, in the Calculator class
	 * and in its respective methods , evaluate and assign ,
	 * check method will be called ;
	 * It means that after checking all elements one by one at the end there might remain
	 * some values in the stack , so operators might not be enough
	 * Therefore , it is a malformed expression
     */
	void check() throws malformedPostfixException{
		if (!this.isEmpty()) 
		{
			throw new malformedPostfixException("Malformed Postfix Exception is thrown !");
		
	}
	}

	
	 /**
     * Returns an iterator to the CStack 
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	public Iterator iterator() 
	{
		return new ListIterator();
	}
	
	public class ListIterator implements Iterator 
	{
		private Node current = first;

		/**
		 * Returns true if the iterator has more elements
    	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
    	 */	
		@Override
		public boolean hasNext() 
		{
			return current != null;
		}
		
		// the iterator, doesn't implement remove() since it's optional
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * The next method will return an integer , since CStack will hold integers
    	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
    	 */	
		@Override
		public Integer next() 
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			int item = current.item;
			current = current.next;
			return item;
		}

	}
}
