import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program uses a graphical interface of Board, the superclass, to 
 * display different TicTacToe combinations. Whether these combinations are
 * winners or losers is evaluated using a lookup table of all combinations 
 * of TicTacToe Strings whose winning status is stored at the corresponding
 * index produced by the myHashCode method created for this class. The
 * hash code used for this class should not produce any collisions.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */
public class TicTacToeHashCode extends Board {

	/**
	 * A boolean array where a TicTacToe String's hash code directly corresponds
	 * to a boolean value (if true, the TicTacToe string is a winner, and if false, 
	 * a loser)
	 */
	private boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	/**
	 * A lookup table with powers of three where the each spot holds three ^ index
	 */
	private static final int[] POWERS_OF_THREE = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683};

	/**
	 * The integer used as a parameter for System.exit() as a result of an error
	 */
	private static final int ERROR_CODE = 1;

	/**
	 * The delay between each display of a TicTacToe String
	 */
	private static final int DELAY = 4000;

	/**
	 * A char holding a space
	 */
	private static final char SPACE = ' ';

	/**
	 * A char holding an x
	 */
	private static final char X = 'x';

	/**
	 * A char holding an o
	 */
	private static final char O = 'o';

	/**
	 * The name of the file containing the winning TicTacToe Strings
	 */
	private static final String WINNER_FILE = "TicTacToeWinners.txt";

	/**
	 * The name of the file holding the TicTacToe tests
	 */
	private static final String TEST_FILE = "TTT_Tests.txt";

	/**
	 * The title of the board
	 */
	private static final String BOARD_TITLE = "Tic Tac Toe";

	/**
	 * The message if the file with the winning strings is not found
	 */
	private static final String WINNER_FILE_NOT_FOUND = "The file with the winning TicTacToe strings is not available, which means the program must end";

	/**
	 * The message if the file with the test strings is not found
	 */
	private static final String TEST_FILE_NOT_FOUND = "The file with the TicTacToe strings to test is not available, which means the program must end";

	/**
	 * The message if a collision occurs
	 */
	private static final String COLLISION_OCCURRED = "A collision has occurred, which should be impossible.";

	/**
	 * This creates a TicTacToeHashCode by calling the Board constructor
	 * with boardTitle and instantiating and filling the winners array 
	 * with true at the correct indexes (which are the corresponding hash codes for 
	 * the winning TicTacToe strings)
	 * 
	 * @param boardTitle the title of the board 
	 */
	TicTacToeHashCode(String boardTitle) {
		super(boardTitle);
		winners = new boolean[POWERS_OF_THREE[9]]; 

		Scanner winReader = null;

		try {
			winReader = new Scanner(new File(WINNER_FILE));
		} catch (FileNotFoundException e) {
			System.out.println(WINNER_FILE_NOT_FOUND);
			System.exit(ERROR_CODE);
		}

		String current;

		while(winReader.hasNextLine()) {
			current = winReader.nextLine();
			if(winners[myHashCode(current)] == true)
				throw new RuntimeException(COLLISION_OCCURRED);

			winners[myHashCode(current)] = true;
		}

		winReader.close();
	}

	/**
	 * Returns a hash code for the current board string using the charAt method.
	 * Each hash code should be individual: no collisions should occur. This
	 * is accomplished by adding a value for an x, o, or space times a power of
	 * three.
	 * 
	 * @return an int that is the hash code for the current board string
	 */
	@Override
	public int myHashCode() {
		int power = 0, sum = 0;

		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				switch(charAt(r, c)) {
				case SPACE:
					break; //equivalent to adding zero
				case X:
					sum += POWERS_OF_THREE[power]; //equivalent to adding 1 * 3^power
					break;
				case O: 
					sum += 2 * POWERS_OF_THREE[power];
					break;
				}
				power++;
			}
		}

		return sum;
	}

	/**
	 * Returns a hash code for str. Each hash code should be individual:
	 * no collisions should occur. This is accomplished by adding a value for 
	 * an x, o, or space times a power of three. The algorithm for this
	 * is essentially the same as the other myHashCode. This method just
	 * uses a passed in string rather than a board string.
	 * 
	 * @param str the String for which the hash code is to be created
	 * @return an int that is the hash code for the str passed in
	 */
	public int myHashCode(String str) {
		int power = 0, sum = 0;
		char[] ch = str.toCharArray();

		for(int i = 0; i < ch.length; i++) {
			switch(ch[i]) {
			case SPACE:
				break; //equivalent to adding zero
			case X:
				sum += POWERS_OF_THREE[power]; //equivalent to adding 1 * 3^power
				break;
			case O: 
				sum += 2 * POWERS_OF_THREE[power];
				break;
			}
			power++;
		}

		return sum;
	}

	/**
	 * Returns whether a TicTacToe board is a winner or loser using
	 * the winners array and myHashCode method. The myHashCode is called for
	 * the String s, and then the corresponding index in the winners array is
	 * returned.
	 * 
	 * @param s a String holding the TicTacToe to be evaluated
	 * @return true if s is a winning TicTacToe string, false otherwise
	 */
	@Override
	public boolean isWin(String s) {
		return winners[myHashCode(s)];
	}

	/**
	 * Returns whether boardString is a winner or loser using
	 * the winners array and myHashCode method. The myHashCode is called for
	 * boardString, and then the corresponding index in the winners array is
	 * returned.
	 * 
	 * @return true if boardString is a winning TicTacToe string, false otherwise
	 */
	@Override
	public boolean isWin() {
		return winners[myHashCode()];
	}

	/**
	 * Displays a TicTacToe on the board including changing the 
	 * buttons representing the board and the labels with the hash code
	 * and whether the String is a winner or a loser
	 * 
	 * @param ticTacToe the String to be evaluated and displayed
	 */
	public void displayTicTacToe(String ticTacToe) {
		setBoardString(ticTacToe);
		show(ticTacToe);
		setHashCodeLabel(myHashCode());
		setWinnerLabel(winners[myHashCode()]);
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode(BOARD_TITLE);
		Scanner testReader = null;

		try {
			testReader = new Scanner(new File(TEST_FILE));
		} catch (FileNotFoundException e) {
			System.out.println(TEST_FILE_NOT_FOUND);
			System.exit(ERROR_CODE);
		}

		while (testReader.hasNextLine()) {
			board.displayTicTacToe(testReader.nextLine());

			Thread.sleep(DELAY);
		}
	}

}
