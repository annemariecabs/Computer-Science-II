import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * This class will read Java files and then determine
 * whether braces are balanced, files are identical, and 
 * will allow the user to write a story.
 *
 * @author annemariecaballero
 *
 */

public class ReadFile {

	private static Scanner keyboard;

	public static void main (String[] args) {
		String[] fileNames = new String[4];
		
		for(int j = 0; j < args.length; j++) 
			fileNames[j] = args[j];
		
		for(int i = 4; i > args.length; i--) 
			fileNames[i - 1] = "";
					
		readAndCheck(fileNames);

	}
	
	public static void readAndCheck(String[] files) {
		
		String txtFileName = "output.txt";

		Writer writer = null;
		
		try {
			writer = new FileWriter(txtFileName, false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PrintWriter output = new PrintWriter(writer);
		StringBuffer str = null;


		try {
			str = loadFile(files[0]);
			
			if(bracesBalanced(str))
				output.println("Braces Balanced");
			else
				output.println("Braces Not Balanced");

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			output.println("Part 1: Unable to Open File");
			output.close();
			System.exit(1); //closes program b/c program is unsuccessful
		}

		output.println("");

		StringBuffer str2 = null;

		try {
			str2 = loadFile(files[1]);

			//BUT WITH CLASS NAME IT WON't BE SAME
			if(str.equals(str2)) 
				output.println("Files Identical");
			else
				output.println("Files Not Identical");

		} catch (FileNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			output.println("Part 2: Unable to Open File");
			output.close();
			System.exit(1); //closes program b/c program is unsuccessful
		}

		output.println("");
		
		try {
			StringBuffer str3 = loadFile(files[2]);
			StringBuffer str4 = new StringBuffer("");
			
			if(files[3].equals(""))
				str4 = loadFile(files[3]);
			
			str3 = fillInStory(str3, str4);
			
			output.println(str3);


		} catch (FileNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			output.println("Part 3: Unable to Open File");
			output.close();
			System.exit(1); //closes program b/c program is unsuccessful
		}

		output.close();
		
		
	}

	//Received a lot of help from the Java Methods book for this method
	public static StringBuffer loadFile(String pathname) throws FileNotFoundException {

		File file = new File(pathname);
		StringBuffer strBuffer = new StringBuffer((int) file.length());
		BufferedReader reader = new BufferedReader(new FileReader(file));

		int ch = 0;
		try {
			while((ch = reader.read())!= -1)
				strBuffer.append((char) ch);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strBuffer;

	}

	public static boolean bracesBalanced(StringBuffer strB) {
		String str = new String(strB);
		char[] chars = str.toCharArray();

		int openB = 0;
		int closedB = 0;

		for(char c: chars) {
			if(c == '{') 
				openB++;
			else if(c == '}')
				closedB++;
		}

		if(openB == closedB)
			return true;
		else
			return false;

	}
	
	public static StringBuffer fillInStory(StringBuffer strBuffer, StringBuffer strBuffer2) {
		ArrayList<String> fillIns = new ArrayList<String>();
		ArrayList<Integer[]> bracketIndex = new ArrayList<Integer[]>();
		
		keyboard = new Scanner(System.in);
		String str = new String(strBuffer);
		String tempStr = "";
		char[] chars = str.toCharArray();
		
		if(strBuffer2.equals("")) {
			for(int i = 0; i < str.length(); i++) {
				if(chars[i] == '<') {
					for(int j = i + 1; j < str.length(); j++) {
						if(chars[j] =='>') {
							System.out.println("Please enter a " + str.substring(i + 1, j));
							tempStr = keyboard.nextLine();
							
							Integer[] tempInts = new Integer[2];
							tempInts[0] = i;
							tempInts[1] = j;
							
							fillIns.add(tempStr);
							bracketIndex.add(tempInts);
							i = j + 1;
							break;
							
						}
							
					}
					
				}	
				
			}
		}
		
		//Add reading fourth file and putting it in fillIns HOW DO I ASSUME FORMATTING
		else {
			
			
		}
		
		String newStr = "";
		Integer[] tempInts = new Integer[2];
		
		for(int h = 0; h < fillIns.size(); h++) {
			tempInts = bracketIndex.get(h);
			char[] tempChars = fillIns.get(h).toCharArray();
			
			for(int l = tempInts[0], k = 0; l < tempInts[1] || k < tempChars.length; l++, k++) {
				
				if(h == 0 && k==0)
					newStr = str.substring(0, l) + tempChars[k];
				else if(k == 0)
					newStr += str.substring(bracketIndex.get(h - 1)[1] + 1, l) + tempChars[k];
				else if(k < fillIns.get(h).length()) 
					newStr += tempChars[k];
					
			}	
		}
		
		newStr += str.substring(bracketIndex.get(bracketIndex.size() - 1)[1] + 1);
		
		strBuffer = new StringBuffer(newStr);
		
		return strBuffer;
	}


}