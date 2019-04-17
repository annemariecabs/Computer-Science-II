import java.util.ArrayList;

/**
 * A class with two recursive methods attempting to solve the Eight Queens theoretical problem.
 * Eight Queens is a problem posed in which eight queens must be placed on an 8x8 chess board
 * without threatening each other. The addQueen() methods both solve this recursively.
 * The first addQueen() that takes only an ArrayList only finds one solution for this problem.
 * The second addQueen() that also takes two integers finds all of the possible solutions
 * to the addQueen() problem although it does not account for rotation of the board. There
 * is also the isValid() method, which assesses whether a Queen can be placed based
 * off of its row and col values and the current queens already placed. This is used in 
 * both addQueen()s.
 * 
 * Finally, this class's main method uses both addQueen() methods with the PictureYourSolution
 * graphical interface to display all of the solutions to eight queens using graphics.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */
public class EightQueens {
	
	/**
	 * An ArrayList of ArrayLists holding Queens
	 * that holds all of the possible solutions for the eight queens problem
	 */
	private static ArrayList<ArrayList<Queen>> allSolutions;
	
	/**
	 * The number of milliseconds the program will pause in between each solution.
	 */
	private static final int PAUSE = 2000;

	/**
	 * Recursively finds a solution to the eight queens thought problem. If currentQueens is
	 * equal to eight, returns true because Queens are only placed if they're valid. If not equal
	 * to eight, then the program will pick the next row and run through all of the possible columns.
	 * If any of the columns are valid spots, then a Queen will be added and the method
	 * will call itself for this new array. If that is unsuccessful, it will return false at that 
	 * level, that queen will be removed, and the next possibility will be tried.
	 * 
	 * NOTE: This method only finds one solution to the eight queens problem.
	 * 
	 * @param currentQueens the Queens that have already been placed
	 * @return true if a solution has been found, false otherwise
	 */
	public static boolean addQueen(ArrayList<Queen> currentQueens) {
		int row = 0, numValid = 0;
		
		if(currentQueens.size() == 8) {
			return true;
		}
		else {
			numValid = 0;
			
			if(! currentQueens.isEmpty())
				row = currentQueens.get(currentQueens.size() - 1).getRow() + 1;
		
			for(int col = 0; col < 8; col++) {
				
				//INCREASES EFFICIENCY
				if(8 - row == numValid)
					break;
				
				if(isValid(row, col, currentQueens)) {
					currentQueens.add(new Queen(row, col));
					numValid++;
				}
				else 
					continue;
			
				boolean result = addQueen(currentQueens);
				

				if(result) 
					return true;
				else {
					currentQueens.remove(row);
					continue;
				}
			}
		
			return false;
		}
	}
	
	/**
	 * This is an expanded version of the other addQueen method in this class. This method actually
	 * does find all possible solutions to the eight queens problem. Instead of exiting once
	 * one solution (a filled 8 queen array) is found, instead it recurses again. The 
	 * parameters have been altered so that a spot to start at can be passed in so that
	 * progress won't be lost. The successful board has its last item removed from it and
	 * then is re-passed to the array to start the process
	 * 
	 * @param row the row to start looking at
	 * @param col the column to start looking at
	 * @param currentBoard the queens currently placed
	 * @return true, once all 92 solutions have been found, false otherwise
	 * 		NOTE: this will return true at lower levels of the loops just when a solution is found
	 * 			but the whole method will only return true once all 92 solutions to this issue have 
	 * 			been found.
	 */
	public static boolean addQueen(int row, int col, ArrayList<Queen> currentBoard) {
		int numValid = 0;
		Queen temp;
		
		if(currentBoard.size() == 8) {
			allSolutions.add(new ArrayList<Queen>(currentBoard));
			temp = currentBoard.remove(currentBoard.size() - 1);
			addQueen(temp.getRow(), temp.getCol() + 1,  currentBoard); // NEED TO DO WAY MORE THAN THIS
			return true;
		}
		else {
			numValid = 0;
			
			for(; col < 8; col++) {
					
					//INCREASES EFFICIENCY
					if(8 - row == numValid)
						break;
					
					if(isValid(row, col, currentBoard)) {
						currentBoard.add(new Queen(row, col));
						numValid++;
					}
					else 
						continue;
				
					boolean result = addQueen(row + 1, 0, currentBoard);
					
					//need a condition in which it returns true to make
					//the method good code
					if(result && allSolutions.size() == 92) 
						return true;
					else if(result) {
						continue;
					}
					else {
						currentBoard.remove(row);
						continue;
					}
				}
			
			return false;
		}
	}

	/**
	 * Checks the validity of a spot on the board. If the queen to be placed at that row 
	 * and column is in the same row, column or diagonal as another queen, then the method
	 * will return false because that new position is not valid 
	 * 
	 * @param row the row of the spot to be checked
	 * @param col the column of the spot to be checked
	 * @param currentQueens an ArrayList containing the already-placed Queens
	 * @return true, if a Queen can be placed in this spot without
	 * 		threatening other already-placed Queens, false otherwise
	 */
	public static boolean isValid(int row, int col, ArrayList<Queen> currentQueens) {
		for(Queen q: currentQueens) {
			if(q.getRow() == row) 
				return false;
			else if(q.getCol() == col) 
				return false;
			else if(q.getRow() - q.getCol() == row - col)
				return false;
			else if(q.getRow() + q.getCol() == row + col)
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws InterruptedException {
		allSolutions = new ArrayList<ArrayList<Queen>>();
		ArrayList<Queen> queens = new ArrayList<Queen>();
		addQueen(0, 0, queens);
		
		boolean[][] queenPositions; 
		queens = new ArrayList<Queen>();
		addQueen(queens);

		PictureYourSolution picture = new PictureYourSolution(PictureYourSolution.queenToBooleanArray(queens));
		Thread.sleep(PAUSE);
		
		int count = 0;
		
		for(ArrayList<Queen> solution: allSolutions) {
			count++;
			
			queenPositions = PictureYourSolution.queenToBooleanArray(solution);
			
			picture.resetSquares(queenPositions);
			picture.resetHeader("Solution #" + count);
			
			Thread.sleep(PAUSE);
		}
	}
 	
}
