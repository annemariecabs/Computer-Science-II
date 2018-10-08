public class Deck {
	
	private Card[] cards;
	private int topCard; //index of topCard on deck, last card's index = 0
	
	public Deck() {
		cards = new Card[52];
		
		int i = 0; //index of card at that time in the loop
		for(int s = 0; s < 4; s++, i++)
			for(int r = 1; r < 14; r++, i++)
				cards[i] = new Card(s, r);
		
		topCard = i;
	}
	
	public Deck(boolean sorted) {
		cards = new Card[52];
		
		int i = 0; //index of card at that time in the loop
		for(int s = 0; s < 4; s++, i++)
			for(int r = 1; r < 14; r++, i++)
				cards[i] = new Card(s, r);
		
		if(! sorted)
			this.shuffle();
		
		topCard = cards.length - 1;
	}
	
	public Deck(Card[] hands) {
		cards = hands;
		
		topCard = hands.length - 1;
	}
	
	public Card[] getCards() {
		return cards;
	}
	
	public void shuffle() {

		Card[] temp = new Card[cards.length];
		
		for(int i = 0; i < cards.length; i++) {
			temp[i] = this.pick();
		}
		
		cards = temp;
		
	}
	
	public String toString() {
		
		
	}
	
	public boolean equals(Deck other) {
		Card[] cards2 = other.getCards()
		
		for(int c = 0; c < cards.length; c++) {
			if(! c.equals(other2[c])
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
			else
				i++;
		}
		
		cards = temp;
		topCard--;
		
		return myPick;
		
	}
	
	public void selectionSort() {
		int min = cards[0];
		
		for(int a = 1; a < cards.length; a++) {
			
			if(cards[a] < min)
				min = cards[a];
		}
	}
	
	public void mergeSort() {
		
	}
	
}