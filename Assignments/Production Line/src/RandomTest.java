/**
 * This is a test class for Assignment #4: ProductionLine. It tests ProductionLine
 * with a random number of disks, each disk with a random radius.
 * 
 * @author AnneMarie Caballero
 */


public class RandomTest {
	
	public static int numDisks;
	public static final int MAX_RANDOM_DISKS = 30;
	public static final int MAX_RANDOM = 10;
	public static final String PARTITION = "******\n";
	
	public static void main(String[] args) {
		ProductionLine line = new ProductionLine();
		int rand;
		Tower temp;
	
		numDisks = (int) (Math.random() * MAX_RANDOM_DISKS);
		
		for(int i = 0; i < numDisks; i++) {
			rand = (int) (Math.random() * MAX_RANDOM) + 1;
			line.addDisk(new Disk(rand));
		}
		
		line.process();
		
		temp = line.removeTower();
		
		
		while(temp != null) {
			System.out.println(temp);
			System.out.println(PARTITION); //how I separate my towers
			
			temp = line.removeTower();
		}
	}
}
