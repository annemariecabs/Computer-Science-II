import java.util.ArrayList;
import java.util.Arrays;

public class EightQueens {
	
	private static ArrayList<ArrayList<Queen>> allSolutions;

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
		
		boolean[][] queensAsBooleans; 
		queens = new ArrayList<Queen>();
		addQueen(queens);
		PictureYourSolution picture = new PictureYourSolution(PictureYourSolution.queenToBooleanArray(queens));
		
		Thread.sleep(2000);
		
		int count = 0;
		
		//missing squares: one below 5, 16
		for(ArrayList<Queen> solution: allSolutions) {
			count++;
			queensAsBooleans = PictureYourSolution.queenToBooleanArray(solution);
			
			picture.resetSquares(queensAsBooleans);
			picture.resetHeader("Solution #" + count);
			
			Thread.sleep(4000);
		}
	}
 	

}
