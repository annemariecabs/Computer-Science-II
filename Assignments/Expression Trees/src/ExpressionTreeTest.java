import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A test file created for ExpressionTree. The file takes in an input file, 
 * either the one with the default input file name, or, if that is not 
 * available the one provided in the command-line arguments. Otherwise, the 
 * program will request an input file name from the user. 
 * 
 * The test file will then process the file, which should have an expression
 * on each line. For each expression, the program will build an ExpressionTree
 * and print the result of the expression using the tree, the expression
 * in prefix, infix and postfix, and the evaluation of the expression using 
 * a stack.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */

public class ExpressionTreeTest {
	
	/**
	 * The default name for the input file
	 */
	private static final String DEFAULT_FILE_NAME = "postFixExpressions.txt";
	
	/**
	 * The name of the designated output file 
	 */
	private static final String OUTPUT_FILE_NAME = "annemarieCaballero_Expressions.txt";
	
	/**
	 * The message shown if the program encounters an IOException when reading the file with the expressions
	 */
	private static final String IOEXCEPTION_MESSAGE = "The system has encountered an IOException when reading the test file";

	/**
	 * The message shown if the file holding the postfix expressions is not found
	 */
	private static final String INVALID_FILE_MESSAGE = "Please enter a valid file containing the postfix expressions "
			+ "(type exit to exit the program)";
	
	/**
	 * The error message used if a PrintWriter throws a FileNotFoundException
	 */
	private static final String PRINTWRITER_ERROR_MESSAGE = "Error: " + OUTPUT_FILE_NAME + " does not exist yet and cannot be created.";
	
	/**
	 * The word exit
	 */
	private static final String EXIT = "exit";
	
	/**
	 * A space
	 */
	private static final String SPACE = " ";
	
	/**
	 * An empty String
	 */
	private static final String EMPTY = "";
	
	/**
	 * A newline
	 */
	private static final String NEW_LINE = "\n";
	
	/**
	 * The code passed as a parameter to System.exit() when something has gone wrong
	 */
	private static final int ERROR_CODE = 1;
	
	/**
	 * The Scanner used to read user input on the keyboard
	 */
	private static Scanner keyboard;
	
	/**
	 * The PrintWriter to print to the output file with name OUTPUT_FILE_NAME
	 */
	private static PrintWriter output;
	
	public static void main(String[] args) {		
		File file;
		keyboard = new Scanner(System.in);
		
		if(args.length <= 0)
			file = new File(DEFAULT_FILE_NAME);
		else 
			file = new File(args[0]);
		
		String input;
		
		while(! file.exists()) {
			System.out.println(INVALID_FILE_MESSAGE);
			
			input = keyboard.nextLine();
			
			if(input == null)
				continue;
			else if (input.equals(EXIT))
				System.exit(ERROR_CODE);
			else
				file = new File(input);
		}
		
		/*
		 * expressions is declared as null, but it will never be used as such because
		 * if the readFile method used to initialize it throws an error, the program will
		 * close
		 */
		String[] expressions = null;
		
		try {
			expressions = readFileWithPostfixExpressions(file);
		} catch (IOException e1) {
			System.out.println(IOEXCEPTION_MESSAGE);
			System.exit(ERROR_CODE);
		}
		
		String[] expPts;
		ExpressionTree root;

		try {

			output = new PrintWriter(OUTPUT_FILE_NAME);
		} catch (FileNotFoundException e) {
			System.out.println(PRINTWRITER_ERROR_MESSAGE);
			System.exit(ERROR_CODE);
		}
		
		for(String exp: expressions) {
			exp = exp.trim();
			
			if(exp.equals(EMPTY)) {
				continue;
			}
		
			expPts = exp.split(SPACE);
			root = new ExpressionTree(expPts[0]);
			root = new ExpressionTree(root.buildTree(expPts));
			printTest(root, expPts);
		}
		
		output.close();
		
	}
	
	/**
	 * Reads a file with postfixExpressions in it and returns the expressions as a String array
	 * with each index holding a singular expression.
	 * 
	 * @param file the name of the file to read
	 * @return a String[] with the expressions stored in it; an empty String[] if the file has no lines
	 * @throws IOException if an error has occurred in reading the file
	 */
	public static String[] readFileWithPostfixExpressions(File file) throws IOException {
		ArrayList<String> expressions = new ArrayList<String>();
		
		expressions = (ArrayList<String>) Files.readAllLines(file.toPath());
		
		String[] result = new String[expressions.size()];
		
		return expressions.toArray(result);

	}
	
	/**
	 * Prints out the various tests this program is required to run. The scanner output
	 * needs to be a field that has already been initialized and closed after this method.
	 * 
	 * @param tree an ExpressionTree to perform various operations on 
	 * @param expressions the list of expressions the ExpressionTree holds
	 */
	public static void printTest(ExpressionTree tree, String[] expressions) {
		output.println(tree.evalTree());
		output.println(tree.toPrefixNotation());
		output.println(tree.toInfixNotation());
		output.println(tree.toPostfixNotation());
		output.println(tree.postfixEval(expressions) + NEW_LINE + NEW_LINE);
		
	}
	
}