import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * TicTacToeMyHashMap is a class to store winning TicTacToe Strings in a HashMap
 * with TTTHashString keys and boolean values. The class fills a hash map with the Strings
 * than analyzes the map for aspects like capacity, load factor, and distribution. 
 * This class will mainly be used to analyze the efficiency of a hash map with my
 * hashCode method as compared to Java's hash code in the storage of TicTacToe strings.
 * Additionally, this class uses reflection in order to analyze the hash map considering
 * the array in which the values are stored is not easily accessible, and HashMap.Entry, which
 * is the type of array is not a public Java class. 
 * 
 * Additionally, this class has the nested class of TTTHashString. This class
 * was created in order for the hash map to use my overridden hashCode method in tttHashString
 * rather than Java's. For almost all intents and purpose, it is basically a String class
 * that has an overridden hash code and needs to be created with a specific hash map capacity,
 * in order for the hashCode method to be effective.
 * 
 * Note: Matthew Grillo basically made this class possible through his thorough research on the subject.
 * All adulation directed toward this class would be misdirected unless 80% was directed towards him.
 * This is the same comment as the one included in TicTacToeHashMap's Javadocs
 * Also, the capacity() method was mainly written by Mrs. Kelly.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */

public class TicTacToeMyHashMap  {
	
	/**
	 * The HashMap that holds the winning TicTacToe Strings. The key is 
	 * a TTTHashString and its boolean value, which should just be true for
	 * the filled in Strings, is stored in the HashMap. TTTHashString is used in this method
	 * because its hashCode has been overridden by myself.
	 */
	HashMap<TTTHashString, Boolean> winners;

	/**
	 * The number of winning TicTacToe Strings
	 */
	private static final int NUMBER_OF_WINNERS = 1400;

	/**
	 * The size of the HashMap chosen because it is one of the closest powers of two (specifically it's 2^9)
	 * to a load factor of two (and I would prefer the smaller one out of the two options)
	 */
	private static final int HASHMAP_SIZE = 512;

	/**
	 * An integer constant holding the number (1) that is passed to System.exit()
	 * after certain conditions occur
	 */
	private static final int ERROR_CODE = 1;

	/**
	 * The name of the file with the TicTacToe winners
	 */
	private static final String WINNER_FILE = "TicTacToeWinners.txt";

	/**
	 * The error message used if the file with the winners is not found
	 */
	private static final String WINNER_FILE_NOT_FOUND = "The file with the winning TicTacToe strings is not available, which means the program must end";
	
	/**
	 * Constructs a TicTacToeMyHashMap, which holds a HashMap with TTTHashString key and a Boolean value. The 
	 * method also initializes the HashMap using all possible winning TicTacToe Strings.
	 * The HashMap capacity is set specifically to HASHMAP_SIZE and the load factor is 
	 * set so that it would not be automatically resized. This method uses my hashCode
	 * method written below in the special TTTHashString method.
	 */
	TicTacToeMyHashMap() {

		winners = new HashMap<TTTHashString, Boolean>(HASHMAP_SIZE, NUMBER_OF_WINNERS/((float) HASHMAP_SIZE));

		Scanner winReader = null;

		try {
			winReader = new Scanner(new File(WINNER_FILE));
		} catch (FileNotFoundException e) {
			System.out.println(WINNER_FILE_NOT_FOUND);
			System.exit(ERROR_CODE);
		}

		String current;

		while(winReader.hasNextLine()) {
			current = winReader.nextLine();
			winners.put(new TTTHashString(current, HASHMAP_SIZE), true);
		}

		winReader.close();

	}
	
