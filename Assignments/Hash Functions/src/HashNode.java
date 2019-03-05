/**
 * This class is for a node in a LinkedList for use in projects involving hashing.
 * Specifically, this class would be used for chaining in LinkedLists when
 * collisions occur in hash maps and other data storage that uses hashes
 * with lookup tables. The class has the ability to hold a hash integer,
 * a String that is the value of the node, and the next node (meaning it
 * is a singly-linked list). This class was created for use with TicTacToe 
 * Strings.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */
public class HashNode {
	
	/**
	 * An integer that holds the hash number of the HashNode
	 */
	private int hash;
	
	/**
	 * The value of the HashNode; this class was specifically created
	 * for use with TicTacToe Strings, which is why it was named as such.
	 */
	private String ticTacToe;
	
	/**
	 * Holds the reference to the next HashNode in the linked list
	 */
	private HashNode next;
	
	/**
	 * Creates an object of type HashNode and initializes all
	 * of its field as the defaults
	 */
	public HashNode() {
		hash = 0;
		ticTacToe = null;
		next = null;
	}
	
	/**
	 * Creates a HashNode object using the pertinent values:
	 * a hash code and a String value, but does not set the next
	 * HashNode in the linked list
	 * 
	 * @param h the hash code that the value is being stored at
	 * @param t the value of the Hash
	 */
	public HashNode(int h, String t) {
		hash = h;
		ticTacToe = t;
	}
	
	/**
	 * Creates a HashNode object using a hash code and a String value and setting
	 * the next HashNode in the linked-list
	 * 
	 * @param h the hash code the value is being stored at
	 * @param t the value of the HashNode
	 * @param n the next HashNode in the list
	 */
	public HashNode(int h, String t, HashNode n) {
		hash = h;
		ticTacToe = t;
		next = n;
	}
	
	/**
	 * Returns the value of the String field, which is the main value
	 * of the Object itself. In the program this is currently being used for,
	 * this would be a TicTacToe String.
	 * 
	 * @return the String value
	 */
	public String getValue() {
		return ticTacToe;
	}
	
	/**
	 * Returns the hash code of the HashNode. Currently, this hash code is being
	 * used as the index for an array of winning TicTacToe Strings.
	 * 
	 * @return the hash node
	 */
	public int getHash() {
		return hash;
	}
	
	/**
	 * Returns whether there is another HashNode after this one.
	 * 
	 * @return true if there is another HashNode after the current one in the array,
	 * 		false otherwise
	 */
	public boolean hasNext() {
		if(getNext() == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Returns the HashNode after the current one in the array.
	 * 
	 * @return the next HashNode
	 */
	public HashNode getNext() {
		return next;
	}
	
	/**
	 * Sets the values of the next HashNode in the array using a given
	 * HashNode.
	 * 
	 * @param h the HashNode to be set as next
	 */
	public void setNext(HashNode h) {
		next = h;
	}


}
