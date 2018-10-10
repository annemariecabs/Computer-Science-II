/**
 * This class will be used to create Card objects.
 *
 * @author AnneMarie Caballero
 *
 */


public class Card implements Comparable<Card> {

	private String suit;
	//1 = ace, 11 = jack, 12 = queen, 13 = king
	private int rank;

	/*
	 * Constructs a default card (the Ace of Clubs)
	 * 
	 */
	public Card () {
		suit = "Clubs";
		rank = 1;
	}

	/*
	 * Constructs a Card using two integers
	 * 
	 * @param s the value of suit in integer form
	 * @param r the value of rank in integer form
	 */
	public Card(int s, int r) {
		suit = getSuitStr(s);
		rank = r;

	}
	
	/*
	 * Constructs a Card using two Strings
	 * 
	 * @param s the value of suit in String form
	 * @param r the value of rank in String form
	 */
	public Card(String s, String r) {
		suit = s.toLowerCase();
		rank = getRankInt(r);

	}
	
	/*
	 * Constructs a Card using a String, for suit, and integer, for rank.
	 * 
	 * @param s the value of suit in String form
	 * @param r the value of rank in int form
	 */
	public Card(String s, int r) {
		suit = s.toLowerCase();
		rank = r;
	}
	
	/*
	 * Constructs a Card using a integer, for suit, and String, for rank.
	 * 
	 * @param s the value of suit in int form
	 * @param r the value of rank in String form
	 */
	public Card(int s, String r) {
		suit = getSuitStr(s);
		rank = getRankInt(r);
	}
	
	/*
	 * Returns the suit of a Card (as a String)
	 * 
	 * @return the value of Suit as a String
	 */
	public String getSuit() {
		//line below changes suit from all lower case to have a capital
		//first letter
		suit = suit.substring(0, 1).toUpperCase() + suit.substring(1);
		return suit;
	}

	/*
	 * Returns the rank of the card (as an integer)
	 * 
	 * @return an integer representing the Card's rank 
	 */
	public int getRank() {
		return rank;
	}
	
	/*
	 * Returns a String object representing the Card's value e.g. "Nine of Spades".
	 * 
	 * @return the value of the Card as a String
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getRankStr() + " of " + getSuit();
	}
	
	/*
	 * Returns the rank (as a String)
	 * 
	 * @return the rank as a String
	 */
	public String getRankStr() {
		switch(rank) {
		case 1:
			return "Ace";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		case 10:
			return "Ten";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		default:
			return "Error"; //means error has occurred
		}
		
	}
	
	/*
	 * Returns the suit (as an int)
	 * 
	 * @return the suit as an integer
	 */
	public int getSuitInt() {
		switch(suit.toLowerCase()) {
		case "clubs":
			return 0;
		case "diamonds":
			return 1;
		case "hearts":
			return 2;
		case "spades":
			return 3;
		default:
			return -1; //suit should always be 0-3
		}
	}
	
	/*
	 * 
	 * Compares two Card objects using both suit and rank
	 * 
	 * @param other the Card that this Card is being compared to.
	 * @return 1 if the Card is greater than other, -1 if this is less than other
	 *		   and 0 if the two Cards being compared are equal.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Card other) {
		if(this.getSuitInt() != other.getSuitInt()) {
			if(this.getSuitInt() > other.getSuitInt())
				return 1;
			else
				return -1;
			
		}
		else if(rank != other.getRank()) {
			if(rank > other.getRank())
				return 1;
			else
				return -1;
			
		}
		else
			return 0;
			
	}
	
	/*
	 * Checks if two Cards are equal
	 * 
	 * @param other the Card that this Card is being checked for equality with
	 * @return true if the Cards are equal, false if the Cards are unequal
	 */
	public boolean equals(Card other) {
		if(this.compareTo(other) == 0)
			return true;
		else
			return false;
	}

	//internal method to reduce redundancy in constructors
	private static String getSuitStr(int s) {

		switch(s) {
		case 0:
			return "Clubs";
		case 1:
			return "Diamonds";
		case 2:
			return "Hearts";
		case 3:
			return "Spades";
		default:
			return "Error"; //suit should always be 0-3
		}
	}

	//internal method to reduce redundancy in constructors
	private static int getRankInt(String r) {

		switch(r.toLowerCase()) {
		case "ace":
			return 1;
		case "two":
			return 2;
		case "three":
			return 3;
		case "four":
			return 4;
		case "five":
			return 5;
		case "six":
			return 6;
		case "seven":
			return 7;
		case "eight":
			return 8;
		case "nine":
			return 9;
		case "ten":
			return 10;
		case "jack":
			return 11;
		case "queen":
			return 12;
		case "king":
			return 13;
		default:
			return -1; //means error has occurred
		}
	}
}