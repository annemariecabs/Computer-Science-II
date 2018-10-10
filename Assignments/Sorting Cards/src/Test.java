import java.util.Arrays;

/*
* A test class for Card, Deck, and CardComparator
* 
* @author AnneMarie Caballero
*/

public class Test {
	
	public static void main(String[] args) {
		Deck deck = new Deck();
		Deck deck2 = new Deck(true);
		
		System.out.println(deck.equals(deck2));
		
		
	}
	
}