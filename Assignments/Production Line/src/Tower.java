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
	
	public String toString() {
		Tower copy = new Tower();
		String temp = "";
		Disk disk;
		
		while(! this.empty()) {
			disk = this.pop();
			copy.push(disk);
			for(int i = 0; i < Integer.parseInt(disk.toString()); i++) {
				temp +=  "-";
			}
			
			temp += "\n";
		}
		
		Collections.reverse(copy);
		while(! copy.empty()) {
			disk = copy.pop();
			this.push(disk);
		}
		
		return temp;
	}
	
	//TODO: is it bad to re-instantiate here - not going to lie this seems inefficient
	// no copy constructor though - could make a private one
	//TODO: should it return the flipped version or just flip it - would get rid of problem
	//TODO: I did the above but may want to reverse it
	//TODO: I destroy my tower is that okay?
	public Tower flip() {
		Tower temp = new Tower();
		
		while(! this.empty()) {
			temp.add(this.pop());
		}
		
		return temp;
	}
}