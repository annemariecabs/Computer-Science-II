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
	 * A typed phrase used if the user wants to exit the program rather than
	 * inputting information.
	 */
	private static final String EXIT_PHRASE = "exit";
	
	/**
	 * The number used as a parameter for System.exit() if the program has
	 * to quit early due to difficulties during runtime.
	 */
	private static final int ERROR_CODE = 1;
	
	/**
	 * The message used if the file with the names of test files is not found.
	 */
	private static final String TESTS_FILE_NOT_FOUND_MESSAGE = "Please provide an existing file with the names of files to test in it."
			+ " (type exit if you wish to leave this program).";
	
	/**
	 * The error message used if a PrintWriter throws a FileNotFoundException
	 */
	private static final String PRINTWRITER_ERROR_MESSAGE = "Error: knapsack.txt does not exist yet and cannot be created.";
	
	/**
	 * The message shown if the program encounters an IOException when reading String arrays from files
	 */
	private static final String IOEXCEPTION_MESSAGE = "The system has encountered an IOException when reading the test files"
			+ "Please enter the names of the files you would like to test separated by spaces"
			+ "(type exit to exit the program)";
	
	/**
	 * The message shown when a test file does not exist.
	 */
	private static final String FILE_DOES_NOT_EXIST_MESSAGE = "\tThis file does not exist.\n\n";
	
	
	/**
	 * The message shown when the file does not contain integers in the proper format.
	 */
	private static final String NO_INTEGERS_MESSAGE = "\tThis file does not contain integers.\n\n";
	
	/**
	 * The message shown when there is no possible combination that is under the limit.
	 */
	private static final String NO_POSSIBILITIES_MESSAGE = "No possible watermelons";
	
	/**
	 * Printed after every watermelon weight that gets added to the knapsack
	 * in knapsack.txt.
	 */ 
	private static final String UNIT = " pound watermelon";
	
	/**
	 * A space.
	 */
	private static final String SPACE = " ";
	
	/**
	 * A tab.
	 */
	private static final String TAB = "\t";
	
	/**
	 * A new-line character.
	 */
	private static final String NEW_LINE = "\n";
	
	/**
	 * A comma.
	 */
	private static final String COMMA = ",";
	
	/**
	 * Empty string.
	 */
	private static final String EMPTY_STRING = "";
	
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
		
		if(args.length == 0 || ! (new File(args[0]).exists())) {
			do {
				System.out.println(TESTS_FILE_NOT_FOUND_MESSAGE);
				fileWithTests = keyboard.nextLine();
				
				if(fileWithTests.equals(EXIT_PHRASE)) {
					System.exit(ERROR_CODE);
				}
					
				testFile = new File(fileWithTests);
			} while (! testFile.exists() || fileWithTests == null);
		}
		else
			fileWithTests = args[0];
		
		ArrayList<String> tests = new ArrayList<String>();
		
		try {
			tests = readFileWithStringArray(fileWithTests);
		} catch (IOException e2) {
			String userInput;
			System.out.println(IOEXCEPTION_MESSAGE);
			
			userInput = keyboard.nextLine();
			
			if(userInput.equals(EXIT_PHRASE))
				System.exit(ERROR_CODE);
			else {
				
				for(String temp: userInput.split(SPACE))
					tests.add(temp);
			}	
		}
		
		Integer[] nums;
		int limit;
		int[] weights;
		
		
		try {
			output = new PrintWriter(OUTPUT_NAME);
		} catch (FileNotFoundException e1) {
			System.out.println(PRINTWRITER_ERROR_MESSAGE);
			System.exit(ERROR_CODE);
		}
		
		for(String test: tests) {
			
			try {
				nums = readItemsFromFile(test);
			} catch (FileNotFoundException e) {
				output.println(test + FILE_DOES_NOT_EXIST_MESSAGE);
				continue;
			}
			
			if(nums.length == 0) {
				output.println(test + NO_INTEGERS_MESSAGE);
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
		int added, notAdded, sum;
		ArrayList<Integer> addedList = new ArrayList<Integer>(), notAddedList = new ArrayList<Integer>();
		
		if(n <= 0 )
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
		else {
			sum =  knapsackSum(w, n - 1, limit, notAddedList);
			
			for(Integer i: notAddedList) {
				list.add(i);
			}
			
			return sum;
		}	
	}
	
	/**
	 * An internal method that reads a file and returns its lines as a 
	 * String array. Used to read the names of the test file.
	 * 
	 * @param inputName the file to read the String array from
	 * @return an empty String array if the file does not exist or has no text, or
	 * 		a String array with the lines of the corresponding file to inputNam
	 * @throws IOException if an error occurs during the Files.readAllLines(path) method
	 */
	private static ArrayList<String> readFileWithStringArray (String inputName) throws IOException {
		
		ArrayList<String> lines = new ArrayList<String>();
		File file = new File(inputName);
		
		if(! file.exists())
			return lines;
				
		Path path = file.toPath();
		
		lines = (ArrayList<String>) Files.readAllLines(path);
		
		//below method ensures that empty lines are not left in
		for(int i = 0; i < lines.size(); i++)
			if(EMPTY_STRING.equals(lines.get(i)))
				lines.remove(i);
		
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
		
		String wFormatted = EMPTY_STRING;
		
		if(w.length != 0 ) {
			for(int i: w) {
				wFormatted += i + COMMA + SPACE;
			}
			
			wFormatted = wFormatted.substring(0, wFormatted.length() - 2);
		}
		
		output.println(fileName + TAB + limit + TAB
				+ wFormatted + NEW_LINE);
		
		watermelons = new ArrayList<Integer>();
		
		knapsackSum(w, w.length, limit, watermelons);
		
		if(watermelons.isEmpty())
			output.println(NO_POSSIBILITIES_MESSAGE);
		else
			for(Integer watermelonWeight: watermelons) {
				output.println(watermelonWeight + UNIT);
			}
			
		output.print(NEW_LINE + NEW_LINE);
		
	}
}
