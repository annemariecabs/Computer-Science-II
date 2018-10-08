import java.util.Comparator;

//TODO: Javadocs, include this is modeled off of book
//TODO: should I have ascend??
public class CardComparator implements Comparator<Card> {
	
	private boolean ascend;
	
	public CardComparator() {
		ascend = true;
	}
	
	public CardComparator(boolean asc) {
		ascend = asc;
	}
	
	public int compare(Card card1, Card card2) {
		
		int diff = card1.getRank() - card2.getRank();
		
		if(ascend) {
			return diff;
		}
		else {
			return -diff;
		}
	}

}