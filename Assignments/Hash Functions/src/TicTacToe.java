/**
 * This class holds all of the necessary methods needed for TicTacToes. 
 * This includes multiple versions of methods such as isWin that take in
 * different parameters, namely the two forms the TicTacToe is stored in
 * (a nine-character String and a 2D 3 by 3 char array). Other important methods
 * allow counting the number of chars in a board, checking if a board is valid,
 * converting boards between the two forms and many other options. 
 * This class was created by my instructor to aid in the Hash Functions assignment.
 * Then, the main creates a String, and prints if it is valid, if it has a 
 * row win, a column win, or a diagonal win and if it is a win.
 * 
 * @author Mrs. Kelly
 */

public class TicTacToe {

	/**
	 * An integer constant holding the number of rows: 3
	 */
	public final static int ROWS = 3;

	/**
	 * An integer constant holding the number of cols: 3
	 */
	public final static int COLS = 3;

	/**
	 * An integer constant holding the number of possibilities for TicTacToe
	 * boards which is three to the ninth
	 */
	public final static int POSSIBILITIES = (int) Math.pow(3,9);

	/**
	 * An integer constant holding the number of possibilities for the chars 
	 * used which is three because the only eligible chars for TicTacToe 
	 * are x, o and space
	 */
	public final static int CHAR_POSSIBILITIES = 3; // x, o or space

