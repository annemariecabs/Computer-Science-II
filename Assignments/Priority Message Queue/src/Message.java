/**
 * This class creates a Message object, which holds a priority and an arrival time.
 * The class then has corresponding getters for those two fields. There are no
 * setters because these values should be set in the constructor, and not afterwards.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */
public class Message {
	/**
	 * The priority of the message - which is important as Messages are used
	 * in a PriorityQueue class
	 */
	private int priority;
	
	/**
	 * The time at which the Message arrives
	 */
	private int arrival;
	
	/**
	 * Constructs a Message with both a priority and an arrival time
	 * 
	 * @param p the priority of the Message
	 * @param a the arrival time of the Message
	 */
	public Message(int p, int a) {
		priority = p;
		arrival = a;
	}
	
	/**
	 * Returns the priority of the Message
	 * 
	 * @return the priority of the Message as an integer ranging from 0 
	 * 	(highest priority) to 4 (lowest priority)
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Returns the arrival time of the Message
	 * 
	 * @return an integer representing the time the Message arrived
	 */
	public int getArrivalTime() {
		return arrival;
	}

}
