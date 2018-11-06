import java.util.TreeSet;

/**
 * The IndexEntry class represents individual entries in the index. The class has two fields:
 * word and lineNumbers. The first field represents the word in the IndexEntry, and the second
 * field represents the number of lines that word appeared on. 
 * 
 * The main functionalitiy of this class beside the standard getters and toString() is the add() 
 * method which allows numbers to be added to the TreeSet lineNumbers. There are getters for 
 * both fields, and a toString which formats the IndexEntry's corresponding String value as
 * "HELLO 1, 7, 9" if the word hello appeared on certain lines in a document, although this class
 * does not deal with such a document, other related classes in the assignment do. Both internally
 * and externally, the word is formatted in all uppercase.
 * 
 * @author AnneMarie Caballero
 * @see DocumentIndex, IndexMaker
 *
 */


public class IndexEntry {
	
	/**
	 * a String that holds the word that appears on the line numbers in the TreeSet
	 */
	private String word;
	
	/**
	 * a TreeSet of Integers that holds the line numbers that word appears on
	 */
	private TreeSet<Integer> lineNumbers;
	
	/**
	 * Initializes an IndexEntry using a single String parameter. The constructor sets the word
	 * field to the parameter. The constructor also initializes a TreeSet that will hold the 
	 * line numbers the word potentially appears on in a document.
	 * 
	 * @param w sets the word field of the IndexEntry
	 * @throws IllegalArgumentException if w is null
	 */
	public IndexEntry(String w) {
		if(w.equals(null))
			throw new IllegalArgumentException("Cannot create an IndexEntry with a null value");
		
		word = w.toUpperCase();
		lineNumbers = new TreeSet<Integer>(); 
	}
	
	/**
	 * Adds a number to the list of line numbers (if it is not already in the list).
	 * 
	 * @param num the line number to be added to the list of numbers
	 */
	public void add(int num) {
		lineNumbers.add(num);
	}
	
	/**
	 * Returns the word field of the IndexEntry.
	 * 
	 * @return a String that is the current value of the IndexEntry's word field
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Returns the corresponding String value for the IndexEntry. The String is formatted like
	 * "THIS 3, 5, 9" if the word this had appeared on lines 3, 5, and 9 of a hypothetical 
	 * document.
	 * 
	 * @return a String that represents the IndexEntry.
	 */
	public String toString() {
		String temp = "";
		
		for(Integer i: lineNumbers) {
			temp += i.toString() +", ";
		}
		
		temp = temp.substring(0, temp.length() - 2);
		
		return word.toUpperCase() + " " + temp;
		
	}

}
