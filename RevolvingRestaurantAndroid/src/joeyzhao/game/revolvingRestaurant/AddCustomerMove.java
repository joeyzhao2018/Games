package joeyzhao.game.revolvingRestaurant;


public class AddCustomerMove implements RevolvingRestaurantMove {
	private RevolvingRestaurantCustomer dinner;
	private int x;
	private int y;

	public AddCustomerMove(RevolvingRestaurantCustomer dinner, int x, int y){
		this.x=x;
		this.y=y;
		this.dinner=dinner;
	}



	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public int getY(){
		return this.y;
	}

	@Override
	public boolean isValid(RevolvingRestaurantState state) {
		if(state.getCoins()>=10){
			if(state.getRestaurant()[this.x][this.y] instanceof RevolvingRestaurantEmptySpace &&
					this.x!=0 && this.x!=state.getRestaurant().length-1&&
					this.y!=0 && this.y!=state.getRestaurant()[1].length-1){
				if(this.x==1||this.x==3||this.x==state.getRestaurant().length-2||this.x==state.getRestaurant().length-4){
					return true;
				}
				else if(this.y==1||this.y==3||this.y==state.getRestaurant()[1].length-2||this.y==state.getRestaurant()[1].length-2
						||this.y==state.getRestaurant()[1].length-4){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void make(RevolvingRestaurantState state) {
		state.getRestaurant()[x][y]=dinner;
		dinner.setLastTimeEat(state.getTimer());
		if(dinner.symbol=='A'){
			state.addCoins(-100);
		}
		else if(dinner.symbol=='B'){
			state.addCoins(-150);
		}
		else if(dinner.symbol=='C'){
			state.addCoins(-200);
		}
		/**if(state.getAvailableCustomers().size()>0){
			state.getAvailableCustomers().remove(0);

		}**/
		/**hard coded
		 I want to delete the customer, who has been added onto the board, from the state.
		 availableCustomers list
		 **/

		if(state.getAvailableCustomers().size()>0){
			state.getAvailableCustomers().remove(0);
		}
		state.setSelected(null);

	}
	@Override
	public String toString(){
		String result="A customer has been add to row "+this.x+" column "+this.y;
		return result;
	}

}
