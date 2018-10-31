import java.util.TreeSet;

/**
 * 
 * 
 * @author AnneMarie Caballero
 *
 */


public class IndexEntry {
	
	private String word;
	private TreeSet<Integer> lineNumbers;
	
	public IndexEntry(String w) {
		word = w;
		lineNumbers = new TreeSet<Integer>(); //TODO: should I do something special about capacity?
	}
	
	/**
	 * Adds a number to the list of line numbers (if it is not already in the list).
	 * 
	 * @param num 
	 */
	public void add(int num) {
		lineNumbers.add(num);
	}
	
	public String getWord() {
		return word;
	}
	
	public String toString() {
		String temp = "";
		
		for(Integer i: lineNumbers) {
			temp += i.toString() +", ";
		}
		
		temp = temp.substring(0, temp.length() - 2);
		
		return word.toUpperCase() + " " + temp;
		
	}

}
