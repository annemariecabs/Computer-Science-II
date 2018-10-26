import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * 
 * @author AnneMarie Caballero
 *
 */
public class IndexMaker {

	public static void main(String[] args) {
		
	}	
	
	public static void readFile(String inputName, String outputName) {
		
		Writer writer = null;
		
		try {
			writer = new FileWriter(outputName, false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PrintWriter output = new PrintWriter(writer);
		StringBuffer str = null;

	}
	
}
