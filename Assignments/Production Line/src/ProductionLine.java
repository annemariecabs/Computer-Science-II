import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ProductionLine represents an assembly line. Disks are fed as a Queue into a robot arm. The robot arm will then create
 * upside-down pyramids and then flip them onto the output line, so that the output queue is a series of pyramids
 * built from the Disks input into the robot arm. The input and output are a Queue of Disks and a Queue of Towers respectively.
 * The robot arm is a Tower. The class has one constructor, which initializes the fields as empty.The method then has four 
 * functionalities: addDisk (this adds a disk to the input), unloadRobot (flips the current Tower in robot arm, puts it on
 * output, and clears the robot arm), process (processes through input line creating pyramids and adding them to the output), 
 * and removeTower (which removes a Tower from the output).
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
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
