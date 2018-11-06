import java.util.Set;
import java.util.TreeMap;

/**
 * The DocumentIndex is used to hold an index created for a document. The class has one field
 * a TreeMap<String, IndexEntry> which holds the index entries created for the document. 
 * The key value for the TreeMap is the word that the corresponding IndexEntry has. 
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
 * @author AnneMarie Caballero
 * @see IndexEntry, IndexMaker
 */

public class DocumentIndex {
	
	/**
	 * A TreeMap<String, IndexEntry> that acts as the index for the document. The word 
	 * of the IndexEntry acts as the key to find the IndexEntry.
	 */
	private TreeMap<String, IndexEntry> index;
	
	/**
	 * Constructs a DocumentIndex by initializing the index field to a default
	 * TreeMap<String, IndexEntry>
	 */
	public DocumentIndex() {
		index = new TreeMap<String, IndexEntry>();
	}
	
	//TODO: write Javadocs
	public Set<String> getKeys() {
		return index.keySet();
	}
	
	//TODO: write Javadocs
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
	 * @see addAllWords() 
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
	 * The method uses the {@link addWord()} method of this class to do so. Words when added
	 * to the index are formatted as uppercase.
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
			for(char c: wordChars) {
				ch = "" + c;
				if(ch.matches("[a-zA-Z]"))
					temp += c;
			}
			
			word = temp.toUpperCase();
			addWord(word, num);
		}
	}

}