	/**
	 * Uses reflection to return the capacity of the winners HashMap
	 * 
	 * @return the capacity of the winners HashMap
	 * @throws NoSuchFieldException thrown if the reflective field this method is looking for,
	 * 		which is table, cannot be found
	 * @throws IllegalAccessException thrown when the method tries to use
	 * 		reflection without access
	 */
	private int capacity() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(winners);
		return table == null ? 0 : table.length;   
	}
	
	/**
	 * Uses reflection to return the table of the winners HashMap, which is a HashMap.Entry[]
	 * 
	 * @return a HashMap.Entry[] that represents the table in a HashMap
	 * @throws NoSuchFieldException thrown if the reflective field this method is looking for,
	 * 		which is table, cannot be found
	 * @throws IllegalAccessException thrown when the method tries to use
	 * 		reflection without access
	 */
	private HashMap.Entry[] getTable() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(winners);
		return (HashMap.Entry[]) table;   
	}
	
	/**
	 * Uses reflection to return the next node after a HashMap.Entry in a linked list
	 * 
	 * @param entry a HashMap.Entry for which the next node will be found
	 * @return the next node after a HashMap.Entry
	 * @throws NoSuchFieldException thrown if the reflective field this method is looking for,
	 * 		which is next, cannot be found
	 * @throws IllegalAccessException thrown when the method tries to use
	 * 		reflection without access
	 */
	private Object getNext(HashMap.Entry entry) throws NoSuchFieldException, IllegalAccessException {
		Field nextField = entry.getClass().getDeclaredField("next");
		nextField.setAccessible(true);
		Object next = (Object) nextField.get(entry);
		return next;   
	}
	
	/**
	 * Uses reflection whether a HashMap.Entry in a linked list has a next node
	 * 
	 * @param entry a HashMap.Entry for which it will be assessed if it has a next node
	 * @return if entry has a node after it 
	 * @throws NoSuchFieldException thrown if the reflective field this method is looking for,
	 * 		which is next, cannot be found
	 * @throws IllegalAccessException thrown when the method tries to use
	 * 		reflection without access
	 */
	private boolean hasNext(HashMap.Entry entry) throws NoSuchFieldException, IllegalAccessException {
		Field nextField = entry.getClass().getDeclaredField("next");
		nextField.setAccessible(true);
		Object next = (Object) nextField.get(entry);
		return next != null;
	}

	public static void main(String[] args) throws java.io.FileNotFoundException,
	NoSuchFieldException, 
	IllegalAccessException {

		TicTacToeMyHashMap m = new TicTacToeMyHashMap();
		
		NumberFormat decimal = new DecimalFormat("#0.00");

		//RETRIEVE ENTRIES
		HashMap.Entry[] table = m.getTable();
		int entryCount = 0;
		int emptyCount = 0;
		int collisionCount = 0;

		int[] quarters = new int[4];
		int[] tenths = new int[10];

		int currentQ = 1, currentT = 1, entriesPerQ = 0, collPerTenth = 0;

		int chainLength;
		ArrayList<Integer> chainLengths = new ArrayList<Integer>();

		for (int i = 0; i < table.length; i++) {
			HashMap.Entry e = table[i];
			if(e == null) {
				emptyCount++;
				continue;
			}

			if(i >= m.capacity()/4.0 * currentQ) {
				quarters[currentQ - 1] = entriesPerQ; 
				currentQ++;
				entriesPerQ = 0;

			}

			if(i >= m.capacity()/10.0 * currentT) {
				tenths[currentT - 1] = collPerTenth;
				currentT++;
				collPerTenth = 0;
			}

			entryCount++;
			entriesPerQ++;

			if(m.hasNext(e)) {
				
				chainLength = 1;

				for(HashMap.Entry temp = (HashMap.Entry) m.getNext(e); temp != null; temp = (HashMap.Entry) m.getNext(temp)) {
					entryCount++; 
					entriesPerQ++;
					collPerTenth++;
					collisionCount++;
					chainLength++;

				}
				
				chainLengths.add(chainLength);
			}
		}

			quarters[currentQ - 1] = entriesPerQ;
			tenths[currentT - 1] = collPerTenth;
			
			int maxChainLength = 0, sumChL = 0;

			if(chainLengths.size() != 0) {
				maxChainLength = chainLengths.get(0);
				sumChL = chainLengths.get(0);

				for(int i = 1; i < chainLengths.size(); i++) {
					if(chainLengths.get(i) > maxChainLength)
						maxChainLength = chainLengths.get(i);

					sumChL += chainLengths.get(i);
				}
			}

			double avgChainLength = sumChL/(double) chainLengths.size();
			
			System.out.print("\nCapacity: " + m.capacity()
			   + "\nLoad Factor: " + decimal.format(NUMBER_OF_WINNERS/(double) HASHMAP_SIZE)
			   + "\nNumber of Entries: " + entryCount
			   + "\nEntries per Quarter: " + Arrays.toString(quarters)
			   + "\nNumber of Collisions: " + collisionCount
			   + "\nCollisions per Tenth: " + Arrays.toString(tenths)
			   + "\nNumber of Chains: " + chainLengths.size()
			   + "\nMaximum Chain Length: " + maxChainLength
			   + "\nAverage Chain Length: " + decimal.format(avgChainLength)
			   + "\nEmpty Spaces: " + emptyCount);
	
		}
	
	/**
	 * A nested class within TicTacToeMyHashMap. This class was created to hold a String
	 * with an overridden hashCode method, specifically one that I created. The class
	 * holds a value, which is a String, and the size of the hash it is being used in.
	 * Currently, the class has limited function considering it is a nested class
	 * and as such will only be used within the above TicTacToeMyHashMap, which 
	 * is why its functions are so limited.
	 * 
	 * 
	 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
	 *
	 */
	private class TTTHashString {
		
		/**
		 * The String value of the TTTHashString. This object is meant to function as 
		 * a String, but one with a customized hashCode, so this method is important.
		 */
		private String value;
		
		/**
		 * The size of the hash this String will go into which is important because
		 * it is a part of the custom hashCode method. This information
		 * will need to come from outside the program.
		 */
		private int sizeOfHash; 
		
		/**
		 * A lookup table with powers of three where the each spot holds three ^ index
		 */
		private final int[] POWERS_OF_THREE = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683};
		
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
		
		TTTHashString(String v, int size) {
			value = v;
			sizeOfHash = size;
		}
		
		/**
		 * Returns a hash code for each winner to be stored. This hash code method will 
		 * cause collisions because this method is focusing on being efficient with space.
		 * This class was created in order for the hash code to be overridden, so this 
		 * method is important. This method is trying to balance storage and efficiency concerns, so
		 * it tries to keep a fairly low average chain length and number of empty spaces.
		 * 
		 * @return an int hash code based off the hash algorithm I created
		 */
		@Override
		public int hashCode() {
			
			int power = 0, sum = 0;
			char[] ch = value.toCharArray();

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

			return sum % sizeOfHash;
		}
		
	}
}