	/**
	 * Returns the number of the specified char on the board
	 * 
	 * @param b a 2d char array representing a TicTacToe board
	 * @param ch the char to be counted
	 * @return the number of times ch appears on b
	 */
	public static int numChars(char[][] b, char ch) {
		int total = 0;
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++)
				if (ch == b[r][c]) 
					total++;
		return total;
	}

	/**
	 * Checks if the board is valid. For example, boards with less than 
	 * 3 xs or 3 os are not considered valid boards.
	 * 
	 * @param board the TicTacToe board to be checked
	 * @return true, if board is valid; false, otherwise
	 */
	public static boolean valid(char[][] board) {

		// Ensure there are at least 3 xs and 2 os, or 3 os and 2 xs
		// Make sure there are at least one more x or one more o
		int numX = numChars(board, 'x');
		int numO = numChars(board, 'o');
		if (! (numX > 2 || numO > 2)) 
			return false;
		if ((numX == numO + 1) || (numO == numX + 1)) 
			return true;
		return false;
	}

	/**
	 * Converts a TicTacToe board into a String
	 * 
	 * @param b the board to be converted
	 * @return the String form of b
	 */
	public static String boardToString(char[][] b) {
		String result = "";
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) 
				result += b[r][c];
			//     result += "\n";
		}
		return result;
	}

	/**
	 * Converts a TicTacToe String into a board, which is a 3 by 3 char array
	 * 
	 * @param board the String to be converted
	 * @return the char array form of String
	 */
	public static char[][] stringToBoard(String board) {
		char[][] b = new char[ROWS][COLS];
		int index = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) 
				b[r][c] = whichLetter(board.charAt(index++));
		}
		return b;
	}

	/**
	 * Returns which letter the numerical equivalent (that this program uses)
	 * of the chars are
	 * 
	 * @param ch the char for whose numerical equivalent should be returned
	 * @return 'x' if ch is 1, '2' if ch is o, ' ' if ch is 1, else returns ch
	 */
	public static char whichLetter(char ch) {
		switch (ch) {
		case '1' : 
			return 'x';
		case '2' : 
			return 'o';
		case '0'  : 
			return ' ';
		default: 
			return ch;
		}
	}
	
	/**
	 * Makes and returns a board when sent a String
	 * 
	 * @param s the String to be converted to a board
	 * @return a 2d array of chars representing the String as a board
	 */
	public static char[][] makeBoard(String s) {
		char[][] b = new char[ROWS][COLS];
		int ch = 0;
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++){         
				b[r][c] = whichLetter(s.charAt(ch));
				ch++;
			}
		return b;
	}
	
	/**
	 * Adds one to the last char of the String and adjust all of the other chars
	 * appropriately
	 * 
	 * @param s a 9 character char composed of 0s, 1s and 2s
	 * @return the String version of s with one added
	 */
	private static String addOne(String s) {
		// s is a 9 character string, composed of 0s, 1s, and 2s.  Add 1 to the last char, adjusting
		// all the rest of the characters as necessary.
		boolean carry = false;
		char ch[] = s.toCharArray();
		ch[ch.length-1] =  (char)((int)(ch[ch.length-1])+1);
		for (int n = ch.length-1; n >=0; n--) {
			if (carry) ch[n] = (char)((int)(ch[n])+1);
			if (ch[n] == '3') {
				carry = true;
				ch[n] = '0';
			}
			else
				carry = false;      
		}
		return new String(ch);
	}
	
	/**
	 * Returns a String array with all the possible values for a TicTacToe board.
	 * This uses the addOne method in a while array to do so. 
	 *
	 * @return a String array with the possible values for a TicTacToe Board 
	 * 		(of size 3^9)
	 */
	public static String[] fillValues() {
		String strBoard = "000000000";
		String[] values = new String[POSSIBILITIES];
		int index = 0;
		values[index++] = strBoard;
		while (!strBoard.equals("222222222") ) {
			strBoard = addOne(strBoard);
			values[index++] = strBoard;
		}
		return values;
	}
	
	/**
	 * Returns whether or not the board has a diagonal win on it
	 * 
	 * @param board the board to be checked, which is a 2D char array
	 * @return true, if the board has a diagonal win, false otherwise
	 */
	private static boolean diagonalWin(char[][] board) {
		int wins = 0;
		if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x') || 
				(board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) 
			wins++;


		if ((board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x') ||
				(board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')) 
			wins++;

		return wins == 1;
	}
	
	/**
	 * Returns whether or not the board has a row win on it
	 * 
	 * @param board the board to be checked, which is a 2D char array
	 * @return true, if the board has a row win, false otherwise
	 */
	private static boolean rowWin(char[][] board) {
		char ch;
		int wins = 0;
		boolean win = true;
		for (int r = 0; r < ROWS; r++) {
			win = true;
			ch = board[r][0];
			for (int c = 0; c < COLS; c++) 
				if (ch != board[r][c]) {
					win = false;
					break;
				}
			if (win && ch != ' ')
				wins++;
		} 
		return wins == 1;
	} 
	
	/**
	 * Returns whether or not the board has a column win on it
	 * 
	 * @param board the board to be checked, which is a 2D char array
	 * @return true, if the board has a column win, false otherwise
	 */
	private static boolean colWin(char[][] board) {
		char ch;
		int wins = 0;
		boolean win = true;
		for (int c = 0; c < COLS; c++) {
			win = true;
			ch = board[0][c];
			for (int r = 0; r < ROWS; r++) 
				if (ch != board[r][c]) {
					win = false;
					break;
				}
			if (win && ch != ' ') 
				wins++;
		} 
		return wins == 1;
	} 
	
	/**
	 * Returns whether the passed in board is a win by checking if 
	 * the board is valid and if there is a row win, a col win or 
	 * a diagonal win.
	 * 
	 * @param b the board to be checked
	 * @return true, if the board is valid and has a win, false otherwise
	 */
	public static boolean isWin(char[][]b) {
		return valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b));
	}

	/**
	 * Performs the same function as isWin but instead of returning a boolean,
	 * it returns a String which allows the type of win to be returned.
	 * 
	 * @param b the board as a 2D char array
	 * @return the type of win as a String
	 */
	public static String isWinString(char[][]b) {
		boolean v = valid(b);
		boolean r = rowWin(b);
		boolean c = colWin(b);
		boolean d = diagonalWin(b);
		if (valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b))) {
			if (r) return "Row";
			if (c) return "Col";
			if (d) return "Dia";
			return "winner";
		}
		else
			return "loser";
	}
	
	/**
	 * Returns whether a String is a win, by converting s to a board and
	 * then checking the board produced using isWin.
	 * 
	 * @param s the String to be checked for a win
	 * @return true, if s is a win, false otherwise
	 */
	public static boolean isWin(String s) {
		char[][] b = stringToBoard(s);
		return isWin(b);
	}
	/**
	 * Returns whether what type of win a String is, by converting s to a board
	 * and then checking the board produced using isWinString.
	 * 
	 * @param s the String to be checked for a win
	 * @return the type of win as a String
	 */
	public static String isWinString(String s) {
		char[][] b = stringToBoard(s);
		return isWinString(b);
	}
	
	public static void main(String[] args) {
		String s = "ooooxxxox";
		char[][] b = stringToBoard(s);
		System.out.println("Valid:   " + valid(b));
		System.out.println("Row Win: " + rowWin(b));
		System.out.println("Col Win: " + colWin(b));
		System.out.println("Dia Win: " + diagonalWin(b));
		System.out.println(isWin(b));

	}    
}
