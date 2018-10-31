/**
 * This class will be used to create Deck objects, which are groups of Cards.
 *
 * @author AnneMarie Caballero
 *
 */

public class Deck {
	
	/**
	 * a card array that holds all of the cards in Deck
	 */
	private Card[] cards;
	/**
	 * the index of the top card of the deck;
	 * for a full deck, it would be 51
	 */
	private int topCard; //index of topCard on deck, last card's index = 0
	
	/**
	 * {@value #MIN_SUIT_INT} represents minimum integer value of suit
	 */
	public static final int MIN_SUIT_INT = 0;
	/**
	 * {@value #MIN_RANK_INT} represents minimum integer value of rank
	 */
	public static final int MIN_RANK_INT = 1;
	/**
	 * {@value #MAX_SUIT_INT} represents maximum integer value of suit
	 */
	public static final int MAX_SUIT_INT = 3;
	/**
	 * {@value #MAX_RANK_INT} represents maximum integer value of rank
	 */
	public static final int MAX_RANK_INT = 13;
	/**
	 * {@value #NUM_CARDS_FULL_DECK} represents number of cards in a full deck
	 */
	public static final int NUM_CARDS_FULL_DECK = 52;
	/**
	 * {@value #NUM_CARDS_SUIT} represents number of cards in a suit
	 */
	public static final int NUM_CARDS_SUIT = 13;
	
	/**
	 * Constructs a default Deck object that holds 52 cards that are sorted.
	 */
	public Deck() {
		cards = new Card[NUM_CARDS_FULL_DECK];
		
		int i = 0; //index of card at that time in the loop
		for(int s = MIN_SUIT_INT; s < MAX_SUIT_INT + 1; s++)
			for(int r = MIN_RANK_INT; r < MAX_RANK_INT + 1; r++, i++)
				cards[i] = new Card(s, r);
		
		topCard = NUM_CARDS_FULL_DECK - 1;
	}
	
	/**
	 * Constructs a Deck object that holds 52 cards.
	 * 
	 * @param sorted if true, sorts the Card[], if false, shuffles the Card[]
	 */
	public Deck(boolean sorted) {
		this(); //reduces redundancy by calling no-args Deck constructor
		
		if(! sorted)
			this.shuffle();
		
		
	}
	
	/**
	 * Constructs a Deck object with a Card[]
	 * 
	 * @param hands sets the array of Card to the passed array
	 */
	public Deck(Card[] hands) {
		cards = hands;
		
		topCard = hands.length - 1;
	}
	
	/**
	 * Returns the value of the Card[] that Deck holds
	 * 
	 * @return an array of cards that is the value of the cards in the Deck
	 */
	public Card[] getCards() {
		return cards;
	}
	
	/**
	 * Returns the index of the top card of the deck
	 * 
	 * @return an integer representing the index of the top card of the deck
	 */
	public int getTopCard() {
		return topCard;
	}
	
	/**
	 * Shuffles the card array in the Deck
	 */
	public void shuffle() {
		
		int random;
		Card temp;
		
		for(int i = 0; i <= topCard; i++) {
			random = (int) (Math.random() * (topCard + 1));  // random will be a random number in range [0, topCard]
			temp = cards[i];
			cards[i] = this.cards[random];
			cards[random] = temp;
		}
		
	}
	
	/**
	 * Returns the String value of the Deck object
	 * 
	 * @return a String representation of the Deck. If there is 52 cards, there will be four columns, else
	 * 		   there will be one column.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = "";
		
		if(topCard + 1 == NUM_CARDS_FULL_DECK) {
			int clubIndex = 0;
			int diamondIndex = 0;
			int heartIndex = 0;
			int spadeIndex = 0;
			
			
			Card[] clubs = new Card[NUM_CARDS_SUIT];
			Card[] diamonds = new Card[NUM_CARDS_SUIT];
			Card[] hearts = new Card[NUM_CARDS_SUIT];
			Card[] spades = new Card[NUM_CARDS_SUIT];
			
			for(int i = 0; i <= topCard; i++) {
				switch(cards[i].getSuitInt()) {
				case Card.CLUBS_INT:
					clubs[clubIndex] = cards[i];
					clubIndex++;
					break;
				case Card.DIAMONDS_INT:
					diamonds[diamondIndex] = cards[i];
					diamondIndex++;
					break;
				case Card.HEARTS_INT:
					hearts[heartIndex] = cards[i];
					heartIndex++;
					break;
				case Card.SPADES_INT:
					spades[spadeIndex] = cards[i];
					spadeIndex++;
					break;
				default:
					System.out.println("Error has occurred");
				}
			}
			
			result = "";
			
			//the irregularities like adding two tabs instead of one or adding a space
			//after the diamonds column are to counter the different lengths of the Card names
			//due to their suit names of different length, which cause the columns to look uneven
			for(int i = 0; i < NUM_CARDS_SUIT; i++) {
				result += clubs[i].toString() + "\t\t" + diamonds[i].toString() + " " + "\t"
						+ hearts[i].toString() + "\t\t" + spades[i].toString() + "\n";
			}
		}
		else {
			for (int i = 0; i <= topCard; i++) {
				result += cards[i].toString() + "\n";
			}
		}
		
		return result;
	}
	
	/**
	 * Checks if this Deck is equal to the other Deck. Order matters in determining equality.
	 * 
	 * @param other the Deck object that this is being checked against
	 * @return true, if this is equal to other; false, if this is not equal to other
	 */
	public boolean equals(Object other) {
		Deck oth = (Deck) other;
		
		Card[] cards2 = oth.getCards();
		
		if(topCard != oth.getTopCard())
			return false;
		
		for(int c = 0; c <= topCard; c++) {
			if(! cards[c].equals(cards2[c]))
					return false;
		}
		
		return true;
		
	}
	
