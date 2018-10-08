public class Trader implements Comparable<Trader> {
	
	Brokerage brkg;
	String screenName;
	String password;
	
	public Trader(Brokerage brokerage, String name, String passwd) {
		brkg = brokerage;
		screenName = name;
		password = passwd;
		
	}
	
	public int compareTo(Trader other) {
		return screenName.compareToIgnoreCase(other.getName()); 
		
	}
	
	public boolean equals(Object other) {
		
		return screenName.equalsIgnoreCase(other.getName());
		
	}

	public String getName() {
		
		return screenName;
		
	}
	
	public String getPassword() {
		return password;
		
	}
	
	//NEED TO DO
	public void getQuote(String symbol) {
		
	}
	
	
	//NEED TO DO
	public boolean hasMessages() {
		return false;
		
	}

	public void openWindow() {
		
	}

	public void placeOrder(TradeOrder order) {
		
	}

	public void quit() {
		
	}
	
	public void	receiveMessage(String msg) {
		
	}

}