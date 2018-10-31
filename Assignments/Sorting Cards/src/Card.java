/**
 * This class will be used to create Card objects. Card objects have two fields: a rank and 
 * a suit. The suit is an String and rank is an integer, but this class has provided methods
 * to retrieve them as both Strings and integers and there are Constructors to create them
 * with the ranks and suits as both ints, int and String, String and int, and both Strings.
 * 
 * This class mainly just provides basic class methods: constructors, getters,
 * toString(), compareTo(), and equals(). This is because this class is primarily being used
 * for the Deck class.
 *
 * @author AnneMarie Caballero
 * @see Deck
 *
 */


public class Card implements Comparable<Card> {

	/**
	 * the suit value of the Card;
	 * could be: "Clubs", "Hearts", "Diamonds", or "Spades"
	 */
	private String suit;
	/**
	 * the rank value of the Card;
	 * assignments of ranks that don't directly match to a number:
	 * 1 = ace, 11 = jack, 12 = queen, 13 = king
	 */
	private int rank;
	
	/**
	 * {@value #CLUBS_INT} the integer value assigned to the clubs suit
	 */
	public static final int CLUBS_INT = 0;
	/**
	 * {@value #DIAMONDS_INT} the integer value assigned to the diamonds suit
	 */
	public static final int DIAMONDS_INT = 1;
	/**
	 * {@value #HEARTS_INT} the integer value assigned to the hearts suit
	 */
	public static final int HEARTS_INT = 2;
	/**
	 * {@value #SPADES_INT} the integer value assigned to the spades suit
	 */
	public static final int SPADES_INT = 3;
	/**
	 * {@value #CLUBS_STR} the String value assigned to the clubs suit
	 */
	public static final String CLUBS_STR = "clubs";
	/**
	 * {@value #DIAMONDS_STR} the String value assigned to the diamonds suit
	 */
	public static final String DIAMONDS_STR = "diamonds";
	/**
	 * {@value #HEARTS_STR} the String value assigned to the hearts suit
	 */
	public static final String HEARTS_STR = "hearts";
	/**
	 * {@value #SPADES_STR} the String value assigned to the spades suit
	 */
	public static final String SPADES_STR = "spades";
	
	/**
	 * {@value #ACE_INT} the integer value assigned to the Ace rank
	 */
	public static final int ACE_INT = 1;
	/**
	 * {@value #TWO_INT} the integer value assigned to the Two rank
	 */
	public static final int TWO_INT = 2;
	/**
	 * {@value #THREE_INT} the integer value assigned to the Three rank
	 */
	public static final int THREE_INT = 3;
	/**
	 * {@value #FOUR_INT} the integer value assigned to the Four rank
	 */
	public static final int FOUR_INT = 4;
	/**
	 * {@value #FIVE_INT} the integer value assigned to the Five rank
	 */
	public static final int FIVE_INT = 5;
	/**
	 * {@value #SIX_INT} the integer value assigned to the Six rank
	 */
	public static final int SIX_INT = 6;
	/**
	 * {@value #SEVEN_INT} the integer value assigned to the Seven rank
	 */
	public static final int SEVEN_INT = 7;
	/**
	 * {@value #EIGHT_INT} the integer value assigned to the Eight rank
	 */
	public static final int EIGHT_INT = 8;
	/**
	 * {@value #NINE_INT} the integer value assigned to the Nine rank
	 */
	public static final int NINE_INT = 9;
	/**
	 * {@value #TEN_INT} the integer value assigned to the Ten rank
	 */
	public static final int TEN_INT = 10;
	/**
	 * {@value #JACK_INT} the integer value assigned to the Jack rank
	 */
	public static final int JACK_INT = 11;
	/**
	 * {@value #QUEEN_INT} the integer value assigned to the Queen rank
	 */
	public static final int QUEEN_INT = 12;
	/**
	 * {@value #KING_INT} the integer value assigned to the King rank
	 */
	public static final int KING_INT = 13;
	/**
	 * {@value #ACE_STR} the String value assigned to the Ace rank (internally)
	 */
	public static final String ACE_STR = "ace";
	/**
	 * {@value #TWO_STR} the String value assigned to the Two rank (internally)
	 */
	public static final String TWO_STR = "two";
	/**
	 * {@value #THREE_STR} the String value assigned to the Three rank (internally)
	 */
	public static final String THREE_STR = "three";
	/**
	 * {@value #FOUR_STR} the String value assigned to the Four rank (internally)
	 */
	public static final String FOUR_STR = "four";
	/**
	 * {@value #FIVE_STR} the String value assigned to the Five rank (internally)
	 */
	public static final String FIVE_STR = "five";
	/**
	 * {@value #SIX_STR} the String value assigned to the Six rank (internally)
	 */
	public static final String SIX_STR = "six";
	/**
	 * {@value #SEVEN_STR} the String value assigned to the Seven rank (internally)
	 */
	public static final String SEVEN_STR = "seven";
	/**
	 * {@value #EIGHT_STR} the String value assigned to the Eight rank (internally)
	 */
	public static final String EIGHT_STR = "eight";
	/**
	 * {@value #NINE_STR} the String value assigned to the Nine rank (internally)
	 */
	public static final String NINE_STR = "nine";
	/**
	 * {@value #TEN_STR} the String value assigned to the Ten rank (internally)
	 */
	public static final String TEN_STR = "ten";
	/**
	 * {@value #JACK_STR} the String value assigned to the Jack rank (internally)
	 */
	public static final String JACK_STR = "jack";
	/**
	 * {@value #QUEEN_STR} the String value assigned to the Queen rank (internally)
	 */
	public static final String QUEEN_STR = "queen";
	/**
	 * {@value #KING_STR} the String value assigned to the King rank (internally)
	 */
	public static final String KING_STR = "king";
	
