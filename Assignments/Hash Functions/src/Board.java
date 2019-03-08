import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The superclass used for TicTacToeHashCode. This class primarily contains
 * the graphical elements used in TicTacToeHashCode, which is why it extends
 * JFrame. The frame created contains nine buttons representing the
 * nine spots on a TicTacToe board and has labels with the hash code and whether
 * or not this class is a winner. This class has abstract methods because
 * the methods related to hashing and determining the win are supposed to be
 * defined in TicTacToeHashCode. This class was written by my instructor
 * to be used in the assignment.
 * 
 * @author Mrs. Kelly
 *
 */
abstract class Board extends JFrame implements ActionListener {
	
	/**
	 * The JButton array that displays the TicTacToe board (the xs and os)
	 */
	private JButton buttons[][];
	
	/**
	 * The label on the graphical interface showing the hash code of the
	 * current TicTacToe String displayed
	 */
	private JLabel lblHashCode;
	
	/**
	 * The label on the graphical interface showing whether the current 
	 * TicTacToe String being displayed is a winner or a loser
	 */
	private JLabel lblWinTitle;
	
	/**
	 * This String is the String being used as the current TicTacToe String
	 */
	private String boardString = "";
	
	/**
	 * Constructs a Board using a title.
	 * 
	 * @param title the title for hte JFrame
	 */
	public Board(String title) {
		super(title);
		setupFrame();
	}
	
	/**
	 * Sets the label for the hashcode using a passed in String. Does
	 * not calculate the hash code itself.
	 * 
	 * @param hashcode a given integer that is supposed to be the hash code
	 */
	public void setHashCodeLabel(int hashcode) {
		lblHashCode.setText("" + hashcode);
	}
	
	/**
	 * Sets the label for the winner using a passed in String. Does not
	 * determine the winner itself.
	 * 
	 * @param result a given String that is supposed to represent whether
	 * 		the boardString is a winner or a loser
	 */
	public void setWinnerLabel(String result) {
		lblWinTitle.setText(result);
	}
	
	/**
	 * Sets the winner label using a given Boolean. This method does not calculate
	 * this status itself. If the parameter is true, the label will be set to winner.
	 * If the boolean is false, the label will be set to loser. This uses
	 * the setWinnerLabel with a String method, so it should be used instead
	 * of that other method because the label should only be set to winner or loser.
	 * 
	 * @param result a boolean representing the winning status
	 */
	public void setWinnerLabel(boolean result) {
		if (result)
			setWinnerLabel("Winner");
		else
			setWinnerLabel("Loser");
	}
	
	/**
	 * This method was required by the superclass, but was not defined because it 
	 * was not needed.
	 * 
	 * @param e an ActionEvent that will not have any effect on the program
	 * 		given the explanation above
	 */
	// required because of abstract method, but not used
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	/**
	 * Creates and returns a JPanel with the correct current hash code,
	 * winner label, and title of the Board. This method is used as part
	 * of the original set up, and is not used every time that something
	 * is changed on the board, meaning that the values such as hash code
	 * are not automatically re-displayed because of this method.
	 * 
	 * @return a JPanel that will be used in the JFrame of the board
	 */
	JPanel setupPanelOne() {
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");
		;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser
		setWinnerLabel(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());
		panel.add(lblHCTitle);
		panel.add(lblHashCode);
		panel.add(lblWinTitle);
		return panel;
	}
	
