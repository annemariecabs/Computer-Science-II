import java.util.Collections;
import java.util.Stack;

/**
 * The Tower class is a Stack of Disks. It has one constructor
 * which calls the superclass's constructor. Its two main functionalities in 
 * addition to the pop(), peek(), push(), and isEmpty() methods of a Stack are 
 * toString(), which prints the each disk in the tower as a line of dashes (number
 * of dashes equals radius of disk of tower being printed. The second functionality is 
 * flip(), which returns the flipped version of this tower. In order to do so,
 * it does destroy the current tower. However, for the purposes of ProductionLine
 * the Tower is never needed after the flip() method is used, so there is no need
 * to preserve the Tower.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */

public class Tower extends Stack<Disk> {
	
	/**
	 * The default constructor for a tower which calls on a Stack's super 
	 * constructor (creates an empty Stack of Disks)
	 */
	public Tower() {
		super();
	}
	
	/**
	 * Returns a String representation of a Tower. The String representation
	 * will have a line of dashes to represent each Disk. For example, a Tower
	 * with Disks of radii 3, 4, 7, and 8 in order would have the String 
	 * representation of: 
	 * <p> --- (3)
	 * <p> ---- (4)
	 * <p> ------- (7)
	 * <p> -------- (8)
	 * 
	 * @return a String with lines of dashes, each line representing a Disk in
	 * 		the tower, the top Disk will be the first line.
	 */
	public String toString() {
		Tower copy = new Tower();
		String temp = "";
		Disk disk;
		
		while(! this.empty()) {
			disk = this.pop();
			copy.push(disk);
			for(int i = 0; i < Integer.parseInt(disk.toString()); i++) {
				temp +=  "-";
			}
			
			temp += " (" + disk.toString() + ")\n";
		}
		
		Collections.reverse(copy);
		while(! copy.empty()) {
			disk = copy.pop();
			this.push(disk);
		}
		
		return temp;
	}
	
	/**
	 * Returns a flipped version of this Tower. For example, if this Tower had 
	 * Disks of 7, 6, 5, and 3 radii in order. The flipped version of that Tower 
	 * would be Disks with radii of 3, 5, 6, and 7.
	 * 
	 * @return a Tower that is a flipped version of this Tower
	 */
	public Tower flip() {
		Tower temp = new Tower();
		
		while(! this.empty()) {
			temp.add(this.pop());
		}
		
		return temp;
	}
}