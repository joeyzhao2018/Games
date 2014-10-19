package joeyzhao.game.revolvingRestaurant;

public abstract class RevolvingRestaurantItems {
	
	public char symbol;
	public char getSymbol(){
		return this.symbol;
	}
	public RevolvingRestaurantItems(char symbol){
		super();
		this.symbol=symbol;
	}
	public String getGroup(){
		return null;
	}
	
	public int getPower(){
		return 0;
	}
	public abstract RevolvingRestaurantItems copy();
}
