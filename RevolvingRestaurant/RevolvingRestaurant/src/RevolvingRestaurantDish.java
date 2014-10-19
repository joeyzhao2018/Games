
public class RevolvingRestaurantDish extends RevolvingRestaurantItems{
	public int pieces;
	
	public RevolvingRestaurantDish(char symbol,int pieces){
		super(symbol);
		this.pieces=pieces;
	}
	public String getGroup(){
		return "dish";
	}
	
	public int getItems(){
		return this.pieces;	
	}
	public int getPower(){
		return this.pieces;
	}


}
