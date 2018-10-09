public class Deck {
	
	private Card[] cards;
	private int topCard; //index of topCard on deck, last card's index = 0
	
	public Deck() {
		cards = new Card[52];
		
		int i = 0; //index of card at that time in the loop
		for(int s = 0; s < 4; s++)
			for(int r = 1; r < 14; r++, i++)
				cards[i] = new Card(s, r);
		
		topCard = 51;
	}
	
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
	
	public Deck(Card[] hands) {
		cards = hands;
		
		topCard = hands.length - 1;
	}
	
	public Card[] getCards() {
		return cards;
	}
	
	public int getTopCard() {
		return topCard;
	}
	
	public void shuffle() {
		
		int originalLength = cards.length;
		Card[] temp = new Card[cards.length];
		
		for(int i = 0; i < originalLength; i++) {
			temp[i] = this.pick();
		}
		
		cards = temp;
		topCard = originalLength - 1;
		
	}
	
	
	//TODO: FIX
	public String toString() {
		String result = "";
		
		if(cards.length == 52) {
			
			
		}
		else {
			for (int i = 0; i < cards.length; i++) {
				result = cards[i].toString() + "\n";
			}
		}
		
		return result;
	}
	
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
	
	public Deck[] deal(int numHands, int cardsPerHand) {
		Deck[] hands = new Deck[numHands];
		Card[] temp = new Card[cardsPerHand];
		
		for(int i = 0; i < hands.length; i++) {
			for(int j = 0; j < temp.length; j++) 
				temp[j] = this.pick(); //TODO: this should be removed from the deck right?
			
			hands[i] = new Deck(temp); 
		}
		
		return hands;
	}
	
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
	
	public void mergeSort() {
		cards = merge(cards);
		
	}
	
	private static Card[] merge (Card[] temps) {
		int half = temps.length/2;
		
		
		
		return temps;
		
	}
	
}