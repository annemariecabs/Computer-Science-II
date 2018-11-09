import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author AnneMarie Caballero {@link https://github.com/annemariecabs}
 *
 */

public class ProductionLine {
	
	//TODO: it's fine if they're equal size right - CHECK WITH MRS. KELLY
	
	private Queue<Disk> input;
	private Queue<Tower> output;
	private Tower robotArm;
	
	public ProductionLine() {
		input = new LinkedList<Disk>();
		output = new LinkedList<Tower>();
		
		robotArm = new Tower();
	}
	
	public void addDisk(Disk d) {
		input.add(d);
	}
	
	public void unloadRobot() {
		output.add(robotArm.flip());
		
		robotArm = new Tower();
	}
	
	public void process() {
		Iterator<Disk> inputIter = input.iterator();
		Disk tempDisk;
		Disk topDisk;
		
		while (inputIter.hasNext()) {
			tempDisk = inputIter.next();
			
			if(! robotArm.empty()) {
				topDisk = robotArm.peek();
			
				if(tempDisk.compareTo(topDisk) < 0)
					unloadRobot();
			}
			
			robotArm.add(tempDisk);
		}
		
		unloadRobot();
	}
	
	public Tower removeTower() {
		if(output.isEmpty())
			return null;
		
		return output.remove();
	}
}
