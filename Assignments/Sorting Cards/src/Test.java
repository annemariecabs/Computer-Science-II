import java.util.Arrays;

/*
* A test class for Card, Deck, and CardComparator
* 
* @author AnneMarie Caballero
*/

public class Test {
	
	public static void main(String[] args) {
		Card[] cards = {new Card(3, 2), new Card(0, 6), new Card(1, 5)};
		Deck deck1 = new Deck(cards);
		System.out.println(deck1);
		
		deck1.mergeSort();
		System.out.println(deck1);
		
		
		Deck deck2 = new Deck(false);
		System.out.println(deck2);
		
		deck2.selectionSort();
		
		System.out.println(deck2);
		
		Deck deck3 = new Deck();
		Deck[] decks = deck3.deal(10, 5);
		
		if(decks != null) {
			for(Deck d: decks) {
				System.out.println(d);
				d.mergeSort();
				System.out.println(d);
			}
				
		}
		
		
	}
	
}