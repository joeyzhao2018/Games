package joeyzhao.game.revolvingRestaurant;

public class RevolvingRestaurantTable extends RevolvingRestaurantItems{

	public RevolvingRestaurantTable(char symbol) {
		super(symbol);
	}

	@Override
	public RevolvingRestaurantTable copy() {
		RevolvingRestaurantTable table=new RevolvingRestaurantTable(this.symbol);
		return table;
	}
	

}
