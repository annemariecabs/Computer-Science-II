import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program is a coded version of the Knapsack problem, which asks how to find
 * the optimal value for a number of weights in a knapsack without exceeding a 
 * weight limit. In this version of the problem, the value of an object is equal
 * to its weight, so the goal is to obtain the maximum weight possible from the objects
 * without exceeding the limit. The program takes the name of a file with the tests
 * to be run in the command-line arguments then prints out the result of the tests
 * (the items to put in the bag because they have the highest weight value without
 * going over the limit) to the output file knapsack.txt.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */

public class Knapsack {
	
	/**
	 * A field representing the String literal that is the name of the output file.
	 */
	private static final String OUTPUT_NAME = "knapsack.txt";
	
	/**
	 * A PrintWriter field to write to the output file, which will be used in the main and other methods.
	 */
	private static PrintWriter output;
	
	/**
	 * The list where the chosen objects for a problem are stored.
	 */
	private static List<Integer> watermelons;
	
	/**
	 * A Scanner object which will be used to read user input.
	 */
	private static Scanner keyboard;
	
	public static void main (String[] args) {
		String fileWithTests;
		keyboard = new Scanner(System.in);
		File testFile;
		
		if(args.length == 0) {
			do {
				System.out.println("Please provide an existing file with the names of files to test in it."
						+ " (type exit if you wish to leave this program).");
				fileWithTests = keyboard.nextLine();
				
				if(fileWithTests.equals("exit")) {
					System.exit(1);
				}
					
				testFile = new File(fileWithTests);
			} while (! testFile.exists() || fileWithTests == null);
		}
		else
			fileWithTests = args[0];
		
		ArrayList<String> tests = readFileWithStringArray(fileWithTests);
		
		Integer[] nums;
		int limit;
		int[] weights;
		
		
		try {
			output = new PrintWriter(OUTPUT_NAME);
		} catch (FileNotFoundException e1) {
			// TODO Figure out what to do with this error- It should never throw it bc worst case scenario it just creates a knapsack
			e1.printStackTrace();
		}
		
		for(String test: tests) {
			
			try {
				nums = readItemsFromFile(test);
			} catch (FileNotFoundException e) {
				output.print(test + "\tThis file does not exist.\n\n");
				continue;
			}
			
			if(nums.length == 0) {
				output.print(test + "\tThis file does not contain numbers. \n\n");
				continue;
			}
			
			limit = nums[0];
			weights = new int[nums.length - 1];
			for(int i = 0; i < nums.length - 1; i++)
				weights[i] = nums[i + 1];
			
			writeTestToFile(test, limit,  weights);
		}
		
		output.close();
		keyboard.close();
		
	}
	
	/**
	 * A method that finds the optimal sum of weights that can still fit in
	 * the knapsack recursively. Unlike the other knapsackSum method, 
	 * this one does not record the items used to achieve the sum.
	 * 
	 * @param w the list of weights 
	 * @param n the number of weights in w (this is a precondition)
	 * @param limit the limit on weight of the knapsack
	 * @return the maximum amount of weight that can be attained with the 
	 * 		weights provided without overloading the knapsack
	 */
	public static int knapsackSum(int[] w, int n, int limit) {
		int added, notAdded;
		
		if(n <= 0 || limit <= 0)
			return 0;
		else if(w[n - 1] <= limit) {
			added = knapsackSum(w, n - 1, limit - w[n - 1]) + w[n - 1];
			notAdded = knapsackSum(w, n - 1, limit);
			
			if(added > notAdded)
				return added;
			else
				return notAdded;
		}
		else
			return knapsackSum(w, n - 1, limit);
			
	}
	
	/**
	 * A method that finds the solution to the knapsack problem and remembers
	 * the items used to achieve that maximum weight without going over the limit.
	 * 
	 * @param w the list of weights
	 * @param n the number of weights (this is a precondition)
	 * @param limit the limit on weight of the knapsack
	 * @param list the list to add items that are chosen to
	 * @return the maximum amount of weight that can be attained with the 
	 * 		weights provided without overloading the knapsack
	 */
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		int added, notAdded;
		ArrayList<Integer> addedList = new ArrayList<Integer>(), notAddedList = new ArrayList<Integer>();
		
		if(n <= 0 || limit <= 0)
			return 0;
		else if(w[n - 1] <= limit) {
			addedList.add(w[n - 1]);
			
			added = knapsackSum(w, n - 1, limit - w[n - 1], addedList) + w[n - 1];
			notAdded = knapsackSum(w, n - 1, limit, notAddedList);
			
			if(added > notAdded) {
				for(Integer i: addedList) {
					list.add(i);
				}
				return added;
			}
			else {
				for(Integer i: notAddedList) {
					list.add(i);
				}
				return notAdded;	
			}
		}
		else
			return knapsackSum(w, n - 1, limit, addedList);
			
	}
	
	/**
	 * An internal method that reads a file and returns its lines as a 
	 * String array. Used to read the names of the test file.
	 * 
	 * @param inputName the file to read the String array from
	 * @return an empty String array if the file does not exist or has no text, or
	 * 		a String array with the lines of the corresponding file to inputNam
	 */
	private static ArrayList<String> readFileWithStringArray (String inputName) {
		
		ArrayList<String> lines = new ArrayList<String>();
		File file = new File(inputName);
		
		if(! file.exists())
			return lines;
				
		Path path = file.toPath();
		
		try {
			lines = (ArrayList<String>) Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Change this catch block to a System.exit and an explanation
			//or let it keep going and print an acceptable explanation to knapsack.txt
			e.printStackTrace();
		}
		
		return lines;
	}
	
	/**
	 * An internal method that reads the integers within a file. Used to read 
	 * the items from a file containing the limit and weights (the "items") 
	 * to be used for the knapsack problem.
	 * 
	 * @param inputName the name of the file to read the items from
	 * @return an int array with the items in it; if the file didn't exist or 
	 * 	had no valid numbers, it returns an empty array
	 * @throws FileNotFoundException if the file with name inputName is not found
	 */
	private static Integer[] readItemsFromFile (String inputName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(inputName));
		ArrayList<Integer> weights = new ArrayList<Integer>();
		
		while(fileReader.hasNextInt()) 
			weights.add(fileReader.nextInt());
		
		Integer[] nums = new Integer[weights.size()];
		weights.toArray(nums);
		
		return nums;
		
	}
	
	/**
	 * Writes a test class for knapsack to the file "knapsack.txt." It prints
	 * the file name, limit, and weights onto the file then prints the best 
	 * combination of watermelons (the one that will attain the most weight 
	 * without going over the limit by calling the second knapsack method. 
	 * 
	 * @param fileName the name of the test file
	 * @param limit the limit contained within the designated test files
	 * @param w the weights contained within the test files
	 */
	private static void writeTestToFile(String fileName, int limit, int[] w) {
		
		String wFormatted = "";
		
		for(int i: w) {
			wFormatted += i + ", ";
		}
		
		wFormatted = wFormatted.substring(0, wFormatted.length() - 2);
		
		output.println(fileName + "\t" + limit + "\t"
				+ wFormatted + "\n");
		
		watermelons = new ArrayList<Integer>();
		
		knapsackSum(w, w.length, limit, watermelons);
		
		if(watermelons.isEmpty())
			output.println("No possible watermelons");
		else
			for(Integer watermelonWeight: watermelons) {
				output.println(watermelonWeight + " pound watermelon");
			}
			
		output.print("\n\n");
		
	}
}
