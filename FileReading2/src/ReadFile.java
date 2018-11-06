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
 * will allow the user to write a Mad Libs story.
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
	
	/**
	 * Loads the files and performs the three main functionalities:
	 * 		1) Checking if a file has balanced braces
	 * 		2) Checking if two files are identical
	 * 		3) Filling in provided spots in a story
	 * 
	 * @param files a String[] that holds the names of all the files to be used
	 */
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
			e1.printStackTrace();
			output.println("Part 1: Unable to Open File");
			output.close();
			System.exit(1); //closes program because program is unsuccessful as file cannot be found
		}

		output.println("");

		StringBuffer str2 = null;

		try {
			str2 = loadFile(files[1]);
			
			String one = new String(str);
			String two = new String(str2);
			
			if(one.equals(two)) 
				output.println("Files Identical");
			else
				output.println("Files Not Identical");

		} catch (FileNotFoundException e) { 
			e.printStackTrace();
			output.println("Part 2: Unable to Open File");
			output.close();
			System.exit(1);  //closes program because program is unsuccessful as file cannot be found
		}

		output.println("");
		
		try {
			StringBuffer str3 = loadFile(files[2]);
			StringBuffer str4 = new StringBuffer("");
			
			if(! files[3].equals("")) 
				str4 = loadFile(files[3]);

			str3 = fillInStory(str3, str4);
			
			output.println(str3);


		} catch (FileNotFoundException e) { 
			e.printStackTrace();
			output.println("Part 3: Unable to Open File");
			output.close();
			System.exit(1);  //closes program because program is unsuccessful as file cannot be found
		}

		output.close();
		
		
	}

	/**
	 * 
	 * This method reads a file by character using BufferedReader.
	 * Returns the file's contents as a StringBuffer.
	 * 
	 * This method was taken (almost completely the same) from pg.415 of the Java Methods book by Maria and Gary Litvin.
	 * 
	 * @param pathname the name of the file to read
	 * @return a StringBuffer with the contents of the file
	 * @throws FileNotFoundException occurs if the file, with name pathname, cannot be found
	 * 
	 */
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
			e.printStackTrace();
		}

		return strBuffer;

	}

	/**
	 * 
	 * Checks a StringBuffer object to see if all open braces within the StringBuffer
	 * have an accompanying closing curly brace.
	 * 
	 * @param strB the StringBuffer for whose curly brace pairs are to be checked
	 * @return true, if the curly braces, if any, are all in pairs; false, otherwise.
	 */
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
	
	/**
	 * 
	 * Returns a StringBuffer that has the original story with all the phrases surrounded by
	 * <> as well as the <> removed. The returned StringBuffer will either be filled with user
	 * response or with words from an already-provided file.
	 * 
	 * @param strBuffer  the name of the first file, which contains the story, with parts to 
	 * 					 be filled in
	 * @param strBuffer2 a list of words to use to fill in the areas between <> in the story.
	 * 					 if there is no already-provided file where this list came from
	 * 					 this will be equal to "".
	 * @return a StringBuffer that has the original story but with the new words filled in
	 * 		   in place of the <> and phrases between them.
	 */
	public static StringBuffer fillInStory(StringBuffer strBuffer, StringBuffer strBuffer2) {
		ArrayList<String> fillIns = new ArrayList<String>();
		ArrayList<Integer[]> bracketIndex = new ArrayList<Integer[]>();

		keyboard = new Scanner(System.in);
		String str = new String(strBuffer);
		String tempStr = "";
		char[] chars = str.toCharArray();
		
		String str2 = new String(strBuffer2);
		char[] chars2 = str2.toCharArray();
		
		if(str2.equals("")) {
			for(int i = 0; i < str.length(); i++) {
				if(chars[i] == '<') {
					for(int j = i + 1; j < str.length(); j++) {
						if(chars[j] =='>') {
							System.out.println("Please enter a " + str.substring(i + 1, j));
							tempStr = keyboard.nextLine();
							
							if(tempStr.equals(""))
								tempStr = "[no madlib available]";
							
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
		
		else {
			for(int i = 0; i < str.length(); i++) {
				if(chars[i] == '<') {
					for(int j = i + 1; j < str.length(); j++) {
						if(chars[j] =='>') {
							Integer[] tempInts = new Integer[2];
							tempInts[0] = i;
							tempInts[1] = j;

							bracketIndex.add(tempInts);
							i = j + 1;
							break;
							
						}
							
					}
					
				}	
				
			}
			
			int beginIndex = 0;
			
			for(int m = 0; m < chars2.length; m++) {
				if(chars2[m] == '\n') {
					if(! "".equals(str.substring(beginIndex, m)))
							fillIns.add(str2.substring(beginIndex, m));
					
					beginIndex = m + 1; 
				}
			}
			
			if(fillIns.size() != 0 && ! "".equals(str2.substring(beginIndex)))
				fillIns.add(str2.substring(beginIndex));
			
			int tempSize = fillIns.size();
			
			for (int b = bracketIndex.size(); b > tempSize; b--) {
				fillIns.add("[no madlib available]");
			}
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
		
		if(bracketIndex.size() > 0)
			newStr += str.substring(bracketIndex.get(bracketIndex.size() - 1)[1] + 1);
		
		if(! newStr.equals(""))
			strBuffer = new StringBuffer(newStr);
		
		return strBuffer;
	}


}