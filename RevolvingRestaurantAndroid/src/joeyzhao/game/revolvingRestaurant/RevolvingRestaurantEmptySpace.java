package joeyzhao.game.revolvingRestaurant;

public class RevolvingRestaurantEmptySpace extends RevolvingRestaurantItems{

	public RevolvingRestaurantEmptySpace(char symbol) {
		super(symbol);
	}

	@Override
	public RevolvingRestaurantEmptySpace copy() {
		RevolvingRestaurantEmptySpace space=new RevolvingRestaurantEmptySpace(this.symbol);
		return space;
	}
	

}
