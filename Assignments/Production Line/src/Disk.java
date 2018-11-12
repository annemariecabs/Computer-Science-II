/**
 * 
 * @author annemariecaballero
 *
 */

public class Disk implements Comparable<Disk> {
	
	private int radius;
	
	public Disk(int r) {
		radius = r;
	}

	@Override
	public int compareTo(Disk o) {
		return radius - o.radius;
	}
	
	public String toString() {
		return "" + radius;
	}
}
