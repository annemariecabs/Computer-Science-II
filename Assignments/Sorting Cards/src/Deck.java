/**
 * This class will be used to create Deck objects, which are groups of Cards.
 * Deck has two fields: a Card[] to hold its current Cards, and a topCard
 * that has the index of the top card of the Card[]. topCard is necessary because
 * some of the Deck's functionalities require the removal of Cards, and topCard 
 * prevents those methods from needing to reinstantiate the Deck.
 * 
 * The main functionalities of Deck are shuffle, deal, pick, selectionSort, 
 * and mergeSort. Shuffle shuffles the current deck. Deal deals a certain amount 
 * of hands from the Deck, each their own individual Deck. Pick picks a random card 
 * from the Deck, removes it and returns it. selectionSort uses a backwards SelectionSort
 * (the algorithm used swaps to the first position not the last and continues by having 
 * the sorted section of the deck during the sort be in the front of the deck which is atypical)
 * algorithm to sort the deck. MergeSort uses a typical MergeSort algorithm to sort the deck.
 * The class also has class-typical functions like equals, getters,
 * and constructors. There are 3 constructors, one that creates a sorted 52-card deck,
 * one that can create a sorted or unsorted 52-card deck, and one that creates a deck
 * based on the Card[] provided.
 * 
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
	 * Holds the sorted Card array in the mergeSort methods
	 */
	public static Card[] sorted;
	
	/**
	 * Uses the MergeSort method to sort the Cards in the Deck. 
	 * This method uses internal methods that were created with a lot of help
	 * from the MergeSort example on pg. 394 in Java Methods book by Maria and Gary Litvin
	 */
	public void mergeSort() {
		sorted = new Card[topCard + 1];
		mSort(cards, 0, topCard);
		
	}
	
	/**
	 * 
	 * The recursive sort part of MergeSort. This method was based heavily on the one from
	 * page 394 of the Java Methods book by Maria and Gary Litvin.
	 * 
	 * @param temp array that references cards and thus changes cards when it is changed
	 * @param from first index of given array
	 * @param to last index of given arry
	 */
	private static void mSort (Card[] temp, int from, int to) {
		
		if(to - from < 2) {
			if(to > from && temp[to].compareTo(temp[from]) < 0) {
				Card t = temp[from];
				temp[from] = temp[to];
				temp[to] = t;
				
			}
		}
		else {
			int middle = (from + to)/2;
			mSort(temp, from, middle);
			mSort(temp, middle + 1, to);
			merge(temp, from, middle, to);
			
		}
	}
	
	/**
	 * 
	 * Joins both sides of the array sent in back together. This method was based heavily on 
	 * the one from page 394 of the Java Methods book by Maria and Gary Litvin.
	 * 
	 * @param temp array that references cards and thus changes cards when it is changed
	 * @param from first index of the given array
	 * @param middle middle index of the given array
	 * @param to last index of the given array
	 */
	private static void merge(Card[] temp, int from, int middle, int to) {
		
		int i = from, j = middle + 1, k = from;
		
		//loop that runs while both halves have unmerged items
		while(i <= middle && j<= to) {
			if(temp[i].compareTo(temp[j]) < 0) {
				sorted[k] = temp[i];
				i++;
			}
			else {
				sorted[k] = temp[j];
				j++;
			}
			k++;
		}

		while(i <= middle) {
			sorted[k] = temp[i];
			i++;
			k++;
		}
		
		while(j <= to) {
			sorted[k] = temp[j];
			j++;
			k++;
		}
		
		for(k = from; k <= to; k++) {
			temp[k] = sorted[k];
		}
		
	}
	
}