package joeyzhao.game.revolvingRestaurant;

public class RevolvingRestaurantCustomer extends RevolvingRestaurantItems{
	private long lastTimeEat;
	public long eatingRate;
	public int appetite;
	public RevolvingRestaurantCustomer(char symbol,long eatingRate,int appetite){
		super(symbol);
		this.eatingRate=eatingRate;
		this.appetite=appetite;
		this.lastTimeEat=0l;
	}

	public long getEatingRate(){
		return this.eatingRate;
	}

	public long getLastTimeEat(){
		return lastTimeEat;
	}

	public void setLastTimeEat(long time){
		this.lastTimeEat=time;
	}

	public int getAppetite(){
		return this.appetite;
	}

	public void eat(RevolvingRestaurantState state, int x, int y) {
		// if elapsed time since last eating is > eatingRate then eat
		long currentTime=state.getTimer();
		long elapsedtime=currentTime-lastTimeEat;
		if(elapsedtime>=eatingRate&&x>=1&&x<=state.getRestaurant().length-2&&y>=1&&y<=state.getRestaurant()[0].length-2){
			// a loop over positions in the state around x,y
			for(int i=x-1;i<=x+1;i++){
				for(int j=y-1;j<=y+1;j++){
					RevolvingRestaurantItems current=state.getRestaurant()[i][j];
					if(current instanceof RevolvingRestaurantDish){
						this.lastTimeEat=state.getTimer();
						((RevolvingRestaurantDish) current).setPieces(((RevolvingRestaurantDish) current).getPieces()-this.appetite);
						state.addCoins(this.appetite/2);
						if(((RevolvingRestaurantDish) current).getPieces()<=0){
							state.addCoins(10);
							state.getRestaurant()[i][j]=new RevolvingRestaurantTable('#');
						}
					}
				}
			}
		}
	}



public String getGroup(){
	return "customer";
}
public int getPower(){
	return (int) (this.appetite/this.eatingRate);
}

public String toString(){
	String result="The symbol of this man: "+this.symbol+" eating rate: "+this.eatingRate+" appetite: "+this.appetite;
	return result;

}

@Override
public RevolvingRestaurantCustomer copy() {
	RevolvingRestaurantCustomer newCustomer=new RevolvingRestaurantCustomer(this.symbol,this.eatingRate,this.appetite);
	newCustomer.lastTimeEat=this.lastTimeEat;
	return newCustomer;
}
}
