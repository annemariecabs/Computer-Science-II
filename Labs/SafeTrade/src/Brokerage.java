public class Brokerage implements Login {
	
	StockExchange exch;
	
	public Brokerage(StockExchange exchange) {
		exch = exchange;
	}
	
	public int addUser(String name, String password) {
		
		return 0;
		
	}
	
	public void getQuote(String symbol, Trader trader) {
		
	}
	
	public int login(String name, String password) {
		
		return 0;
		
	}
	
	public void logout(Trader trader) {
		
	}
	
	public void placeOrder(TradeOrder order) {
		
	}
	
	
}