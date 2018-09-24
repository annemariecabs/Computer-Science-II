import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/** 
 * This class will read Java files and then determine
 * whether braces are balanced, files are identical, and 
 * will allow the user to write a story.
 *
 * @author annemariecaballero
 *
 */
 
 public class ReadFile {
 
 	public static void main (String[] args) {
 		String txtFileName = "output.txt";
 		File file = new File(txtFileName);
 		
 		String sampleFile = "Sample.java";
 		
 		Writer writer = null;
		try {
			writer = new FileWriter(txtFileName, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		PrintWriter output = new PrintWriter(writer);
 		
 		
 		FileReader fileReader;
		try {
			fileReader = new FileReader(sampleFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output.print("Part 1: Unable to Open File");
		}
 		
 		output.close();
 		
 		
 	}
 
 }