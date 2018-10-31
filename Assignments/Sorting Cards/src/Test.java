import java.util.Arrays; //is used but in a commented out part

/*
* A test class for Card, Deck, and CardComparator
* 
* @author AnneMarie Caballero
*/

public class Test {
	
	public static void main(String[] args) {
		//testing Card Constructors & toString 
				/*
				Card card = new Card();
				System.out.println(card.toString());
				
				card = new Card(1, 4);
				System.out.println(card.toString());
				
				card = new Card("Spades", 4);
				System.out.println(card.toString());
				
				card = new Card(1, "Four");
				System.out.println(card.toString());
			
				card = new Card("Spades", "Four");
				System.out.println(card.toString());
				
				
				//testing constructors with out of bounds
				//all tests returned exceptions 
				
				//card = new Card(-1, 4);
				//card = new Card(4, 4);
				//card = new Card(3, 14);
				//card = new Card(3, 0);
				
				
		
				card = new Card(1, 3);
				//testing getters and similar methods
				System.out.println(card.getRank());
				System.out.println(card.getSuit());
				System.out.println(card.getRankStr());
				System.out.println(card.getSuitInt());
				
				
				
				card = new Card(3, 4);
				
				//testing compareTo and equals
				Card card2 = new Card("Spades", "Four");
				System.out.println(card.equals(card2));
				
				card2 = new Card(3, 4);
				System.out.println(card.equals(card2));
				System.out.println(card.compareTo(card2));
				
				card2 = new Card(2, 4);
				//below line should return 1
				System.out.println(card.compareTo(card2));
				
				card = new Card(2, 4);
				card2 = new Card(3, 4);
				//below line should return -1
				System.out.println(card.compareTo(card2));
				
				card2 = new Card(2, 5);
				//below line should return -1
				System.out.println(card.compareTo(card2));
				
				card2 = new Card(2, 3);
				//below line should return 1
				System.out.println(card.compareTo(card2));
				
				
				//testing CardComparator
				
				Card[] cards = {new Card(1, 2), new Card(3, 4), new Card(0, 13), new Card(3, 12), new Card(2, 12)};
				System.out.println(Arrays.toString(cards));
				
				Arrays.sort(cards, new CardComparator(true));
				System.out.println(Arrays.toString(cards));
				
				Arrays.sort(cards, new CardComparator(false));
				System.out.println(Arrays.toString(cards));
				
				Card[] cards2 = {new Card(1, 2), new Card(2, 2), new Card(3, 2), new Card(0, 2)};
				System.out.println(Arrays.toString(cards2));
				
				Arrays.sort(cards2, new CardComparator(true));
				System.out.println(Arrays.toString(cards2));
				
				Arrays.sort(cards2, new CardComparator(false));
				System.out.println(Arrays.toString(cards2));
				*/
	
				
				
		/*
				//testing Deck constructors
				Deck deck = new Deck();
				System.out.println(deck.toString());
				
				deck = new Deck(true);
				System.out.println(deck.toString());
				
				deck = new Deck(false);
				System.out.println(deck.toString());
				
				Card card = deck.pick();
				System.out.println(card.toString());
				
				System.out.println("\n" + deck.toString());
				
				card = deck.pick();
				System.out.println(card.toString());
				
				System.out.println("\n" + deck.toString());
			
				
				deck.shuffle();
				System.out.println(deck.toString());

				deck = new Deck();
				deck.shuffle();
				System.out.println(deck.toString());
				
				Deck deck2 = new Deck();
				System.out.println(deck.equals(deck2)); 
			
				
				deck = new Deck(false);
				System.out.println(deck);
				
				deck.selectionSort(); 
				System.out.println(deck);
				
				
				deck.pick();
				deck.shuffle();
				deck.selectionSort(); //works for less than 52-card decks
				System.out.println(deck);
				
				System.out.println("PARTITION + \n");
				
				deck = new Deck(false);
				System.out.println(deck);
				
				deck.mergeSort(); //MergeSort works
				System.out.println(deck);
				
				deck.pick();
				deck.shuffle();
				deck.mergeSort(); //MergeSort works for less than 52-card decks
				System.out.println(deck + "\n");
				
				deck.pick();
				deck.pick();
				deck.shuffle();
				System.out.println(deck + "\n");
				
				deck.mergeSort();
				System.out.println(deck + "\n");
				
				
		*/
		/*
				Deck deck = new Deck();
				
				Deck[] decks = deck.deal(15, 4); 
				System.out.println(decks);//should print null - update: it does
				
				System.out.println(deck);
				
				deck.selectionSort();
				decks = deck.deal(12, 4);
				System.out.println(Arrays.toString(decks));
				
				System.out.print(deck.toString());
				
				deck = new Deck();
				deck.shuffle();
				decks = deck.deal(4, 12);
				System.out.println(Arrays.toString(decks));
				
				System.out.println(deck.toString());
				
				Deck[] decks2 = deck.deal(1, 4);
				System.out.println(Arrays.toString(decks2));
				
				deck = new Deck();
				decks2 = deck.deal(4, 13);
				System.out.println(Arrays.toString(decks));
				System.out.println(deck.toString());
			*/
			/*
			Deck deck = new Deck();
			int topCard = deck.getTopCard();
			for(int i = 0; i <= topCard; i++) {
				deck.pick();
			}
			
			System.out.println(deck.pick());
			deck.mergeSort();
			deck.selectionSort();
			deck.deal(1, 1);
			deck.shuffle();
			System.out.println(deck);
			*/
		
	}
	
}