	/**
	 * Deals a Deck 
	 * 
	 * @param numHands an integer that represents the number of hands to be dealt
	 * @param cardsPerHand an integer that represents the card per hand that is dealt
	 * @return null, if there are not enough cards for the deal specified; an array of Decks that represents
	 * 		   the hands dealt
	 */
	public Deck[] deal(int numHands, int cardsPerHand) {
		Deck[] hands = new Deck[numHands];
		
		if(numHands * cardsPerHand > topCard + 1)
			return null;
		
		for(int i = 0; i < hands.length; i++) {
			Card[] temp = new Card[cardsPerHand];
			
			for(int j = 0; j < temp.length; j++)  {
				temp[j] = this.cards[topCard];
				this.cards[topCard] = null;
				topCard--;
			}
			
			hands[i] = new Deck(temp); 
		}
		
		return hands;
	}
	
	/**
	 * Returns and removes a random Card from the Deck
	 * 
	 * @return a random Card from the Deck
	 */
	public Card pick() {
		if(topCard == -1)
			return null;
		
		int random = (int) (Math.random() * topCard);
		
		Card myPick = cards[random];
		
		for(int i = random; i < topCard; i++) {
			cards[i] = cards[i + 1];
		}
		
		topCard--;
		
		return myPick;
		
	}
	
	/**
	 * Uses the SelectionSort method to sort the Cards in the Deck
	 */
	public void selectionSort() {
		int min = 0;
		Card temp = null;
		
		for(int a = 0; a <= topCard; a++) {
			
			min = a;
			
			for(int b = a; b <= topCard; b++) {
				
				if(cards[min].compareTo(cards[b]) == 1)
					min = b;
				
			}
			
			temp = cards[a];
			cards[a] = cards[min];
			cards[min] = temp;
		}
	}
	
	/**
	 * Uses the MergeSort method to sort the Cards in the Deck. 
	 * This method uses internal methods that were created using the pseudocode at this address:
	 * https://www.tutorialspoint.com/data_structures_algorithms/merge_sort_algorithm.htm 
	 * and with help from MergeSort example on pg. 394 in Java Methods book by Maria and Gary Litvin
	 */
	public void mergeSort() {
		cards = mSort(cards, topCard + 1);
		
	}
	
	private static Card[] mSort (Card[] temps, int length) {
		
		if(length == 1)
			return temps;
		
		int half = length/2;
		
		Card[] cards1;
		Card[] cards2;
		
		if(length%2 == 0) {
		
			cards1 = new Card[half];
			cards2 = new Card[half];
			
			for(int i = 0; i < half; i++) {
				cards1[i] = temps[i];
				cards2[i] = temps[i + half];
			}
		}
		
		else {
		
			cards1 = new Card[half];
			cards2 = new Card[half + 1];
			
			for(int i = 0; i < half; i++) {
				cards1[i] = temps[i];
				cards2[i] = temps[i + half];
			}
			
			cards2[cards2.length - 1] = temps[length - 1];
		}
				
		
		cards1 = mSort(cards1, cards1.length);
		cards2 = mSort(cards2, cards2.length);
		
		return merge(cards1, cards2);
		
	}
	
	
	private static Card[] merge(Card[] cards1, Card[] cards2) {
		
		Card[] cards3 = new Card[cards1.length + cards2.length];
		int cardIndex = 0;
		
		while(cards1.length > 0 && cards2.length > 0) {
			if(cards1[0].compareTo(cards2[0]) == 1) {
				cards3[cardIndex] = cards2[0];
				cardIndex++;
				Card[] temps = new Card[cards2.length - 1];
				
				if(cards2.length != 1) {
					for(int i = 0; i < temps.length; i++)
						temps[i] = cards2[i + 1];
					
					cards2 = temps;
				}
				
				else 
					cards2 = new Card[0];
				
			}
			else {
				cards3[cardIndex] = cards1[0];
				cardIndex++;
				Card[] temps = new Card[cards1.length - 1];
				
				if(cards1.length != 1) {
					for(int i = 0; i < temps.length; i++)
						temps[i] = cards1[i + 1];
					
					cards1 = temps;
				}
				else 
					cards1 = new Card[0];
				
			}
		}
		
		while(cards1.length > 0) {
			cards3[cardIndex] = cards1[0];
			cardIndex++;
			
			Card[] temps = new Card[cards1.length - 1];
				
			if(cards1.length != 1) {
				for(int i = 0; i < temps.length; i++)
					temps[i] = cards1[i + 1];
				
				cards1 = temps;
			}
			else
				cards1 = new Card[0];
				
		}
		
		while(cards2.length > 0) {
			cards3[cardIndex] = cards2[0];
			cardIndex++;
			
			Card[] temps = new Card[cards2.length - 1];
			
			if(cards2.length != 1) {
				for(int i = 0; i < temps.length; i++)
					temps[i] = cards2[i + 1];
				
				cards2 = temps;
			}
			else
				cards2 = new Card[0];
			
		}
		
		return cards3;

		
	}
	
}