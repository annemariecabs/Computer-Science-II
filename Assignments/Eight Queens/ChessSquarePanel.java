import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

/**
 * A class that creates a sample graphical chess square panel using the Java Graphics 
 * capacity. This class keeps track of the background color of the current square and
 * whether it has a Q on it. The class then creates and paints the square using that 
 * information. The class also offers the capability to change those fields.
 * This class will be used to construct a chess board by the PictureYourSolution 
 * class.
 * 
 * NOTE: This class was partially created using Mrs. Kelly's MyPanel class.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */
public class ChessSquarePanel extends JPanel {
	
	/**
	 * The current color of this chess square
	 */
	private Color color;
	
	/**
	 * If true, means that there is a queen (a "q") on this square
	 * If false, means there isn't a queen (a "q") on this square
	 */
	private boolean flag;
	
	/**
	 * The font to be used for the q
	 */
	public static final Font Q_FONT = new Font("Georgia", Font.BOLD, 45);
	
	/**
	 * The letter Q as a String
	 */
	private static final String Q = "Q";
	
	/**
	 * A custom dark blue used for the q
	 */
	public static final Color DARK_BLUE = new Color(83, 64, 191);
	
	/**
	 * Creates a ChessSquarePanel with a color and a boolean, which represents 
	 * whether this square has a queen on it
	 * 
	 * @param c the Color for the back of the square
	 * @param f the boolean flag will be set to
	 */
	ChessSquarePanel(Color c, boolean f) {
		color = c;
		flag = f;
	}
	
	/**
	 * Paints the square with the set background color, sets the font and color of the Q,
	 * and places a Q on the square, if flag is true.
	 * 
	 * NOTE: Matthew Grillo helped me create the code for placing the Q
	 * 
	 * @param g the Graphics object to be used
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setBackground(color);
		g.setFont(Q_FONT);
		g.setColor(DARK_BLUE); //the color of the "q"
		
		int x, y;
		Graphics2D g2 = (Graphics2D) g;
		
		if(flag) {
			x = (int) (Q_FONT.getSize() * .25);
			y = (int) (g2.getFontMetrics().stringWidth(Q));
			g.drawString(Q, x, y);
		}
		
	}
	
	/**
	 * Sets the background color of the square and then repaints
	 * 
	 * @param c the new color that the square will be set to
	 */
	public void setBackgroundColor(Color c) {
		color = c;
		repaint();
	}
	
	/**
	 * Sets the flag field then repaints
	 * 
	 * @param f the boolean for flag to be set to
	 */
	public void setFlag(boolean f) {
		flag = f;
		repaint();
	}
}
