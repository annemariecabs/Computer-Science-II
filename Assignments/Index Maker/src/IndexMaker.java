import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


/**
 * This class reads input files sent as arguments
 * 
 * 
 * @author AnneMarie Caballero
 *
 */
public class IndexMaker {


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
			e.printStackTrace(); //TODO: take this out
		}
		
		return index;
	}

	
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
