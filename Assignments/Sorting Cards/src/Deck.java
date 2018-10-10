/**
 * This class will be used to create Deck objects, which are groups of Cards.
 *
 * @author AnneMarie Caballero
 *
 */

public class Deck {
	
	private Card[] cards;
	private int topCard; //index of topCard on deck, last card's index = 0
	
	/*
	 * Constructs a default Deck object that holds 52 cards that are sorted.
	 */
	public Deck() {
		cards = new Card[52];
		
		int i = 0; //index of card at that time in the loop
		for(int s = 0; s < 4; s++)
			for(int r = 1; r < 14; r++, i++)
				cards[i] = new Card(s, r);
		
		topCard = 51;
	}
	
	/*
	 * Constructs a Deck object that holds 52 cards.
	 * 
	 * @param sorted if true, sorts the Card[], if false, shuffles the Card[]
	 */
	public Deck(boolean sorted) {
		cards = new Card[52];
		
		int i = 0; //index of card at that time in the loop
		for(int s = 0; s < 4; s++)
			for(int r = 1; r < 14; r++, i++) 
				cards[i] = new Card(s, r);
			
				
		topCard = 51;
		
		if(! sorted)
			this.shuffle();
		
		
	}
	
	/*
	 * Constructs a Deck object with a Card[]
	 * 
	 * @param hands sets the array of Card to the passed array
	 */
	public Deck(Card[] hands) {
		cards = hands;
		
		topCard = hands.length - 1;
	}
	
	/*
	 * Returns the value of the Card[] that Deck holds
	 * 
	 * @return an array of cards that is the value of the cards in the Deck
	 */
	public Card[] getCards() {
		return cards;
	}
	
	/*
	 * Returns the index of the top card of the deck
	 * 
	 * @return an integer representing the index of the top card of the deck
	 */
	public int getTopCard() {
		return topCard;
	}
	
	/*
	 * Shuffles the card array in the Deck
	 */
	public void shuffle() {
		
		int originalLength = cards.length;
		Card[] temp = new Card[cards.length];
		
		for(int i = 0; i < originalLength; i++) {
			temp[i] = this.pick();
		}
		
		cards = temp;
		topCard = originalLength - 1;
		
	}
	
	/*
	 * Returns the String value of the Deck object
	 * 
	 * @return a String representation of the Deck. If there is 52 cards, there will be four columns, else
	 * 		   there will be one column.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = "";
		
		if(cards.length == 52) {
			int clubIndex = 0;
			int diamondIndex = 0;
			int heartIndex = 0;
			int spadeIndex = 0;
			
			
			Card[] clubs = new Card[13];
			Card[] diamonds = new Card[13];
			Card[] hearts = new Card[13];
			Card[] spades = new Card[13];
			
			for(int i = 0; i < cards.length; i++) {
				if(cards[i].getSuitInt() == 0) {
					clubs[clubIndex] = cards[i];
					clubIndex++;
				}
				else if(cards[i].getSuitInt() == 1) {
					diamonds[diamondIndex] = cards[i];
					diamondIndex++;
				}
				else if(cards[i].getSuitInt() == 2) {
					hearts[heartIndex] = cards[i];
					heartIndex++;
				}
				else if(cards[i].getSuitInt() == 3) {
					spades[spadeIndex] = cards[i];
					spadeIndex++;
				}
				else {
					System.out.println("Error has occurred");
				}
			}
			
			result = "";
			
			for(int i = 0; i < 13; i++) {
				result += clubs[i].toString() + "\t" + diamonds[i].toString() + "\t"
						+ hearts[i].toString() + "\t" + spades[i].toString() + "\n";
			}
		}
		else {
			for (int i = 0; i < cards.length; i++) {
				result += cards[i].toString() + "\n";
			}
		}
		
		return result;
	}
	
	/*
	 * Checks if this Deck is equal to the other Deck. Order matters in determining equality.
	 * 
	 * @param other the Deck object that this is being checked against
	 * @return true, if this is equal to other; false, if this is not equal to other
	 */
	public boolean equals(Deck other) {
		Card[] cards2 = other.getCards();
		
		if(topCard != other.getTopCard())
			return false;
		
		for(int c = 0; c < cards.length; c++) {
			if(! cards[c].equals(cards2[c]))
					return false;
		}
		
		return true;
		
	}
	
	/*
	 * Deals a Deck 
	 * 
	 * @param numHands an integer that represents the number of hands to be dealt
	 * @param cardsPerHand an integer that represents the card per hand that is dealt
	 * @return null, if there are not enough cards for the deal specified; an array of Decks that represents
	 * 		   the hands dealt
	 */
	public Deck[] deal(int numHands, int cardsPerHand) {
		Deck[] hands = new Deck[numHands];
		
		if(numHands * cardsPerHand > cards.length)
			return null;
		
		for(int i = 0; i < hands.length; i++) {
			Card[] temp = new Card[cardsPerHand];
			
			for(int j = 0; j < temp.length; j++)  {
				temp[j] = this.pick(); 
			}
			
			hands[i] = new Deck(temp); 
		}
		
		return hands;
	}
	
	/*
	 * Returns and removes a random Card from the Deck
	 * 
	 * @return a random Card from the Deck
	 */
	public Card pick() {
		int random = (int) (Math.random() * cards.length);
		
		Card[] temp = new Card[cards.length - 1];
		Card myPick = cards[random];
		
		for(int i = 0, j = 0; i < cards.length; i++, j++) {
			if(i != random)
				temp[j] = cards[i];
			else {
				j--;
			}
		}
		
		cards = temp;
		topCard--;
		
		return myPick;
		
	}
	
	/*
	 * Uses the SelectionSort method to sort the Cards in the Deck
	 */
	public void selectionSort() {
		int min = 0;
		Card temp = null;
		
		for(int a = 0; a < cards.length; a++) {
			
			min = a;
			
			for(int b = a; b < cards.length; b++) {
				
				if(cards[min].compareTo(cards[b]) == 1)
					min = b;
				
			}
			
			temp = cards[a];
			cards[a] = cards[min];
			cards[min] = temp;
		}
	}
	
	/*
	 * Uses the MergeSort method to sort the Cards in the Deck. 
	 * This method uses internal methods that were created using the pseudocode at this address:
	 * https://www.tutorialspoint.com/data_structures_algorithms/merge_sort_algorithm.htm 
	 */
	public void mergeSort() {
		cards = mSort(cards);
		
	}
	
	private static Card[] mSort (Card[] temps) {
		
		if(temps.length == 1)
			return temps;
		
		int half = temps.length/2;
		
		Card[] cards1;
		Card[] cards2;
		
		if(temps.length%2 == 0) {
		
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
			
			cards2[cards2.length - 1] = temps[temps.length - 1];
		}
				
		
		cards1 = mSort(cards1);
		cards2 = mSort(cards2);
		
		return merge(cards1, cards2);
		
	}
	
	
	private static Card[] merge(Card[] cards1, Card[] cards2) {
		
		Card[] cards3 = new Card[cards1.length + cards2.length];
		int cardIndex = 0;;
		
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