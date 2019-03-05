import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TTT_HC method accomplishes a similar purpose as TicTacToeHashCode except
 * the hash code method in this class is intended to cause collisions and deals
 * with these collisions through chaining. This class uses HashNodes to accomplish
 * this, as the HashNodes essentially have the functions of a node in a linked list
 * but are specialized for use with methods that have hash functions in them because
 * they hold a String value and a hash number. The main of this method creates an object
 * of this class, and analyzes the efficiency of the winners array in a number of fields
 * ranging from load factor to collisions per tenth.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */

public class TTT_HC {
	
	/**
	 * The winners array which will store the Strings of all the possible
	 * TicTacToe winners using HashNodes
	 */
 	private HashNode[] winners;

	/**
	 * The name of the file with the TicTacToe winners
	 */
	private static final String WINNER_FILE = "TicTacToeWinners.txt";
	
	/**
	 * The error message used if the file with the winners is not found
	 */
	private static final String WINNER_FILE_NOT_FOUND = "The file with the winning TicTacToe strings is not available, which means the program must end";

	/**
	 * A lookup table with powers of three where the each spot holds three ^ index
	 */
	private static final int[] POWERS_OF_THREE = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683};

	/**
	 * A char holding a space
	 */
	private static final char SPACE = ' ';

	/**
	 * A char holding an x
	 */
	private static final char X = 'x';

	/**
	 * A char holding an o
	 */
	private static final char O = 'o';
	
	/**
	 * An integer constant holding the number (1) that is passed to System.exit()
	 * after certain conditions occur
	 */
	private static final int ERROR_CODE = 1;
	
	/**
	 * The number of winning TicTacToe Strings
	 */
	private static final int NUMBER_OF_WINNERS = 1400;
	
	/**
	 * This creates a TTT_HC by instantiating and filling the winners array of HashNodes
	 * which should hold all of the winning Strings at their proper hash code
	 * index. If there is already a HashNode at that index, the constructor
	 * uses chaining as the method of solving collisions by adding the HashNode
	 * at that index, and setting the next HashNode to the current HashNode
	 * located there.
	 * 
	 * @param boardTitle the title of the board 
	 */
	TTT_HC() {
		winners = new HashNode[700]; 

		Scanner winReader = null;

		try {
			winReader = new Scanner(new File(WINNER_FILE));
		} catch (FileNotFoundException e) {
			System.out.println(WINNER_FILE_NOT_FOUND);
			System.exit(ERROR_CODE);
		}

		String current;
		int hash;
		HashNode temp;

		while(winReader.hasNextLine()) {
			current = winReader.nextLine();
			hash = tttHashCode(current);

			if(winners[hash] != null) {
				temp = winners[hash];
				winners[hash] = new HashNode(hash, current, temp);
			}
			else
				winners[hash] = new HashNode(hash, current);
		}

		winReader.close();
	}
	
	/**
	 * Returns a hash code for each winner to be stored. This hash code method will 
	 * cause collisions because this method is focusing on being efficient with space.
	 * 
	 * @param str the String to create a hash code for
	 * @return an integer hash code for str
	 */
	public int tttHashCode(String str) {

		int power = 0, sum = 0;
		char[] ch = str.toCharArray();

		for(int i = 0; i < ch.length; i++) {
			switch(ch[i]) {
			case SPACE:
				break; //equivalent to adding zero
			case X:
				sum += POWERS_OF_THREE[power]; //equivalent to adding 1 * 3^power
				break;
			case O: 
				sum += 2 * POWERS_OF_THREE[power];
				break;
			}
			power++;
		}

		return sum % (int) (700);

	}
	
	/**
	 * Analyzes and prints the required information surrounding the winners method
	 * which is: size of the array, load factor, the number of chains, 
	 * the maximum chain length, the average chain length, the number
	 * of entries per quarter and the number of collisions per tenth.
	 * 
	 * @param arraySize the size of the array to be analyzed
	 * @param chainLengths an ArrayList of all of the chainLengths (greater 
	 * 		than 1) found in the array being analyzed
	 * @param entrySpots an ArrayList of the HashNodes that are directly located
	 * 		in winners
	 * @param collisionSpots an ArrayList of the HashNodes where collisions occurred
	 */
	public static void analyzeAndPrint(int arraySize, ArrayList<Integer> chainLengths,
			ArrayList<HashNode> entrySpots, ArrayList<HashNode> collisionSpots) {

		//TODO is this actually the right load factor
		double loadFactor = ((double) NUMBER_OF_WINNERS)/arraySize;

		int chainNum = chainLengths.size(), maxChainLength = 0, sumChL = 0;

		if(chainLengths.size() != 0) {
			maxChainLength = chainLengths.get(0);
			sumChL = chainLengths.get(0);

			for(int i = 1; i < chainNum; i++) {
				if(chainLengths.get(i) > maxChainLength)
					maxChainLength = chainLengths.get(i);

				sumChL += chainLengths.get(i);
			}
		}

		int avgChainLength = sumChL/chainNum;

		int[] quarters = new int[4];
		int[] tenths = new int[10];
		
		int currentQ = 1, currentT = 1, entriesPerQ = 0, collPerTenth = 0;

		for(HashNode e: entrySpots) {
			
			if(e.getHash() >= arraySize/4.0 * currentQ) {
				quarters[currentQ - 1] = entriesPerQ; 
				currentQ++;
				entriesPerQ = 0;

			}
			
			for(HashNode temp = e; temp != null; temp = temp.getNext()) {
				entriesPerQ++;
			}
		}
		
		quarters[currentQ - 1] = entriesPerQ;

		for(HashNode c: collisionSpots) {
			if(c.getHash() >= arraySize/10.0 * currentT) {
				tenths[currentT - 1] = collPerTenth;
				currentT++;
				collPerTenth = 0;
			}

			for(HashNode temp = c; temp != null; temp = temp.getNext()) {
				collPerTenth++;
			}
		}
		
		tenths[currentT - 1] = collPerTenth;


		System.out.print("Size of the Array: " + arraySize 
				+ "\nLoad Factor: " + loadFactor
				+ "\nNumber of Chains: " + chainNum
				+ "\nMaximum Chain Length: " + maxChainLength
				+ "\nAverage Chain Length: " + avgChainLength
				+ "\n\nNumber of Entries in First Quarter: " + quarters[0]
				+ "\nNumber of Entries in Second Quarter: " + quarters[1]
				+ "\nNumber of Entries in Third Quarter: " + quarters[2]
				+ "\nNumber of Entries in Fourth Quarter: " + quarters[3]
				+ "\n\nNumber of Collisions in First Tenth: " + tenths[0] 
				+ "\nNumber of Collisions in Second Tenth: " + tenths[1]
				+ "\nNumber of Collisions in Third Tenth: " + tenths[2]
				+ "\nNumber of Collisions in Fourth Tenth: " + tenths[3]
				+ "\nNumber of Collisions in Fifth Tenth: " + tenths[4]
				+ "\nNumber of Collisions in Sixth Tenth: " + tenths[5]
				+ "\nNumber of Collisions in Seventh Tenth: " + tenths[6]
				+ "\nNumber of Collisions in Eighth Tenth: " + tenths[7]
				+ "\nNumber of Collisions in Ninth Tenth: " + tenths[8] 
				+ "\nNumber of Collisions in Tenth Tenth: " + tenths[9]);


	}

	//this just gets the basic data by traversing the array
	public static void main(String[] args) {
		TTT_HC tttHC = new TTT_HC();

		ArrayList<HashNode> entrySpaces = new ArrayList<HashNode>();

		int chainLength;
		ArrayList<Integer> chainLengths = new ArrayList<Integer>();
		ArrayList<HashNode> collSpaces = new ArrayList<HashNode>();
		HashNode w;

		for(int i = 0; i < tttHC.winners.length; i++) {
			w = tttHC.winners[i];

			if(w == null) 
				continue;
			else
				entrySpaces.add(w);
			
			if(w.hasNext()) {

				collSpaces.add(w);
				chainLength = 0;

				for(HashNode temp = w; temp != null; temp = temp.getNext()) {
					chainLength++;
				}

				chainLengths.add(chainLength);
			}

		}

		analyzeAndPrint(tttHC.winners.length, chainLengths, entrySpaces, collSpaces);
	}

}
