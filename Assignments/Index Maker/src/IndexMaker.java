import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


/**
 * This class reads an input file and writes to an output file an index of that input file
 * using the IndexEntry and DocumentIndex classes. If the class is provided both the input and
 * output file as arguments, it will utilize them as given. However, if the class is provided
 * only the input file as an argument, it will generate an output file with a name that takes
 * the input file's name and adds Index before the extension e.g. input file "hello.txt" would
 * generate an output file named "helloIndex.txt" if an output file name was not provided.
 * If neither file is provided through run arguments, the program asks the user to input
 * them through the console. The main method mainly takes in the files and checks that they exist
 * before utilizing the two other methods of the class: createIndex and writeIndexToFile.
 * 
 * The method createIndex takes in an input file name, checks if it exists and, if not, asks
 * the user to either exit or give an existing file, and then generates and returns a 
 * DocumentIndex that serves as the index referenced in the main method. The method 
 * writeIndexToFile takes in an output file name and a DocumentIndex and writes the index to
 * that output file. If the output file does not exist, the method creates one.
 * 
 * 
 * @author AnneMarie Caballero
 * @see DocumentIndex IndexEntry
 *
 */
public class IndexMaker {

	/**
	 * The main method takes in the run arguments, and accounts for if some are missing.
	 * If there are two run arguments, the first is the input file and the second is the 
	 * output file. If there is one run argument, the input file name will be used to 
	 * generate the output file name. If there are no arguments, the user will be asked
	 * to provide both the input and output file name. The main method then calls the 
	 * createIndex and writeIndexToFile methods.
	 * 
	 * @param args The run arguments provided.
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String input;
		String output;
		
		if(args.length == 0) {
			do {
				System.out.println("Please enter a file name for the program's input.");
				input = keyboard.next();
			} while (input == "");
			do {
				System.out.println("Please enter a file name for the program's output.");
				output = keyboard.next();
			} while (output == "");
			
			keyboard.close();
		}
		
		else if(args.length == 1) {
			input = args[0];
			int periodIndex = input.lastIndexOf('.');
			output = input.substring(0, periodIndex) + "Index" + input.substring(periodIndex);
		}
		else {
			input = args[0];
			output = args[1];
		}
			
		DocumentIndex index = createIndex(input);
		try {
			writeIndexToFile(output, index);
		} catch (IOException e) {
			System.exit(1);
		}
	}
	/**
	 * 
	 * The createIndex method checks if the file given by inputName exists, and, if it does,
	 * uses that file to generate a DocumentIndex that it then returns. If the file does not 
	 * exist, the program will ask the user to provide a file that does work until either
	 * the file provided exists or the user exits the program. 
	 * 
	 * @param inputName the name of the input file used to generate the DocumentIndex
	 * @return DocumentIndex that is created using the file with name inputName 
	 */
	public static DocumentIndex createIndex(String inputName) {
		
		DocumentIndex index = new DocumentIndex();
		ArrayList<String> lines = new ArrayList<String>();
		
		//checks if input file exists, if it doesn't asks for it to be input again
		File file = new File(inputName);
		String temp = "";
		Scanner keyboard = new Scanner(System.in);
		
		while (! file.exists()){
			System.out.println("Please enter the name of an existing input file"
					+ " (type exit to exit the program)");
			
			temp = keyboard.nextLine();
			
			if(temp.equals("exit")) 
				System.exit(1);
			else 
				file = new File(temp);
		}
		
		keyboard.close();
		
		Path path = file.toPath();
		
		try {
			lines = (ArrayList<String>) Files.readAllLines(path);
			
			for(int i = 0; i < lines.size(); i++) {
				index.addAllWords(lines.get(i), i + 1);
			}
		} catch (IOException e) {
			System.exit(1);
			System.out.println("Unfortunately due to unforseeable errors, the program was unable to generate an index.");
		}
		
		return index;
	}

	/**
	 * The writeIndexToFile method will write the index provided to the file with name
	 * outputName or will generate that file with name outputName and then write the index
	 * to it. 
	 * 
	 * @param outputName the String value representing the name of the output file to be 
	 * 					written to or created then written to
	 * @param index the DocumentIndex that holds the index to be written to the output file
	 * @throws IOException if an error occurs with the PrintWriter
	 */
	public static void writeIndexToFile(String outputName, DocumentIndex index) throws IOException {

		PrintWriter output = new PrintWriter(outputName);
		
		Set<String> keys = index.getKeys();
		int trace = 0;
		
		for(String key: keys) {
			//the if statement makes sure there's no extra line in the output file
			if(trace != keys.size() - 1) {
				output.println(index.getEntry(key).toString());
				trace++;
			}
			else
				output.print(index.getEntry(key).toString());
		}
		
		output.close();
	}	
}
