import java.util.ArrayList;

/**
 * This class creates a priority queue using MessageQueues for each priority
 * level. The constructor initializes each MessageQueue. Otherwise, the 
 * queue has the needed methods: add(), remove(), peek(), and isEmpty(). 
 * All of these methods are O(1). Currently, this class has five 
 * priority levels, as set by the integer constant NUM_PRIORITIES.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 */
public class MessagePriorityQueue {
	
	/**
	 * An ArrayList of MessageQueues which will hold the values for 
	 * the priority queue; each MessageQueue represents a priority level
	 */
	private ArrayList<MessageQueue> queues;
	
	/**
	 * The number of priorities available for this MessagePriorityQueue
	 */
	public static final int NUM_PRIORITIES = 5;
	
	/**
	 * Constructs a MessagePriorityQueue object by initializing
	 * each queue in the queues field
	 */
	public MessagePriorityQueue() {
		queues = new ArrayList<MessageQueue>(NUM_PRIORITIES);

		for(int i = 0; i < NUM_PRIORITIES; i++)
			queues.add(new MessageQueue());
	}
	
	/**
	 * Adds a Message to the MessageQueue at the index of its priority 
	 * in queues
	 * 
	 * @param msg the Message to be added
	 */
	public void add(Message msg) {
		queues.get(msg.getPriority()).add(msg);
	}

	/**
	 * Returns and removes the highest-priority Message (0 is the highest,
	 * 4 is the lowest)
	 * 
	 * @return the highest priority Message, null otherwise
	 */
	public Message remove() {
		for(int i = 0; i < queues.size(); i++)
			if(! queues.get(i).isEmpty())
				return queues.get(i).remove();
		
		return null;
	}
	
	/**
	 * Returns the highest-priority Message (0 is the highest, 4 is the lowest)
	 * 
	 * @return the highest priority Message, null otherwise
	 */
	public Message peek() {
		for(MessageQueue queue: queues) 
			if(! queue.isEmpty())
				return queue.peek();
			
		return null;
	}
	
	/**
	 * Returns whether the queue is empty
	 * 
	 * @return true if the queue is empty, null if there are any Messages in 
	 * 		the queue
	 */
	public boolean isEmpty() {
		int numEmpty = 0;
		
		for(MessageQueue queue: queues)
			if(queue.isEmpty())
				numEmpty++;
		
		return numEmpty == NUM_PRIORITIES ? true : false;
	}
}
