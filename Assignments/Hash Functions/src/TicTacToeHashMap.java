import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//TODO HashMap Tree-ify
public class TicTacToeHashMap  {

	HashMap<String, Boolean> winners;

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

	TicTacToeHashMap() {
		// TODO Instantiate/fill your HashMap ... pay attention to initial capacity and load values
		//if it was just default, it would be 16 and .75
		winners = new HashMap<String, Boolean>(HASHMAP_SIZE, NUMBER_OF_WINNERS/((float) HASHMAP_SIZE));
		//if you set the load factor

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
			winners.put(current, true);
		}

		winReader.close();

	}

	// TODO This method uses reflect to investigate the objects inside the HashMap
	// You should be able to update this with your information and determine 
	// Information about capacity (different than size()) and what is stored in the cells

	private int capacity() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(winners);
		return table == null ? 0 : table.length;   
	}

	private HashMap.Entry[] getTable() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(winners);
		return (HashMap.Entry[]) table;   
	}

	private Object getNext(HashMap.Entry entry) throws NoSuchFieldException, IllegalAccessException {
		Field nextField = entry.getClass().getDeclaredField("next");
		nextField.setAccessible(true);
		Object next = (Object) nextField.get(entry);
		return next;   
	}

	private boolean hasNext(HashMap.Entry entry) throws NoSuchFieldException, IllegalAccessException {
		Field nextField = entry.getClass().getDeclaredField("next");
		nextField.setAccessible(true);
		Object next = (Object) nextField.get(entry);
		return next != null;
	}

	// TODO using the same code to get the table of entries as in the capacity method,
	// create a method that will evaluate the table as directed in the assignment.
	// note - if an entry is not null, then it has a value, it may have more than one value
	// see if you can determine how many values it has.  Using the debugger will assist.

	public static void main(String[] args) throws java.io.FileNotFoundException,
	NoSuchFieldException, 
	IllegalAccessException {

		TicTacToeHashMap m = new TicTacToeHashMap();

		System.out.print("\nCapacity: " + m.capacity());

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

			System.out.println("\nNumber of Entries: " + entryCount
					   + "\nLoad Factor: " + NUMBER_OF_WINNERS/(double) HASHMAP_SIZE
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
					   + "\nNumber of Collisions in Tenth Tenth: " + tenths[9]
					   + "\n\nMaximum Chain Length: " + maxChainLength
					   + "\nAverage Chain Length: " + avgChainLength);
			
			
			
			//TODO remove collision check stuff
			int collsByTenth = 0;
			for(int t: tenths)
				collsByTenth += t;
			System.out.println("\n\nCollisions counted: " + collisionCount
					+ "\nSum of collisions per tenth: " + collsByTenth
					+ "\nMath collision stuff: " + (NUMBER_OF_WINNERS - m.capacity() + emptyCount));

			// TODO print out the other analytical statistics:
			//load factor, capacity, number of entries stored in the tables
			//distribution (number of entries per quarter and number of collisions in each tenth)
			//right number of entries and collisions - yayayayayaya
			//average chain length, maximum chain length

		}

}