
public class Test {
	
	public static final int NUMBER_DISKS_ADDED = 25;
	public static final int MAX_RANDOM = 10;
	
	public static void main(String[] args) {
		ProductionLine line = new ProductionLine();
		int rand;
		Tower temp;
		
		for(int i = 0; i < NUMBER_DISKS_ADDED; i++) {
			rand = (int) (Math.random() * MAX_RANDOM) + 1;
			
			line.addDisk(new Disk(rand));
		}
		
		line.process();
		
		temp = line.removeTower();
		
		
		while(temp != null) {
			System.out.println(temp);
			System.out.println("******\n"); //how I separate my towers
			
			temp = line.removeTower();
		}
	}
}
