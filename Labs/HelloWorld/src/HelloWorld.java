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
		
		System.out.println("Please enter an integer to add");
		int num1 = keyboard.nextInt();
		System.out.println("Please enter another integer to add to the first");
		int num2 = keyboard.nextInt();
		
		System.out.println("\n2 + 2 = 5 \nkidding, not 1984"
				+ "\n" + num1 + " + " + num2 + " = " + (num1 + num2));

	}
	
}

// This is my signature for the lab project ~Matthew Grillo