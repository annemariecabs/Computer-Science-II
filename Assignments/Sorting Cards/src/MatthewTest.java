/*
 * @author Matthew Grillo
 */


public class MatthewTest {
	public static void main(String[] args) {
		Card c = new Card();
		System.out.println(String.format("Default Card is Ace of Clubs: %b", c.toString().equals("Ace of Clubs")));

		System.out.println("\nCreating real int cards: ");

		// Real int suits and ranks
		c = new Card(0, 1);
		po(c);
		c = new Card(1, 11);
		po(c);
		c = new Card(2, 12);
		po(c);
		c = new Card(3, 13);
		po(c);
		c = new Card(3, 2);
		po(c);
		
	
		// Random suit ints
		/*
		c = new Card(-1, 1);
		System.out.println(String.format("Random int suits allowed: %b", !c.toString().contains("Error")));
		
		c = new Card(1, -1);
		System.out.println(String.format("Random int ranks allowed: %b", !c.toString().contains("Error")));
		*/
		
		pl();

		System.out.println("\nCreating real String cards: ");

		// Real String suits and ranks
		c = new Card("cLuBs", "aCe");
		po(c);
		c = new Card("Diamonds", "Jack");
		po(c);
		c = new Card("hearts", "queen");
		po(c);
		c = new Card("SPADES", "KING");
		po(c);
		c = new Card("SPADES", "Two");
		po(c);

		pl();

		// Random suit names
		/*
		c = new Card("Test", "Ace");
		System.out.println(String.format("Random string suits allowed: %b", !c.toString().contains("Error")));
		
		// Random Rank names
		c = new Card("Spades", "Test");
		System.out.println(String.format("Random string ranks allowed: %b", !c.toString().contains("Error")));
		*/
		pl();

		// Getters
		c = new Card();
		System.out.println(c.getRank());
		System.out.println(c.getRankStr());
		System.out.println(c.getSuit());
		System.out.println(c.getSuitInt());

		pl();
		
		// Card Comparison
		System.out.println("Card Comparisons: ");
		Card card1 = new Card("spades", "ace");
		Card card2 = new Card("spades", "king");
		System.out.println(String.format("Less Test1: %b, Expected: %b, %s", card1.compareTo(card2) < 0, true,
				card1.toString() + " < " + card2.toString()));
		card1 = new Card("clubs", "ace");
		card2 = new Card("hearts", "ace");
		System.out.println(String.format("Less Test2: %b, Expected: %b, %s", card1.compareTo(card2) < 0, true,
				card1.toString() + " < " + card2.toString()));

		card1 = new Card("spades", "ace");
		card2 = new Card("spades", "king");
		System.out.println(String.format("Greater Test1: %b, Expected: %b, %s", card2.compareTo(card1) > 0, true,
				card1.toString() + " > " + card2.toString()));
		card1 = new Card("clubs", "ace");
		card2 = new Card("hearts", "ace");
		System.out.println(String.format("Greater Test2: %b, Expected: %b, %s", card2.compareTo(card1) > 0, true,
				card1.toString() + " > " + card2.toString()));

		boolean error = false;
		try {
			card1.compareTo(null);
		} catch (Throwable t) {
			error = true;
		}
		System.out.println("Compare to Null Test: " + (error ? "pass" : "fail"));
		pl();

		// Equals test
		card1 = new Card();
		card2 = new Card("clubs", "ace");
		System.out.println(String.format("Equals Test1: %b, Expected: %b", card1.equals(card2), true));
		card2 = new Card("spades", "king");
		System.out.println(String.format("Equals Test2: %b, Expected: %b", card1.equals(card2), false));

		// Deck test
		Deck d = new Deck();
		po(d);
		pl();
		
		d.shuffle();
		po(d);
		
		Deck[] hands = d.deal(4, 4);
		for (Deck deck : hands){
			po(deck);
		}
		po(d);
		
		System.out.println(String.format("Deck Test1: %b, Expected: %b", d.deal(16, 4) == null, true));
		Deck one = new Deck();
		Deck two = new Deck();
		two.shuffle();
		two.selectionSort();
		System.out.println(String.format("SelectionSort Test1: %b, Expected: %b", one.equals(two), true));
		two.shuffle();
		two.mergeSort();
		System.out.println(String.format("MergeSort Test1: %b, Expected: %b", one.equals(two), true));
		
		
	}

	// quick method for printing a line
	private static void pl() {
		System.out.println();
	}

	// quick method for printing an object's toString
	private static void po(Object o) {
		System.out.println(o.toString());
	}
}