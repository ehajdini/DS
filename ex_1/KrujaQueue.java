/*
 * Egla Hajdini
 * 
 * Homework assignment 1
 * Data Structures , Spring 2016
 * 
 *********************************/

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
 * The KrujaQueue class represents an unusual Queue of AlbaStack items.
 * It is a sorted Queue , which adds an item and places it where it fits.
 * 
 * It supports the main methods of an usual Queue , 
 * Dequeuing an item , checking whether the Queue is empty or not , 
 * and returning Queue's size .
 * In addition , it has two other methods : 
 * One is used to print the whole stack , another one is used to add Items
 * in decreasing order (according to each AlbaStack Item size())
 * 
 * This implementation uses a singly-linked list with a nested class of
 * linked-list nodes .
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class KrujaQueue implements Iterable<AlbaStack> 
{

	private Node first; //beginning of a queue
	private Node last;  //end of a queue
	private int N;   //number of items 

	 /**
     * Linked List Class
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	private class Node 
	{ 
		AlbaStack item;
		Node next;
	}
	
	/**
     * Create an empty queue.
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	KrujaQueue() 
	{
		first = null;
		last = null;
		N = 0;
	}

	 /**
     * Check whether the queue is empty
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	boolean isEmpty() 
	{
		return first == null;
	}

	/**
     * Return the number of items in the queue.
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	int size() 
	{
		return N;
	}

	/**
     * Add the item to the queue in the position it fits.
     * Firstly , an empty node is created.
     * Then we have two main cases : 1) The Queue is Empty 2)It isn't Empty
     * -If it is empty the element will be added in the first node.
     * -If it isn't Empty there are three cases :
     * a) If the size of new item is greater than the first , then the new item 
     *    will be the first item
     * b) If the size of the new item is smaller than the last one , then the new 
     * 	  item will be the last one
     * c) If the size of the new item is within the interval [first.size(),last.size()],
     *    then we have to check for the place where the new item fits.
     * At the end , increment the number of items in the Queue 
     */
	void add(AlbaStack item) 
	{
		Node n = new Node();
		n.item = item;
		N++;
		if (isEmpty()) 
		{
			first = n;
			last = first;
			last.next = null;
		}

		else 
		{
			if (item.size() >= first.item.size()) 
			{
				n.next = first;
				first = n;
				return;
			}

			if (item.size() <= last.item.size()) 
			{
				last.next = n;
				last = n;
				last.next=null;
				return;
			}

			if (item.size() < first.item.size() && item.size() > last.item.size()) 
			{
				Node tfirst = first;
				while (tfirst.next != null) 
				{
					if (tfirst.item.size() >= item.size() && tfirst.next.item.size() <= item.size()) 
					{
						n.next = tfirst.next;
						tfirst.next = n;
						break;
					}
					tfirst = tfirst.next;
				}
			}

		}
	}

	/**
     * Removes and returns the item on this queue that was least recently added.
     * @throws NoSuchElementException if this queue is empty
     * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
	AlbaStack dequeue() {
		AlbaStack item = first.item;
		first = first.next;
		if (isEmpty())
		{
			last = null;
		}
		N--;
		return item;
	}

	/**
	 * Prints the whole Queue
	 */
	void prinT() 
	{
		int i = 1;
		if (isEmpty())
		{
			System.out.println("Empty KrujaQueue");
		}
		else 
		{
			System.out.println("----------------------------");
			System.out.println("KrujaQueue Items: "+this.size()+" items");
			System.out.println("---------------------------- \n");

			for (AlbaStack item : this) 
			{
				System.out.println("----------------------------");
				System.out.print("AlbaStack " + i + ": " + item.size() + " items \n");
				System.out.print("---------------------------- \n");
				i++;
				item.prinT();
			}
			// System.out.println(str);

		}
	}

	/**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	 */
	public Iterator iterator() 
	{
		return new ListIterator();
	}

	public class ListIterator implements Iterator<AlbaStack> 
	{
		private Node current = first;

		/**
		 * Returns true if the iteration has more elements
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
		 * The next method will return an item of AlbaStack type
    	 * © Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
    	 */	
		@Override
		public AlbaStack next() 
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			AlbaStack item = current.item;
			current = current.next;
			return item;
		}

	}

}