	/**
	 * Creates and returns a JPanel to be integrated into the board.
	 * This mainly sets up the button section of the board. 
	 * 
	 * @return a JPanel with buttons on it
	 */
	private JPanel setupPanelTwo() {
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS, TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton) e.getSource();
						btn.setText("" + cycleValue(btn.getText().charAt(0)));
						resetBoardString();
						setHashCodeLabel(myHashCode());
						setWinnerLabel(isWin());

					}
				});
				panel.add(b);
				buttons[r][c] = b;
			}

		return panel;
	}
	
	/**
	 * Used to cycle out the values of the board, by replacing certain character
	 * with other certain characters.
	 * 
	 * @param ch an x, o or space character
	 * @return if 'x', an 'o'; if 'o', a ' '; else, 'x'
	 */
	private static char cycleValue(char ch) {
		switch (ch) {
		case 'x':
			return 'o';
		case 'o':
			return ' ';
		default:
			return 'x';
		}
	}
	
	/**
	 * Sets up basic aspects of the frame like size, layout,
	 * and default close operation.
	 */
	private void setupFrame() {
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		// Setup Panels
		panel2 = setupPanelTwo(); // panelOne displays a value that requires panelTwo to be ready
		super.add(setupPanelOne());
		super.add(panel2);

		super.setVisible(true);
	}
	
	/**
	 * Returns a random option for the TicTacToe board, namely an x, an o,
	 * or a space.
	 * 
	 * @return a random character out of the three options: an x, and o or a space
	 */
	private char randomXO() {
		int rnd = (int) (Math.random() * TicTacToe.CHAR_POSSIBILITIES);
		switch (rnd) {
		case 1:
			return 'x';
		case 2:
			return 'o';
		default:
			return ' ';
		}
	}
	
	/**
	 * An abstract method that is intended to generate a hash code using 
	 * the boardString 
	 * 
	 * @return the hash code corresponding to the boardString
	 */
	abstract int myHashCode();
	
	/**
	 * An abstract method that should return whether or not the String passed 
	 * in is a winning TicTacToe String, hopefully using an array of winners 
	 * and hash codes
	 * 
	 * @param s the String whose winning status should be determined
	 * @return true, if s is a win, false otherwise
	 */
	abstract boolean isWin(String s);
	
	/**
	 * An abstract method that should return whether or not the boardString 
	 * is a winning TicTacToe String, hopefully using an array of winners 
	 * and hash codes
	 * 
	 * @return true, if boardString is a win, false otherwise
	 */
	abstract boolean isWin();
	
	/**
	 * Returns the value of the char in buttons at [row, col]
	 * 
	 * @param row the row the char is on in buttons
	 * @param col the col the char is on in buttons
	 * @return the char at the position in the buttons array
	 */
	public char charAt(int row, int col) {
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}
   
	/**
	 * Returns the char in a String, which if it were a 3x3 array, would
	 * be at position [row, col]. If the length is not long enough,
	 * an * will be returned
	 * 
	 * @param s the String holding the char to be found
	 * @param row the row the char would be on
	 * @param col the col the char would be on
	 * @return a char at the proper position in s, if s is not long enough, an *
	 */
   public char charAt(String s, int row, int col) {
     int pos = row * TicTacToe.COLS + col;
     if (s.length() >= pos)
       return s.charAt(pos);
     else
       return '*';   
   }
   
   /**
    * Shows the TicTacToe string passed in on the buttons array. This does not
    * change the labels which show the hash code and the winning status
    * 
    * @param s the String to be displayed on the buttons
    */
   public void show(String s) {
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = s.charAt(pos);
				switch (ch) {
				case '1':
					letter = "x";
					break;
				case '2':
					letter = "o";
					break;
				case '0':
					letter = " ";
					break;
				default:
					letter = "" + ch;
				}
				buttons[r][c].setText(letter);
				pos++;
			}
	}

   /**
    * Resets the boardString to an empty String
    */
	public void resetBoardString() {
		boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				boardString += buttons[r][c].getText();
			}
	}

	/**
	 * Sets the boardString to s
	 * 
	 * @param s the String boardString is to be set to
	 */
	public void setBoardString(String s) {
		boardString = s;
		show(s);
	}
	
	/**
	 * Returns boardString
	 * 
	 * @return boardString
	 */
	public String getBoardString() {
		return boardString;
	}
	
	/**
	 * Displays a random string on the board. It sets both the buttons and 
	 * the appropriate labels
	 */
	public void displayRandomString() {
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				boardString += ch;
				buttons[r][c].setText("" + ch);
			}
		setHashCodeLabel(myHashCode());
		setWinnerLabel(isWin());
	}

}