import java.io.File;
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
 		String fileName = "output.txt";
 		File file = new File(fileName);
 		
 		Writer writer = null;
		try {
			writer = new FileWriter(fileName, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		PrintWriter output = new PrintWriter(writer);
 		
 		output.print("Let us see");
 		
 		output.close();
 		
 		
 	}
 
 }