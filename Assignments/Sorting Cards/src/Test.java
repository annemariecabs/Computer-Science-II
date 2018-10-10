import java.util.Arrays;

/*
* A test class for Card, Deck, and CardComparator
* 
* @author AnneMarie Caballero
*/

public class Test {
	
	public static void main(String[] args) {
		Card[] cards = {new Card(1,1), new Card(3, 10), new Card(2, 6), new Card(2, 5)};
		
		Deck deck2 = new Deck(cards);
		System.out.println(deck2);
		
		deck2.selectionSort();
		
		System.out.println(deck2);
		
		
	}
	
}