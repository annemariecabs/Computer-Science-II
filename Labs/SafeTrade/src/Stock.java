import java.text.DecimalFormat;

public class Stock {
	
	public static DecimalFormat money;
	public String symb;
	public String compName;
	public double oPrice;
	
	public Stock(String symbol, String name, double price) {
		symb = symbol;
		compName = name;
		oPrice = price;
		
	}
	
	public String  getQuote() {
		return "";
		
	}
	
	
	public void placeOrder(TradeOrder order) {
		
	}
	
	
}