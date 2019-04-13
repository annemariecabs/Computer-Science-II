import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


//TODO give credit to PracticeGraphics and the border thing if i use it
//TODO make the colors I used CONSTANTS with names like marigold
public class PictureYourSolution {
	
	private static final Color MARIGOLD = new Color(255, 226, 61);
	private static final Color DARK_BLUE = new Color(83, 64, 191);
	
	private static final int ROWS = 8;
	private static final int COLS = 8;
	private static final int HEIGHT = 60*ROWS;
	private static final int WIDTH = 60*COLS;
	private static final Color LIGHT_COLOR = Color.WHITE;
	private static final Color DARK_COLOR = MARIGOLD;
	
	private static final Color HEADER_COLOR = DARK_BLUE;
	
	private static final Font PANEL_FONT = new Font("Georgia", Font.PLAIN, 40);
	private static final int MIN_PANEL_HEIGHT = 10;
	private static final int MAX_PANEL_HEIGHT = 50;
	private static final int BEST_PANEL_HEIGHT = 50;
	
	private static final String HEADER_TEXT = "My Chessboard";
	private static final Color PANEL_TEXT_COLOR = Color.WHITE;
	
	private static final String TITLE = "My Eight Queens Solution";
	
	private JFrame window;
	private JPanel header, grid;
	private JLabel headerText;
	private ChessSquarePanel[][] squares;
	private boolean[][] queens; 
	
	PictureYourSolution(boolean[][] qs) {
		queens = qs;
		squares = new ChessSquarePanel[ROWS][COLS];
		
		buildFrame();
		
		header = buildHeaderPanel();
		grid = buildGridPanel();
		
		window.add(header);
		window.add(grid);
		
		window.setVisible(true);
	}
	
	public void setVisibility(boolean visible) {
		window.setVisible(visible);
	}
	
	public void resetSquares(boolean[][] qs) {
		queens = qs;
		
		window.remove(grid);
		grid = buildGridPanel();
		window.add(grid);

	}
	
	public void resetHeader(String hText) {
		headerText.setText(hText);
	}
	
	private void buildFrame() {
	      window = new JFrame(TITLE);
	      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      window.setSize(new Dimension(WIDTH, HEIGHT));
	      window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS)); 
	} 
	
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
	
	private JPanel buildGridPanel() {
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(ROWS, COLS));
		
		ChessSquarePanel square;
		Border border = BorderFactory.createLineBorder(new Color(83, 64, 191));
		
		for(int r = 0; r < ROWS; r++)
			for(int c = 0; c < COLS; c++) {
				square = new ChessSquarePanel(getRightColor(r, c), getRightFlag(r, c));
				squares[r][c] = square;
				panel.add(square);
			}

		return panel;
	}

	private Color getRightColor(int r, int c) {
		if(r%2 == c % 2) 
			return LIGHT_COLOR;
		else
			return DARK_COLOR;
	}

	private boolean getRightFlag(int r, int c) {
		return queens[r][c];
	}

	//TODO is the use of literals here okay
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
	
	public static boolean[][] queenToBooleanArray(ArrayList<Queen> queens) {
		boolean[][] aSolution = new boolean[8][8]; //TODO remove literals
				
		for(Queen q: queens) {
			aSolution[q.getRow()][q.getCol()] = true;
		}
		
		return aSolution;
			
	}
	/**
	public static void main(String[] args) {
		PictureYourSolution p = new PictureYourSolution(mySolution());
	}
	**/

}
