/*
 * 
 * Homework assignment 1
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
 * The AlbaStack class represents a Stack of generic items ,
 * which allows removal of some items .
 * 
 * It supports the main methods of an usual Stack , 
 * pushing an item , popping it out , checking whether it is empty or not , 
 * and returning Stack's size .
 * In addition , it has two other methods : 
 * One is used to print the whole stack , another one is used to remove 
 * a specific item .
 * 
 * This implementation uses a singly-linked list with a nested class of
 * linked-list nodes .
 * 
 *  <Item> is the generic type of an Item in AlbaStack
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AlbaStack<Item> implements Iterable<Item> {

	private Node first; // The top of the Stack
	private int N;     // Number of items of the Stack

	 /**
     * Linked List Class
     *© Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	private class Node {
		Node next;
		Item item;
	}

	 /**
     * Create an empty stack.
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	AlbaStack() 
	{
		first = null;
		N = 0;
	}

	
	 /**
     * Push the item to the stack.
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	void push(Item item) 
	{
		Node prevFirst = first;
		first = new Node();
		first.item = item;
		first.next = prevFirst;
		N++;
	}

	 /**
     * Delete and return the most recently added item to the stack
     * If stack is Empty it will throw an exception
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	Item pop() 
	{
		if (this.isEmpty()) 
		{
			throw new NoSuchElementException("Stack underflow");
		}
		Item item = first.item;
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
     * Remove a specific item from the Stack by iterating through it.
     * Remove() method is defined in iterator interface , and it is 
     * called only after next() method.
     * Break condition is used in order to break the loop in the first occurrence
     */
	public void remove(Item item ) 
	{
		Iterator<Item> it=this.iterator(); 
		while(it.hasNext())
		{
			if(it.next().equals(item))
			{
				it.remove(); 
				break;    
			}
		}
	}
	
	 /**
     * Print the whole Stack
     */
	void prinT() 
	{
		if (isEmpty()) 
		{
			System.out.println("*  *  *  *  * * *");
			System.out.println("* Empty Stack\t*");
			System.out.println("*  *  *  *  * * *");
		} 
		else 
		{
			System.out.println("*  *  *  *  * * *");
			for (Item item : this) 
			{
				System.out.println("*\t" + item + "\t*");
			}
			System.out.println("*  *  *  *  * * *");
		}

	}

	 /**
     * Returns an iterator to the Stack that iterates through the items
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	public Iterator<Item> iterator()
	{

		return new ListIterator();
	}
	
    /**
     * ListIterator class is implemented using Linked list.
     * There are three nodes that keep track of the current node 
     * and two previous nodes (different from classical implementation) .
     * Current node is firstly initialized to the first node , then it
     * will iterate to the whole list .
     * next() method operates like this : 
     * a) node before previous node(pre_previous) will pass to previous node
     * b) previous node will pass to current node
     * c) current node will pass to current.next node
     * 
     * A boolean variable will decide whether next() is called before remove ()
	 * Remove method will be called from remove(Item item ) defined in AlbaStack class
     */
	public class ListIterator implements Iterator<Item>  
	{
		private Node current=first;
    	private  Node previous=null;
    	private Node pre_previous=null;
    	private boolean delete=false;  //We can only remove if we call next() first. 
		
    	/**
		 * Returns true if the iteration has more elements
    	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
    	 */	
		@Override
		public boolean hasNext()
		{
			return current != null;
		}
	
		/**
		 * Returns the next element in the iteration.
    	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
    	 */	
		@Override
		public Item next() 
		{			
			if (!hasNext())
			{
	    		throw new NoSuchElementException();
			}
	    		Item item = current.item;
	    		pre_previous = previous;
	    		previous = current;
	    		current = current.next;
	    		delete = true;
	    		return item;
		}

		/**
		 * In remove() method , if remove() will be called before next() , an
		 * exception will be thrown.
		 * throw IllegalStateException();
		 * If Item exists then the current node ,that contains it will be removed by connecting 
		 * the previous node to the next node , thus the connections between previous-current
		 * and current-next will be lost.
		 */
		@Override
		public void remove() 
		{
			if (!delete)   // checks whether next() method is called 
		{
				throw new IllegalStateException();
		}
	    		if (previous==first)  
	    		{
	    			first = current;	
	    		}
	    		else
	    		{
	    			pre_previous.next = current;
	    		}
	    		previous = pre_previous;
	    		delete = false;
	    		N--;
			
		}

	}

}
