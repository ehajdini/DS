/*
 * Egla Hajdini
 * 
 * Homework assignment 2
 * Data Structures , Spring 2016
 * 
 **********************/
/**
 * malformedPostfixException is a class that extends the built-in Exception class
 * and will be thrown when a malformed expression is found
 */
public class malformedPostfixException extends Exception {
	malformedPostfixException (String s)
	{
	super(s);
	}

	public String toString()
	{
	return(" " + getMessage());
	}
}
