/** 
 * This is a class that will read two numbers and print their sum.
 * 
 * @author annemariecaballero
 *
 */

import java.util.Scanner;

public class HelloWorld {
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Please enter an integer");
		int num1 = keyboard.nextInt();
		System.out.println("Please enter another integer");
		int num2 = keyboard.nextInt();
		
		System.out.println("Please choose an operation: +, -, *, /, or %"
				+ "\nDo not add any additional spaces.");
		String op = keyboard.next();
		
		System.out.println(returnOperation(num1, num2, op));
	
	}
	
	public static String returnOperation(int one, int two, String operator) {
		double result = 0;
		
		switch(operator) {
			
		case "+": result = one + two;
		case "-": result = one - two;
		case "*": result = one * two;
		case "/": result = one / two;
		case "%": result = one % two;
		
		}
		
		return "\n2 + 2 = 5 \nkidding, not 1984" + "\n" + one + " " + operator + " " + two + " = " + result;
		
	}
	
}

// This is my signature for the lab project ~Matthew Grillo