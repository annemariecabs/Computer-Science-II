public class TradeOrder {

	public TradeOrder() {
		
	}

	public double getPrice() {

		return 5.0;
	}

	public int getShares() {

		return 4;
	}

	public String getSymbol() {

		return "";
	}

	public Trader getTrader() {

		return new Trader();
	}

	public boolean isBuy() {

		return true;
	}

	public boolean isLimit() {

		return true;
	}

	public boolean isMarket(){

		return true;
	}

	public boolean isSell() {
		return true;
	}

	public void subtractShares(int shares) {

	}

}