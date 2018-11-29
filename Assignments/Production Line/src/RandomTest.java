/**
 * This is a test class for Assignment #4: ProductionLine. It tests ProductionLine
 * with a random number of disks, each disk with a random radius. This test also 
 * involves testing the program by inputting disks with invalid radii. 
 * 
 * @author AnneMarie Caballero
 */

//TODO: fix javadocs

public class RandomTest {
	
	public static int numDisks;
	public static final int MAX_RANDOM_DISKS = 30;
	public static final int MAX_RANDOM = 10;
	public static final int CHECK_INVALID_RADII = 5;
	public static final String PARTITION = "******\n";
	
	public static void main(String[] args) {
		ProductionLine line = new ProductionLine();
		int rand;
		Tower temp;
	
		numDisks = (int) (Math.random() * MAX_RANDOM_DISKS);
		
		for(int i = 0; i < numDisks; i++) {
			//the below statement will create disks from -CHECK_INVALID_RADII to MAX_RANDOM_DISKS, which
			//checks if disks with invalid radii can be created. if it can't,
			//the try-catch statement will handle the error and print out that it occurred
			rand = (int) (Math.random() * MAX_RANDOM + CHECK_INVALID_RADII) - CHECK_INVALID_RADII; 
			
			try {
				line.addDisk(new Disk(rand));
			}
			catch(IllegalArgumentException e) {
				System.out.println("Random attempted to add a disk with an invalid radius, but was unsuccessful\n");
				rand = (int) (Math.random() * MAX_RANDOM) + 1;
				line.addDisk(new Disk(rand));
			}
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
