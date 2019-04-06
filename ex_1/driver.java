/*
 * Egla Hajdini
 * 
 * Homework assignment 1
 * Data Structures , Spring 2016
 * 
 **************/

import java.util.Iterator;
import java.util.Random;

public class driver 
{

	public static void main(String[] args) 
	{
		KrujaQueue kruja = new KrujaQueue(); //Creates an empty KrujaQueue 
		AlbaStack<Integer> stack = new AlbaStack<Integer>(); // Creates an empty AlbaStack of Integers
		Random rand = new Random();				 //create a random number generator object

		for (int i = 0; i < 15; i++)              // for loop used to create 15 AlbaStack 
		{
			stack = new AlbaStack<Integer>();     //creates an empty AlbaStack 15 times
			int r = 1 + rand.nextInt(200);        // Generates Random number from [1,200]
			for (int j = 0; j < r; j++)    		  // for loop used to generate r random numbers 
			{
				int k = 500 + rand.nextInt(4500); // Generates Random number from [500,4500]
				stack.push(k); 					  //pushing Integers to AlbaStack
			}
			int num = 1000 + rand.nextInt(1000); // Generates Random number from [1000,2000]
			stack.remove(num); 					 // filtration
			kruja.add(stack);  					 // adding each stack to KrujaQueue
		}
		kruja.prinT(); 							 //printing the whole KrujaQueue


	} 

}
