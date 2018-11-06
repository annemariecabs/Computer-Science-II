import java.util.Set;
import java.util.TreeMap;

/**
 * The DocumentIndex is used to hold an index created for a document. The class has one field
 * a TreeMap, which has a String key mapped to an IndexEntryValue and which holds the index 
 * entries created for the document. The key value for the TreeMap is the word that the 
 * corresponding IndexEntry holds. 
 * 
 * The class has one constructor, which only initializes a new TreeMap. It has two main functionalities:
 * addWord and addAllWords. The addWord method takes a String and checks if a String has been used 
 * as a key value in the TreeMap yet (essentially if the word has already been added to the index).
 * If the word hasn't been added, a new IndexEntry is created with the word provided and the 
 * integer parameter is added as the first line number. Then, the new IndexEntry is added 
 * to the TreeMap. If the word has been added, it just adds another number to the TreeSet of line
 * numbers in the IndexEntry already. The addAllWords method splits a String by its spaces, then adds 
 * the words in the String to the TreeMap with the number provided. The addAllWords is intended to
 * add a whole line of a document to a DocumentIndex, and uses the addWord method internally. Once again,
 * this class formats the words as uppercase once they have been added to the document.
 * 
 * Additionally, I added the methods getKeys() and getEntry(String key) in addition to the 
 * required functions because the IndexMaker class needed the ability to access the keys
 * and entries in the index. Both are simple getters that allow access to TreeMap functions
 * that do the same thing.
 * 
 * 
 * @author AnneMarie Caballero
 */

public class DocumentIndex {
	
	/**
	 * A TreeMap with a String key mapped on to an Index Entry value that acts as the index 
	 * for the document. The word of the IndexEntry acts as the key to find the IndexEntry.
	 */
	private TreeMap<String, IndexEntry> index;
	
	/**
	 * Constructs a DocumentIndex by initializing the index field to a default
	 * TreeMap
	 */
	public DocumentIndex() {
		index = new TreeMap<String, IndexEntry>();
	}
	
	/**
	 * Returns the keySet() of index. This allows access to a TreeMap function needed
	 * for the IndexMaker class.
	 * 
	 * @return the keySet() of the TreeMap that holds the IndexEntries.
	 * @see IndexMaker
	 */
	public Set<String> getKeys() {
		return index.keySet();
	}
	
	/**
	 * This method returns the corresponding IndexEntry to the key provided if the key
	 * set does contain the key. The method was created for its use within IndexMaker,
	 * and, as such, it is unlikely it will return null within that class because IndexMaker 
	 * uses it with the key set provided by getKeys()
	 * 
	 * @param key a String that if it is found within the key set for the TreeMap 
	 * 			  will return the corresponding IndexEntry for the key
	 * @return an IndexEntry if index has key and null if IndexEntry does not
	 * @see IndexMaker
	 */
	public IndexEntry getEntry(String key) {
		return index.get(key);
	}
	
	/**
	 * Adds word to the index by taking the word parameter and checking if it is already in 
	 * index. If it is, the method will add the given int to the TreeSet of line numbers 
	 * for the corresponding IndexEntry. If not, the method will add a new IndexEntry to the 
	 * index with word as the value and num as the first line number it appears on. Words when
	 * added to the index are formatted as uppercase.
	 * 
	 * @param word the word to be added
	 * @param num the line number the word was found on
	 * @see #addAllWords(String str, int num)
	 */
	public void addWord(String word, int num) {
		IndexEntry temp;
		word = word.toUpperCase();
		
		if(word.equals(""))
			return;
		
		if(index.containsKey(word)) {
			temp = index.get(word);
			temp.add(num);
		}
		else {
			temp = new IndexEntry(word);
			temp.add(num);
			index.put(word, temp);
		}
	}
	
	/**
	 * This method adds all words in a given String to the index. The method is intended
	 * to take a line of a document and add all of the new words on the line to the index
	 * and add the number of the line to the TreeSet of line numbers for words already in the index.
	 * The method uses the {@link #addWord(String word, int num)} method of this class to do so. Words when added
	 * to the index are formatted as uppercase. The method utilizes regex to ensure that 
	 * words do not include numbers or unnecessary punctuation.
	 * 
	 * @param str String that contains the words to be added to index.
	 * @param num number of the line of the document str is
	 */
	public void addAllWords(String str, int num) {
		String[] words = str.split(" ");
		char[] wordChars;
		String ch;

		for(String word: words) {
			wordChars = word.toCharArray();
			String temp = "";
			for(int i = 0; i < wordChars.length; i++) {
				ch = "" + wordChars[i];
				//the below expression uses regex to remove all non-alphabetic characters
				//the second half of the condition allows hyphenated words to be added
				//while still taking out dashes that only act as punctuation
				if(ch.matches("[a-zA-Z']") || ((ch.equals("-") && i + 1 != wordChars.length && 
					i != 0 && '-' != wordChars[i - 1])))
					temp += wordChars[i];
			}
			
			word = temp.toUpperCase();
			addWord(word, num);
		}
	}

}
