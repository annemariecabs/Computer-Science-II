import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author AnneMarie Caballero {@link https://github.com/annemariecabs}
 *
 */

public class ProductionLine {
	
	/**
	 * A Queue of Disks that represent the input being fed into the production line
	 */
	private Queue<Disk> input;
	/**
	 * A Queue of Towers that represent the output of the production line. These
	 * towers should all be pyramids (no Disk will be larger than the Disk below
	 * it in the Tower)
	 */
	private Queue<Tower> output;
	/** 
	 * A Tower representing the Disks that the robot arm is currently holding
	 */
	private Tower robotArm;
	
	/**
	 * Constructs a ProductionLine object by creating default LinkedLists
	 * for the input and output lines and creating the default Tower for the
	 * ProductionLine's robot arm
	 */
	public ProductionLine() {
		input = new LinkedList<Disk>();
		output = new LinkedList<Tower>();
		
		robotArm = new Tower();
	}
	
	/**
	 * Adds a disk to the input of the ProductionLine
	 * 
	 * @param d the Disk to be added to the input line of production line
	 */
	public void addDisk(Disk d) {
		input.add(d);
	}
	
	/**
	 * Flips the Tower of Disks currently held by the robot arm and places it
	 * on the output line.
	 */
	public void unloadRobot() {
		output.add(robotArm.flip());
		
		robotArm = new Tower();
	}
	
	/**
	 * Processes through the input line of Disks. If the Disk is greater than the 
	 * current top Disk of the robot arm, the robot arm will pick up the disk. 
	 * Else, the robot arm will flip the current Tower of Disks and place it
	 * onto the output line, and then pick up the next Disk. At the end, there 
	 * will be an output line consisting of pyramid Towers.
	 */
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
	
	/**
	 * Removes a Tower from the output Queue
	 * 
	 * @return null if output is empty, the Tower if output is not empty.
	 */
	public Tower removeTower() {
		if(output.isEmpty())
			return null;
		
		return output.remove();
	}
}
