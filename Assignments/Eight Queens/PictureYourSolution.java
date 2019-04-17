import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class will be used to display a chess board with eight queens on it, which will
 * serve as the graphical representation of solutions to the eight queens theoretical
 * problem. One such solution is displayed when this program is run. However, that
 * solution is manually programmed in, not found by a method, in the private mySolution()
 * method, as that method simply sets the boolean array to a solution I found not using
 * a computer program. This class will be used by EightQueens to display solutions that
 * that class found using a method. 
 * 
 * The graphical interface displayed is a JFrame. It has two panels. A header
 * with a JLabel, which has the automatic header text of "My Chess Board," although the header
 * text can be changed. It will also have a grid panel with a GridLayout and 8x8 ChessSquarePanels
 * added to represent the chess board.
 * 
 * NOTE: This class used Mrs. Kelly's PracticeGraphics class heavily as a reference.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 */
public class PictureYourSolution {
	
	/**
	 * The color of the chess square panels that will be put on the chess board - also
	 * just a lovely yellow color
	 */
	private static final Color MARIGOLD = new Color(255, 226, 61);
	
	/**
	 * A custom dark blue color to be used for the qs and the header
	 */
	private static final Color DARK_BLUE = new Color(83, 64, 191);
	
	/**
	 * The number of rows on the chess board
	 */
	private static final int ROWS = 8;
	
	/**
	 * The number of columns on the chess board
	 */
	private static final int COLS = 8;
	
	/**
	 * The height of the JFrame
	 */
	private static final int HEIGHT = 60*ROWS;
	
	/**
	 * The width of the JFrame
	 */
	private static final int WIDTH = 60*COLS;
	
	/**
	 * The light color for the chess board
	 */
	private static final Color LIGHT_COLOR = Color.WHITE;
	
	/**
	 * The dark color for the chess board (my custom marigold color)
	 */
	private static final Color DARK_COLOR = MARIGOLD;
	
	/**
	 * The color of the header (my custom dark blue color)
	 */
	private static final Color HEADER_COLOR = DARK_BLUE;

	/**
	 * The custom font to be used in the panels
	 */
	private static final Font PANEL_FONT = new Font("Georgia", Font.PLAIN, 40);
	
	/**
	 * The minimum panel height for the header
	 */
	private static final int MIN_PANEL_HEIGHT = 10;
	
	/**
	 * The maximum panel height for the header
	 */
	private static final int MAX_PANEL_HEIGHT = 50;
	
	/**
	 * The optimal panel height for the header
	 */
	private static final int BEST_PANEL_HEIGHT = 50;
	
	/**
	 * The de facto title that will be printed on the header JLabel
	 */
	private static final String HEADER_TEXT = "My Chess Board";

	/**
	 * The text color to be used on the panel
	 */
	private static final Color PANEL_TEXT_COLOR = Color.WHITE;
	
	/**
	 * The title for the board
	 */
	private static final String TITLE = "Eight Queens Solution";
	
	/**
	 * The window in which all the graphical components will be held
	 */
	private JFrame window;
	
	/**
	 * The header panel
	 */
	private JPanel header;
	
	/**
	 * The grid panel
	 */
	private JPanel grid;
	
	/**
	 * The text to be written on the header
	 */
	private JLabel headerText;
	
	/**
	 * A 2d array of ChessSquarePanels which will display the chess board
	 */
	private ChessSquarePanel[][] squares;
	
	/**
	 * A boolean array of queens that will be keep track of the current solution in 
	 * terms of a boolean array, rather than a Queen[]
	 */
	private boolean[][] queens; 
	
	/**
	 * Creates a PictureYourSolution object with a boolean[][] of queens that
	 * should represent a viable solution. This sets queens to qs, initializes squares,
	 * builds the grid and header panel, and then adds these panels to the window.
	 * 
	 * @param qs the solution to be displayed
	 */
	PictureYourSolution(boolean[][] qs) {
		queens = qs;
		squares = new ChessSquarePanel[ROWS][COLS];
		
		buildFrame();
		
		header = buildHeaderPanel();
		grid = buildGridPanel();
		
		window.add(header);
		window.add(grid);
		window.repaint();
		
		window.setVisible(true);
	}
	
	/**
	 * Sets the window panel's visibility
	 * 
	 * @param visible if true, sets the JFrame to visible, else makes it disappear
	 */
	public void setVisibility(boolean visible) {
		window.setVisible(visible);
	}
	
