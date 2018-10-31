/**
 * This class will be used to create Card objects.
 *
 * @author AnneMarie Caballero
 *
 */


public class Card implements Comparable<Card> {

	/*
	 * the suit value of the Card
	 * could be: "Clubs", "Hearts", "Diamonds", or "Spades"
	 */
	private String suit;
	/*
	 * the rank value of the Card
	 * assignments of ranks that don't directly match to a number:
	 * 1 = ace, 11 = jack, 12 = queen, 13 = king
	 */
	private int rank;
	
	/*
	 * {@value #CLUBS_INT} the integer value assigned to the clubs suit
	 */
	public static final int CLUBS_INT = 0;
	/*
	 * {@value #DIAMONDS_INT} the integer value assigned to the diamonds suit
	 */
	public static final int DIAMONDS_INT = 1;
	/*
	 * {@value #HEARTS_INT} the integer value assigned to the hearts suit
	 */
	public static final int HEARTS_INT = 2;
	/*
	 * {@value #SPADES_INT} the integer value assigned to the spades suit
	 */
	public static final int SPADES_INT = 3;

	/**
	 * Constructs a default card (the Ace of Clubs)
	 */
	public Card () {
		suit = "Clubs";
		rank = 1;
	}

	/**
	 * Constructs a Card using two integers
	 * 
	 * @param s the value of suit in integer form
	 * @param r the value of rank in integer form
	 * @throws IllegalArgumentException if rank or suit values are invalid
	 */
	public Card(int s, int r) {
		suit = getSuitStr(s);
		
		rank = r;
		if(getRankStr().equals("Error"))
			throw new IllegalArgumentException("Invalid input for rank: rank must be an integer between 1 & 13 inclusive");
	}
	
	/**
	 * Constructs a Card using two Strings
	 * 
	 * @param s the value of suit in String form
	 * @param r the value of rank in String form
	 * @throws IllegalArgumentException if rank or suit values are invalid
	 */
	public Card(String s, String r) {
		suit = s.toLowerCase();
		if(getSuitInt() == -1)
			throw new IllegalArgumentException("Invalid input for suit: " + s + " is not a possible suit value");
		
		rank = getRankInt(r);
	}
	
	/**
	 * Constructs a Card using a String, for suit, and integer, for rank.
	 * 
	 * @param s the value of suit in String form
	 * @param r the value of rank in int form
	 * @throws IllegalArgumentException if rank or suit values are invalid
	 */
	public Card(String s, int r) {
		suit = s.toLowerCase();
		if(getSuitInt() == -1)
			throw new IllegalArgumentException("Invalid input for suit: " + s + " is not a possible suit value");
		
		rank = r;
		if(getRankStr().equals("Error"))
			throw new IllegalArgumentException("Invalid input for rank: rank must be an integer between 1 & 13 inclusive");
	}
	
	/**
	 * Constructs a Card using a integer, for suit, and String, for rank.
	 * 
	 * @param s the value of suit in int form
	 * @param r the value of rank in String form
	 * @throws IllegalArgumentException if rank or suit values are invalid
	 */
	public Card(int s, String r) {
		suit = getSuitStr(s);
		rank = getRankInt(r);
	}
	
	/**
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

	/**
	 * Returns the rank of the card (as an integer)
	 * 
	 * @return an integer representing the Card's rank 
	 */
	public int getRank() {
		return rank;
	}
	
	
	/**
	 * Returns a String object representing the Card's value e.g. "Nine of Spades".
	 * 
	 * @return the value of the Card as a String
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		try {
			return getRankStr() + " of " + getSuit();
		} catch (IllegalArgumentException e) {
			return "Not a valid card: " + e.getMessage();
		}
	}
	
	/**
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
			return "Error";
		}
		
	}
	
	/**
	 * Returns the suit (as an int)
	 * 
	 * @return the suit as an integer
	 */
	public int getSuitInt()  {
		switch(suit.toLowerCase()) {
		case "clubs":
			return CLUBS_INT;
		case "diamonds":
			return DIAMONDS_INT;
		case "hearts":
			return HEARTS_INT;
		case "spades":
			return SPADES_INT;
		default:
			return -1; //suit should always be 0-3
		}
	}
	
	/**
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
	
	/**
	 * Checks if two Cards are equal
	 * 
	 * @param other the Card that this Card is being checked for equality with
	 * @return true if the Cards are equal, false if the Cards are unequal
	 */
	public boolean equals(Object other) {
		Card oth = (Card) other;
		
		if(this.compareTo(oth) == 0)
			return true;
		else
			return false;
	}

	//internal method to reduce redundancy in constructors
	//throws an IllegalArgument Exception if s doesn't match an acceptable value of suit
	/**
	 * 
	 * Returns the String value of a suit given as an integer. Used internally to reduce
	 * redundancy of constructors where suit is given as an integer.
	 * 
	 * @param s the integer version of suit that will be used to find the String equivalent
	 * @return the String equivalent of integer s
	 * @throws IllegalArgumentException if the int given doesn't have a String equivalent for suit
	 */
	private static String getSuitStr(int s) throws IllegalArgumentException {

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
			throw new IllegalArgumentException("Invalid input for suit: should be between 0 & 3 inclusive");
		}
	}

	//internal method to reduce redundancy in constructors
	//throws an IllegalArgument Exception if r doesn't match an acceptable value of rank
	/**
	 * 
	 * Returns the integer value of a given String rank. Is used internally to 
	 * get the equivalent integer value of a rank given as String to reduce redundancy
	 * in constructors where rank is given as a String.
	 * 
	 * @param r the String value of the rank that it gets the int for
	 * @return the equivalent integer value to the rank held by String r 
	 * @throws IllegalArgumentException if String r doesn't have an equivalent integer rank
	 */
	private static int getRankInt(String r) throws IllegalArgumentException{

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
			throw new IllegalArgumentException("Invalid input for rank: " + r + " is not a possible value of rank");
		}
	}
}