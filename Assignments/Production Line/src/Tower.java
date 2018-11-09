import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

/**
 * 
 * @author annemariecaballero
 *
 */

public class Tower extends Stack<Disk> {
	
	public Tower() {
		super();
	}
	
	//TODO: Should this just be what's on top, considering it's a stack
	//TODO: Should it print from top to bottom or bottom to top
	//TODO: just which way should it print ***IMPORTANT***
	public String toString() {
		Iterator<Disk> iter = this.iterator();
		ArrayList<String> temp = new ArrayList<String>();
		
		while(iter.hasNext()) {
			temp.add(iter.next().toString());
		}
		
		Collections.reverse(temp);
		
		return temp.toString();
		
		/* what i had before when it printed top to bottom
		 Iterator<Disk> iter = this.iterator();
		String temp = "[";
		
		while(iter.hasNext()) {
			temp += iter.next().toString() + ", ";
		}
		
		temp = temp.substring(0, temp.length() - 2) + "]";
		
		return temp; 
		 * 
		 */
	}
	
	//TODO: is it bad to re-instantiate here - not going to lie this seems inefficient
	// no copy constructor though - could make a private one
	//TODO: should it return the flipped version or just flip it - would get rid of problem
	//TODO: I did the above but may want to reverse it
	public Tower flip() {
		Tower temp = new Tower();
		
		while(! this.empty()) {
			temp.add(this.pop());
		}
		
		return temp;
	}
}