/**
 * 
 * @author AnneMarie Caballero
 *
 */

public class Disk implements Comparable<Disk> {
	
	/**
	 * an integer that holds the radius of the disk
	 */
	private int radius;
	
	/**
	 * Constructs a disk using a given radius.
	 * 
	 * @param r the radius of the disk to be created
	 * @throws IllegalArgumentException if r is not valid because it is
	 * 		less than or equal to zero
	 */
	public Disk(int r) throws IllegalArgumentException {
		if(!(r > 0)) 
			throw new IllegalArgumentException("Error: You cannot create a disk with a radius less than one.");
		
		radius = r;
	}
	 
	/**
	 * Compares this disk with another disk and returns an integer representing
	 * that comparison.
	 * 
	 * @param o the other Disk that this disk is being compared to
	 * @return a negative integer if this is less than o, a positive integer
	 * 		   if this is greater than o, and 0 if they are equal
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Disk o) {
		return radius - o.radius;
	}
	
	/**
	 * Returns a String representation of the disk, namely its radius.
	 * 
	 * @return the radius of the Disk as a String (the Disk's String representation)
	 * @see java.lang.Object#toString()
	 */

	public String toString() {
		return "" + radius;
	}
}
