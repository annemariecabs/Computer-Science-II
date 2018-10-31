import java.util.Comparator;

/**
 * 
 * This Comparator is for Card objects, and compares them on the basis of rank. This Comparator was modeled 
 * off the Comparator example from pg.384 of the Java Methods book by Maria and Gary Litvin. 
 * 
 * The Comparator does not include suit in its comparison. Additionally, the comparator has
 * one boolean which determines whether or not the comparator is sorting in ascending order (if true)
 * or descending order (if false). The default constructor however just sets this boolean field
 * to true.
 *
 * @author AnneMarie Caballero
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html"> Comparator </a>
 */

public class CardComparator implements Comparator<Card> {
	
	/**
	 * A boolean that keeps track of whether the Cards should be sorted in ascending
	 * or descending order. If true, Cards should be sorted in ascending order.
	 * If false, Cards should be sorted in ascending order.
	 */
	private boolean ascend;
	
	/**
	 * Constructs a default CardComparator object that can be used to sort Cards in ascending order
	 */
	public CardComparator() {
		ascend = true;
	}
	
	/**
	 * Constructs a default CardComparator object
	 * 
	 * @param asc a boolean that if true, indicates to sort cards in an ascending fashion, and if false
	 * 			  indicates to sort cards in a descending fashion
	 */
	public CardComparator(boolean asc) {
		ascend = asc;
	}
	
	/**
	 * Compares two Cards
	 * 
	 * @param card1 the first Card to be compared
	 * @param card2 the second Card to be compared
	 * @return an int that represent the difference between card1 and card2.
	 * 		   the difference will be negative if asc is false.
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Card card1, Card card2) {
		
		int diff = card1.getRank() - card2.getRank();
		
		if(ascend) {
			return diff;
		}
		else {
			return -diff;
		}
	}

}