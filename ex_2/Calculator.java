/*
 * Egla Hajdini
 * 
 * Homework assignment 2
 * Data Structures , Spring 2016
 * 
 **********************/

/**
 * Calculator is a class which is used to Calculate values of Postfix expressions
 * It has an Empty Constructor and two methods:  assign and evaluate
 */

public class Calculator {
	VList list;

	/**
	 * Empty constructor 
	 */
	public Calculator() {
		super();
		list = new VList();
	}

	/**
	 * This method is used to assign a Variable value 
	 * It uses a CStack
	 * It takes a String assigning a one-character variable to a value, 
	 * expressed as a valid postfix expression
	 * In a regular expression the first character will be the variable name ,
	 * and the second character will be '=' operator
	 * If the first character doesn't exist in VList , it will be added into the list 
	 * Otherwise it will be updated 
	 * It will start checking each character of the String starting from the
	 * second position
	 * 1) If the character is a Digit (0-9) it will be pushed into the string
	 * 2) If it is a letter and it exists in the list its value will be returned and 
	 * pushed to the stack ,
	 * if it doesn't exist 
	 * @undefinedVariableException will be thrown
	 * 3) If the character is an Arithmetic Operator ('+' , '-' , '/' , '*') , 
	 * two Variables will be popped from the Stack and after performing the operation the 
	 * new value will be pushed into the stack , 
	 * if it is a different character from one of Arithmetic Operators or letters 
	 * it will try to pop a value , but since it won't find one
	 *  @malformedPostfixException will be thrown 
	 * At the end if the CStack is not empty , check method will be called and
	 *  @malformedPostfixException will be thrown , because there might be more
	 *  operators than operands or vice versa.
	 */
	public void assign(String str)  {
		
		CStack s = new CStack();
		try{
		for (int i = 2; i < str.length(); i++) {
			Character ch = str.charAt(i);
			if (Character.isDigit(ch)){

				s.push(Character.getNumericValue(str.charAt(i)));	
			}
			if (!Character.isLetter(ch) && !Character.isDigit(ch)) {
				int a = s.pop();
				int b = s.pop();
				if (ch == '+')
					s.push(a + b);
				if (ch == '-')
					s.push(b - a);
				if (ch == '*')
					s.push(a * b);
				if (ch == '/')
					s.push(b / a);

			}
			if(Character.isLetter(ch)){
				s.push(list.getVar(ch));
			}
		}
		if(!list.exists(str.charAt(0))){   
		Variable v = new Variable();
		v.name = str.charAt(0);
		v.value = s.pop();
		list.add(v);
		s.check();
		}
		else list.replace(str.charAt(0),s.pop());  }
		catch (malformedPostfixException e) {
			System.out.println(e.getMessage());	
		}
		catch(undefinedVariableException e){
			System.out.println(e.getMessage());
		}
		catch (ArithmeticException e){
			System.out.println(e.getMessage()+" not allowed");
		}
	}

 /**
  * This method is used to evaluate a postfix expression by using a CStack
  * It works in the same way like assign method
  * It will start by checking the String from the first character
  * 1) If it is a digit it will be added to the CStack
  * 2) If it is a character and it exists in the VList its value with be returned and 
  * pushed to the CStack ,otherwise it will be thrown
  * @undefinedVariableException
  * 3) If the character is an Arithmetic Operator ('+' , '-' , '/' , '*') , 
  * two Variables will be popped from the Stack and after performing the operation the 
  * new value will be pushed into the stack
  * Then after all characters are checked the result will be popped from the CStack
  * and will be returned
  * However , since evaluate has int signature , it has to return a value even though 
  * there might be a Malformed Expression .
  * Therefore if it one of exceptions will occur it will return 0;
  */
	public int evaluate(String str) {
		int result=0;
		CStack stack=new CStack();
		try{
		for (int i = 0; i < str.length(); i++) {
			Character ch = str.charAt(i);
				if (Character.isDigit(ch)){
				stack.push(Character.getNumericValue(str.charAt(i)));	
			}
			if (!Character.isLetter(ch) && !Character.isDigit(ch)) {
				int a = stack.pop();
				int b = stack.pop();
				if (ch == '+')
					stack.push(a + b);
				if (ch == '-')
					stack.push(b - a);
				if (ch == '*')
					stack.push(a * b);
				if (ch == '/')
					stack.push(b / a);
			}
			if(Character.isLetter(ch)){
			stack.push(list.getVar(ch));
			}
		}
		result=stack.pop();
		stack.check();
		}
		catch (malformedPostfixException e) {
		
			System.out.println(e.getMessage());	
		}
		catch(undefinedVariableException e){
			
			System.out.println(e.getMessage());
		}
		catch (ArithmeticException e){
			System.out.println(e.getMessage()+" not allowed !");
		}
		return result;
	}
}
