/*
 * Egla Hajdini
 * 
 * Homework assignment 2
 * Data Structures , Spring 2016
 * 
 **********************/

/**
 * undifinedVariableException is a class that extends the built-in Exception class
 * and will be thrown when an undefined Variable is found
 */
public class undefinedVariableException extends Exception {

	undefinedVariableException (String s)
	{
	super(s);
	}
	public String toString()
	{
	return(" " + getMessage());
	}
	
}
