
public class RevolvingRestaurantCustomer extends RevolvingRestaurantItems{
	
	public int eatingRate;
	public int appetite;
	public int getEatingRate(){
		return this.eatingRate;
	}
	public int getAppetite(){
		return this.appetite;
	}
	public RevolvingRestaurantCustomer(char symbol,int eatingRate,int appetite){
		super(symbol);
		this.eatingRate=eatingRate;
		this.appetite=appetite;
	}
	
	public String getGroup(){
		return "customer";
	}
	public int getPower(){
		return this.appetite*this.eatingRate;
	}

}
