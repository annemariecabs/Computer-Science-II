import java.util.LinkedList;
import java.util.Queue;

/**
 * This is a test class for Assignment #4: ProductionLine. It tests ProductionLine
 * with pre-created inputs. Mostly checking for special cases.
 * 
 * @author AnneMarie Caballero
 */

public class CannedTest {
	
	public static final String PARTITION = "******\n";
	
	//processes through a ProductionLine with a given input - trying to avoid redundancy
	public static String processInput(Queue<Disk> disks) {
		ProductionLine line = new ProductionLine();
		Tower temp;
		String check = "";
		
		for(Disk d: disks) {
			line.addDisk(d);
		}
		
		line.process();
		
		temp = line.removeTower();
		
		
		while(temp != null) {
			check += temp;
			check += PARTITION; //how I separate my towers
			
			temp = line.removeTower();
		}
		
		return check;
	}
	
	
	//tests ProductionLine if no disks are added
	public static void emptyTest() {
		String check = processInput(new LinkedList<Disk>());
		String correctAnswer = PARTITION;
		
		System.out.print("Test with empty ProductionLine: ");
		
		if(check.equals(correctAnswer))
			System.out.println("Successful");
		else
			System.out.println("Failure");
	}
	
	public static void onePyramidTest() {
		Queue<Disk> onePyramid = new LinkedList<Disk>();
		onePyramid.add(new Disk(1));
		onePyramid.add(new Disk(2));
		onePyramid.add(new Disk(3));
		onePyramid.add(new Disk(4));
		onePyramid.add(new Disk(5));
		onePyramid.add(new Disk(6));
		
		String check = processInput(onePyramid);
		
		System.out.print("Test with only one pyramid: ");
		
		if(check.indexOf(PARTITION) == check.lastIndexOf(PARTITION))
			System.out.println("Successful");
		else
			System.out.println("Failure");
	}
	
	//tests ProductionLine with standard input
	public static void typicalTest() {
		Queue<Disk> typical = new LinkedList<Disk>();
		typical.add(new Disk(1));
		typical.add(new Disk(2));
		typical.add(new Disk(3));
		typical.add(new Disk(2));
		typical.add(new Disk(5));
		typical.add(new Disk(3));
		typical.add(new Disk(5));
		typical.add(new Disk(7));
		typical.add(new Disk(5));
		
		String check = processInput(typical);
		
		String correctAnswer = "";
		Tower first = new Tower();
		first.add(new Disk(3));
		first.add(new Disk(2));
		first.add(new Disk(1));
		correctAnswer += first.toString() + PARTITION;
		
		Tower second = new Tower();
		second.add(new Disk(5));
		second.add(new Disk(2));
		correctAnswer += second.toString() + PARTITION;
		
		Tower third = new Tower(); 
		third.add(new Disk(7));
		third.add(new Disk(5));
		third.add(new Disk(3));
		correctAnswer += third.toString() + PARTITION;
		
		Tower fourth = new Tower(); 
		fourth.add(new Disk(5));
		correctAnswer += fourth.toString() + PARTITION;
		
		System.out.print("Test with typical ProductionLine input: ");
		
		if(check.equals(correctAnswer))
			System.out.println("Successful");
		else
			System.out.println("Failure"); 
			
		
	}
	
	//tests ProducitonLine with disks with the same radii
	public static void sameRadiusDiskTest() {
		Queue<Disk> disks = new LinkedList<Disk>();
		Tower correct = new Tower();
		for(int i = 0; i < 15; i++) {
			disks.add(new Disk(3));
			correct.add(new Disk(3));
		}
		
		String check = processInput(disks);
		String correctAnswer = correct.toString() + PARTITION;
		
		System.out.print("Test with disks all having same radius as ProductionLine input: ");
		
		if(check.equals(correctAnswer))
			System.out.println("Successful");
		else
			System.out.println("Failure");
	}
	
	//tests if Disks can be created with invalid radii 
	public static void invalidRadiiTest() {
		Disk temp;
		
		System.out.print("Invalid input (0) for radii produces error: ");
		try {
			temp = new Disk(0);
			System.out.println("Failure");
		}
		catch(IllegalArgumentException e) {
			System.out.println("Successful");
		}
		
		System.out.print("Invalid input (-3) for radii produces error: ");
		try {
			temp = new Disk(-3);
			System.out.println("Failure");
		}
		catch(IllegalArgumentException e) {
			System.out.println("Successful");
		}
	}
	
	public static void main(String[] args) {
		emptyTest();
		onePyramidTest();
		typicalTest();
		sameRadiusDiskTest();
		invalidRadiiTest();
	}
}