	/**
	 * Resets the chess board squares using queens, which represents a different solution.
	 * 
	 * @param qs the new solution to be displayed, represented as a boolean 2d array
	 */
	public void resetSquares(boolean[][] qs) {
		queens = qs;
		ChessSquarePanel square;
		
		for(int r = 0; r < ROWS; r++)
			for(int c = 0; c < COLS; c++) {
				square = squares[r][c];
				square.setBackgroundColor(getRightColor(r,c));
				square.setFlag(getRightFlag(r, c));
				window.repaint();
			}
	}
	
	/**
	 * Resets the header text using the passed-in hText
	 * 
	 * @param hText the new text to set the header to 
	 */
	public void resetHeader(String hText) {
		headerText.setText(hText);
	}
	
	/**
	 * Builds the JFrame window to be used including setting its size and layout
	 */
	private void buildFrame() {
	      window = new JFrame(TITLE);
	      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      window.setSize(new Dimension(WIDTH, HEIGHT + BEST_PANEL_HEIGHT));
	      window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS)); 
	} 
	
	/**
	 * Builds and returns a viable headerPanel including instantiating one, setting size and
	 * background color, creating and setting the fields of a JPanel, then adding that JPanel in.
	 * The JPanel will have the relevant text. 
	 * 
	 * @return the header panel
	 */
	private JPanel buildHeaderPanel() {
	      JPanel panel = new JPanel();
	      
	      panel.setMinimumSize(new Dimension(WIDTH, MIN_PANEL_HEIGHT));
	      panel.setPreferredSize(new Dimension(WIDTH, BEST_PANEL_HEIGHT));
	      //width is so high bc i want it to be able to dynamically resize
	      panel.setMaximumSize(new Dimension(WIDTH * 30, MAX_PANEL_HEIGHT));
	      panel.setBackground(HEADER_COLOR);
	      
	      headerText = new JLabel(HEADER_TEXT);
	      headerText.setForeground(PANEL_TEXT_COLOR);
	      headerText.setFont(PANEL_FONT);
	      
	      panel.add(headerText);
	      
	      return panel;
	 }
	
	/**
	 * Builds and returns the grid panel representing the chess board using 
	 * the queens field. This will have a GridLayout and have 64 ChessSquarePanels.
	 * 
	 * @return the grid panel
	 */
	private JPanel buildGridPanel() {
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(ROWS, COLS));
		
		ChessSquarePanel square;
		
		for(int r = 0; r < ROWS; r++)
			for(int c = 0; c < COLS; c++) {
				square = new ChessSquarePanel(getRightColor(r, c), getRightFlag(r, c));
				squares[r][c] = square;
				panel.add(square);
			}

		return panel;
	}
	
	/**
	 * Returns which color should be used for each square on the chess board
	 * using the row and column of that square
	 * 
	 * @param r the row of the square in question
	 * @param c the column of the square in question
	 * @return LIGHT_COLOR for squares on even rows and columns, DARK_COLOR otherwise
	 */
	private Color getRightColor(int r, int c) {
		if(r%2 == c % 2) 
			return LIGHT_COLOR;
		else
			return DARK_COLOR;
	}
	
	/**
	 * Returns whether this square should have a queen on it or not
	 * depending on the information in the queens array.
	 * 
	 * @param r the row in question
	 * @param c the column in question
	 * @return whether there should be a queen on (r, c)
	 */
	private boolean getRightFlag(int r, int c) {
		return queens[r][c];
	}

	/**
	 * A private method that simply creates a boolean array that represents the first solution that 
	 * I found, which was found without using a computer program. I used literals because it seemed 
	 * necessary, as this whole method is a way to avoid using literals in the body of the
	 * program.
	 * 
	 * @return the first solution I found (!!)
	 */
	private static boolean[][] mySolution() {
		boolean[][] mySolution = new boolean[ROWS][COLS];
		
		mySolution[0][5] = true;
		mySolution[1][2] = true;
		mySolution[2][0] = true;
		mySolution[3][7] = true;
		mySolution[4][4] = true;
		mySolution[5][1] = true;
		mySolution[6][3] = true;
		mySolution[7][6] = true;
		
		return mySolution;
		
	}
	
	/**
	 * Converts a Queen ArrayList to a boolean array. Will be used in the Eight Queens class.
	 * Mostly a reference method. 
	 * 
	 * @param queens the Queen ArrayList to be converted
	 * @return the boolean[][] that represents the same information as the Queen ArrayList
	 */
	public static boolean[][] queenToBooleanArray(ArrayList<Queen> queens) {
		boolean[][] aSolution = new boolean[ROWS][COLS]; 
				
		for(Queen q: queens) {
			aSolution[q.getRow()][q.getCol()] = true;
		}
		
		return aSolution;
			
	}
	
	public static void main(String[] args) {
		//the first solution I found graphically
		PictureYourSolution picture = new PictureYourSolution(mySolution());
	}
	

}
