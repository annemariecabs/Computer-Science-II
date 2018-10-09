import java.util.Arrays;

/**
*
*
*/

public class Test {
	
	public static void main(String[] args) {
		Deck deck = new Deck(false);
		
		Arrays.sort(deck.getCards(), new CardComparator(false));
		
		System.out.println(deck.getCards().toString());
		
	}
	
}