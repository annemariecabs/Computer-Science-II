/**
 * This object creates a ListNode with the value of a Message. This ListNode
 * will be used in a queue. The MessageListNode has two constructors: one with
 * a value passed in and one with a value and the next node passed in. Additionally,
 * the class has setters and getters for both the value and next fields, which
 * is in line with the use of the linked node for a typical linked list.
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 */
public class MessageListNode {
	
	/**
	 * the value of the MessageListNode
	 */
	private Message value;
	
	/**
	 * the next ListNode after this
	 */
	private MessageListNode next;
	
	/**
	 * Constructs a new MessageListNode that does not have a MessageListNode
	 * after it with a value of msg
	 * 
	 * @param msg the value of the MessageListNode being created
	 */
	public MessageListNode(Message msg) {
		value = msg;
		next = null;
	}
	
	/**
	 * Constructs a new MessageListNode that does not have a MessageListNode
	 * after it with a value of msg
	 * 
	 * @param msg the value of the MessageListNode being created
	 * @param nxt the next MassageListNode in the linked list
	 */
	public MessageListNode(Message msg, MessageListNode nxt) {
		value = msg;
		next = nxt;
	}
	
	/**
	 * Returns the value of the MessageListNode
	 * 
	 * @return the value (which is a message)
	 */
	public Message getValue() {
		return value;
	}
	
	/**
	 * Returns the next MessageListNode in the linked list
	 * 
	 * @return the next node (which is a MessageListNode)
	 */
	public MessageListNode getNext() {
		return next;
	}
	
	/**
	 * Sets the value of the MessageListNode
	 * 
	 * @param msg a Message to set the MessageLinkedNode value to
	 */
	public void setValue(Message msg) {
		value = msg;
	}
	
	
	/**
	 * Sets the next linked node to nxt
	 * 
	 * @param nxt a MessageListNode to be the next node in the list
	 */
	public void setNext(MessageListNode nxt) {
		next = nxt;
	}
}
