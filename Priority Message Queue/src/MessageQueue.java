/**
 * This class creates a queue (FIFO) as a linked list using MessageListNodes.
 * The queue keeps track of its first and last node, so that the add, remove,
 * peek, and isEmpty are all O(1). The queue just has the required queue
 * functions: the constructor, add(), remove(), isEmpty() and peek().
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 */

public class MessageQueue {
	
	/**
	 * The first node in the queue, which is also where the nodes will be 
	 * removed from
	 */
	private MessageListNode first;
	
	/**
	 * The last node in the queue, which is also where the nodes will added
	 * after
	 */
	private MessageListNode last;
	
	//TODO is this needed?
	/**
	 * Constructs a MessageQueue, setting both the first and last node pointers
	 * to null
	 */
	public MessageQueue() {
		first = null;
		last = null;
	}
	
	/**
	 * Returns whether the queue is empty
	 * 
	 * @return true, if there are no nodes in the queue, false otherwise
	 */
	public boolean isEmpty() {
		if(first == null)
			return true;
		else
			return false;
	}
	
	/**
	 * Adds a Message to the queue
	 * 
	 * @param msg the Message to be added
	 */
	public void add(Message msg) {
		MessageListNode temp = new MessageListNode(msg);
		
		if(isEmpty()) {
			first = temp;
			last = temp;
		}
		else {
			last.setNext(temp);
			last = temp;
		}
		
	}
	
	/**
	 * Returns the first Message in the queue (without removing it); which
	 * should be the first one added to the queue (FIFO)
	 * 
	 * @return the first Message in the queue and null if the queue was empty
	 */
	public Message peek() {
		if(isEmpty())
			return null;
		else
			return first.getValue();
 	}
	
	/**
	 * Returns and removes the first Message in the queue, which should be the first 
	 * one added to the queue (FIFO)
	 * 
	 * @return the first Message in the queue, null if queue is empty
	 */
	public Message remove() {
		Message msg; 
		
		if(isEmpty())
			return null;
		else {
			msg = first.getValue();
			
			if(first.getNext() != null)
				first = first.getNext();
			else {
				first = null;
				last = null;
			}
			
			return msg;
		}
	}

}
