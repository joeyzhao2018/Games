package joeyzhao.game.revolvingRestaurant;

public class RevolvingRestaurantDish extends RevolvingRestaurantItems{
	private int pieces;
	
	public RevolvingRestaurantDish(char symbol,int pieces){
		super(symbol);
		this.pieces=pieces;
	}
	public String getGroup(){
		return "dish";
	}
	
	public int getPieces(){
		return this.pieces;	
	}
	public void setPieces(int pieces){
		this.pieces=pieces;
	}
	public int getPower(){
		
		return this.pieces;
	}
	@Override
	public RevolvingRestaurantDish copy() {
		RevolvingRestaurantDish newDish=new RevolvingRestaurantDish(this.symbol,this.pieces);
		return newDish;
	}
	


}
