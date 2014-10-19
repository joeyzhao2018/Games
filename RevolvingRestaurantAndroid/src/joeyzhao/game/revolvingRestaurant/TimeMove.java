package joeyzhao.game.revolvingRestaurant;
//this move is allowing customers to eat dishes
public class TimeMove implements RevolvingRestaurantMove{
	private long TABLE_ROTATION_FREQUENCY = 1000l;

	private long time;/** I don't know why I haven't used this yet **/
	/**in fact I used this class to eat dishes**/

	public TimeMove(long time) {
		this.time = time;
	}

	public float getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public boolean isValid(RevolvingRestaurantState state) {
		return true;
	}

	@Override
	public void make(RevolvingRestaurantState state){
		// compute what the new time in the state is
		long afterMoveTime = state.getTimer() + time;

		// want customers to eat the dishes
		for(int i=0;i<state.getRestaurant().length;i++){
			for(int j=0;j<state.getRestaurant()[i].length;j++){
				RevolvingRestaurantItems current=state.getRestaurant()[i][j];
				if(current instanceof RevolvingRestaurantCustomer){
					((RevolvingRestaurantCustomer)current).eat(state,i,j);
				}				 
			}
		}

		// is it time to rotate the table?
		long elapsedTime = afterMoveTime - state.getLastTimeTableRotated();
		/**MUST SEE**/
		long newFrequency=TABLE_ROTATION_FREQUENCY/state.getAccelerator();
		for (; elapsedTime > newFrequency; elapsedTime -= newFrequency) {
			if(elapsedTime>=newFrequency){
				state.rotateTable();


				state.setLastTimeTableRotated(afterMoveTime);
			}
		}
		// generate new dish
		double decider=Math.random()*state.getDishAddFrequency();
		if(!(state.getRestaurant()[2][1] instanceof RevolvingRestaurantDish) &&
				decider<0.5 /**temp**/ &&state.getAvailableDishes().size()!=0){
			
			RevolvingRestaurantDish dishToGo= state.getAvailableDishes().get(0).copy();
			if(!state.isInfinite()){
			state.getRestaurant()[2][1]=dishToGo;
			state.getAvailableDishes().remove(0);
			}
			else{
				state.getRestaurant()[2][1]=dishToGo;
				dishToGo.setPieces((int)(Math.random()*10000));
			}
		}
		//update timer
		state.setTimer(afterMoveTime);
	}

	public String toString(){
		String result="Time goes by...";
		return result;
	}
}
