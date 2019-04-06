/*
 * 
 * Homework assignment 2
 * Data Structures , Spring 2016
 * 
 **********************/
/***************************************************************************************
 * Variable Class .
 * It contains an empty constructor , two class variables and two getters;
 * name , will hold the variable name
 * value , will hold the value of the variable 
 * getName() will return the variable name
 * getValue() will return the value of the variable 
***************************************************************************************/
public class Variable {
	
 char name;
 int value;
 
public Variable() {
	super();
	name = ' ';
	value = 0;
}
public char getName() {
	return name;
}
public int getValue() {
	return value;
}

}
