import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class ChessSquarePanel extends JPanel {
	
	private Color color;
	private boolean flag;
	
	//TODO is the use of literals here okay because it's a final
	public static final Font Q_FONT = new Font("Georgia", Font.BOLD, 45);
	private static final String Q = "Q";
	
	ChessSquarePanel(Color c, boolean f) {
		color = c;
		flag = f;
	}
	
	//give credit to Mrs. Kelly's MyPanel class
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setBackground(color);
		g.setFont(Q_FONT);
		g.setColor(new Color(83, 64, 191)); //the color of the "q"
		
		int x, y;
		Graphics2D g2 = (Graphics2D) g;
		
		//PARTIALLY INSPIRED BY MATTHEW's CODE
		if(flag) {
			x = (int) (Q_FONT.getSize() * .25);
			y = (int) (g2.getFontMetrics().stringWidth(Q));
			g.drawString(Q, x, y);
		}
		
	}
	
	public void setBackgroundColor(Color c) {
		color = c;
		repaint();
	}
	
	public void setFlag(boolean f) {
		flag = f;
		repaint();
	}
}
