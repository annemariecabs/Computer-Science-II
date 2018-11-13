import java.util.LinkedList;
import java.util.TreeSet;

public class MailServer extends LinkedList<Message> {
	
	TreeSet<Actor> actors;
	
	public MailServer() {
		super();
		actors = new TreeSet<Actor>();
	}
	
	public void signUp(Actor actor) {
		actors.add(actor);
	}
	
	public boolean isEmpty() {
		return super.isEmpty();
	}
	
	public void dispatch(Message msg) {
		Actor recipient = msg.getRecipient();
		
		if(recipient == null) {
			for(Actor actor: actors) 
				if(! actor.equals(msg.getSender())) 
					actor.receive(msg);
		}
		
		else 
			recipient.receive(msg);		
	}
}
