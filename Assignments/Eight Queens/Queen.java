/**
 * This class is meant to recreate the chess piece of a Queen, although it is general enough
 * it could be used for different chess pieces, for the Eight Queens theoretical problem.
 * This class holds a row and column and the getters for those fields. It also has a toString()
 * method that returns the Queen as a String resembling "{row, column}" and an equals method
 * which considers queens equal when they have the same row and column
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */

public class Queen {
	
	/**
	 * Holds the row that the Queen occupies
	 */
	private int row;
	
	/**
	 * Holds the column the Queen occupies
	 */
	private int col;
	
	/**
	 * Creates a Queen using a row and column
	 * @param r the int row will be set to
	 * @param c the int column will be set to
	 */
	Queen(int r, int c) {
		row = r;
		col = c;
	}
	
	/**
	 * Returns the row of this queen
	 * 
	 * @return an integer representing this Queen's row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns the column of this queen
	 * 
	 * @return an integer representing this Queen's column
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Returns a String representation of this Queen 
	 * 
	 * @return "{row, col}"
	 */
	public String toString() {
		return "{" + row + ", " + col + "}";
	}
	
	/**
	 * Overrides Object's equals method - considers Queens equal
	 * when they have the same 
	 * 
	 * @param obj the Object this queen is being compared to
	 */
	public boolean equals(Object obj) {
		Queen q = (Queen) obj;
		
		if(row == q.getRow() && col == q.getCol())
			return true;
		else 
			return false;
	}

}