	/**
	 * {@value #ERROR_STR} the String value returned by a method if there is an error
	 */
	public static final String ERROR_STR = "Error";
	
	/**
	 * {@value #ERROR_INT} the integer value returned by some methods if there is an error
	 */
	public static final int ERROR_INT = -1;
	
	
	/**
	 * Constructs a default card (the Ace of Clubs)
	 */
	public Card () {
		suit = CLUBS_STR;
		rank = ACE_INT;
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
		if(getRankStr().equals(ERROR_STR))
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
		if(getSuitInt() == ERROR_INT)
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
		if(getSuitInt() == ERROR_INT)
			throw new IllegalArgumentException("Invalid input for suit: " + s + " is not a possible suit value");
		
		rank = r;
		if(getRankStr().equals(ERROR_STR))
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
	//Uses string literals because this is used to get rank formatted properly
	//and so first letter should be capitalized
	public String getRankStr() {
		switch(rank) {
		case ACE_INT:
			return "Ace";
		case TWO_INT:
			return "Two";
		case THREE_INT:
			return "Three";
		case FOUR_INT:
			return "Four";
		case FIVE_INT:
			return "Five";
		case SIX_INT:
			return "Six";
		case SEVEN_INT:
			return "Seven";
		case EIGHT_INT:
			return "Eight";
		case NINE_INT:
			return "Nine";
		case TEN_INT:
			return "Ten";
		case JACK_INT:
			return "Jack";
		case QUEEN_INT:
			return "Queen";
		case KING_INT:
			return "King";
		default:
			return ERROR_STR;
		}
		
	}
	
	/**
	 * Returns the suit (as an int)
	 * 
	 * @return the suit as an integer
	 */
	public int getSuitInt()  {
		switch(suit.toLowerCase()) {
		case CLUBS_STR:
			return CLUBS_INT;
		case DIAMONDS_STR:
			return DIAMONDS_INT;
		case HEARTS_STR:
			return HEARTS_INT;
		case SPADES_STR:
			return SPADES_INT;
		default:
			return ERROR_INT; //suit should always be 0-3
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
		case CLUBS_INT:
			return CLUBS_STR;
		case DIAMONDS_INT:
			return DIAMONDS_STR;
		case HEARTS_INT:
			return HEARTS_STR;
		case SPADES_INT:
			return SPADES_STR;
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
	private static int getRankInt(String r) throws IllegalArgumentException {

		switch(r.toLowerCase()) {
		case ACE_STR:
			return ACE_INT;
		case TWO_STR:
			return TWO_INT;
		case THREE_STR:
			return THREE_INT;
		case FOUR_STR:
			return FOUR_INT;
		case FIVE_STR:
			return FIVE_INT;
		case SIX_STR:
			return SIX_INT;
		case SEVEN_STR:
			return SEVEN_INT;
		case EIGHT_STR:
			return EIGHT_INT;
		case NINE_STR:
			return NINE_INT;
		case TEN_STR:
			return TEN_INT;
		case JACK_STR:
			return JACK_INT;
		case QUEEN_STR:
			return QUEEN_INT;
		case KING_STR:
			return KING_INT;
		default:
			throw new IllegalArgumentException("Invalid input for rank: " + r + " is not a possible value of rank");
		}
	}
}