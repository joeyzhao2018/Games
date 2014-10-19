package joeyzhao.game.revolvingRestaurant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class RevolvingRestaurantState {
    private RevolvingRestaurantItems [][] restaurant;
	private List<RevolvingRestaurantCustomer> availableCustomers;
	private List<RevolvingRestaurantDish> availableDishes;
	private RevolvingRestaurantCustomer selected;
	private int accelerator=1;
	private boolean infinity;
	private int dishAddFrequency;
	
	public void setDishAddFrequency(int a){
		this.dishAddFrequency=a;		
	}
	
	public int getDishAddFrequency() {
		return dishAddFrequency;
	}

	public int getAccelerator() {
		return accelerator;
	}

	public void setAccelerator(int accelerator) {
		this.accelerator = accelerator;
	}

	public RevolvingRestaurantCustomer getSelected() {
		return selected;
	}

	public void setSelected(RevolvingRestaurantCustomer selected) {
		this.selected = selected;
	}
	public boolean isInfinite(){		
		return infinity;
	}
	public void setInfinite(){
		this.infinity=true;
	}
	
	private int coins;//this matters!

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public void addCoins(int coins){
		this.coins+=coins;
	}


	private long lastTimeTableRotated;
    private long timer;
    private int level;

    
    public  RevolvingRestaurantState (RevolvingRestaurantItems [][] restaurant,
    		List<RevolvingRestaurantCustomer> availableCustomers,
    		List<RevolvingRestaurantDish> availableDishes){
    	this.restaurant=restaurant;
		this.availableCustomers=availableCustomers;
		this.availableDishes=availableDishes;
		this.lastTimeTableRotated=0;
		this.coins=100;
		this.accelerator=1;
		this.infinity=false;
		this.dishAddFrequency=20;

    }
    
    public long getLastTimeTableRotated() {
		return lastTimeTableRotated;
	}

	public void addAvailableCustomers(RevolvingRestaurantCustomer dinner){
		this.availableCustomers.add(dinner);
	}
	public  List<RevolvingRestaurantCustomer> getAvailableCustomers(){
		return this.availableCustomers;
	}
	public  List<RevolvingRestaurantDish> getAvailableDishes(){
		return this.availableDishes;
	}
    public RevolvingRestaurantItems [][] getRestaurant() {
        return restaurant;
    }
    public long getTimer(){
    	return this.timer;
    }
    public void setTimer(long timer){
    	this.timer=timer;
    }
    
    public int getLevel(){
    	return this.level;
    }
    
    public void setLevel(int level){
    	this.level=level;
    }  
    public boolean isEnd() {
    	if(restaurant[2][1] instanceof RevolvingRestaurantDish && 
    			restaurant[3][2] instanceof RevolvingRestaurantDish
    			||(this.availableDishes.size()+this.getNumberOfDishes()==0)){
    		//This is the situation that one more dish is about to be added onto the
    		//revolving desk while there is another dish moving towards the entrance
    		//and they are going to crush and that's how this game ends.
    		//When one dish is going to be added, it must be shown at restaurant[1][0] first.
    		//And the revolving desk is a circle with four vertexes [1][1], [restaurant.length-2][1],
    		//[1][restaurant[1].length-2] and [restaurant.length-2][restaurant[1].length-2] 
    		return true;    		     
    	}
    	else{
    		return false;
    	}
    	
    }
    public int getNumberOfCustomers(){
    	int numberOfCustomers=0;
    	for (int i = 0; i < restaurant.length; i++) {
            for (int j = 0; j < restaurant[i].length; j++) {
            	if(restaurant[i][j].getGroup()=="customer"){
            		numberOfCustomers++;
            		}
            	}
            		
            }
     	return numberOfCustomers;
    	}

    
    public int getNumberOfDishes(){
    	int numberOfDishes=0;
    	for (int i = 0; i < restaurant.length; i++) {
            for (int j = 0; j < restaurant[i].length; j++) {
            	if(restaurant[i][j].getGroup()=="dish"){
            		numberOfDishes++;  		     		
            	}
            }
    	}
    	return numberOfDishes;
            
    }
    
    public int getHeuristicScore(){
   
    	int heuristicScore=0;
    	for (int i = 0; i < restaurant.length; i++) {
            for (int j = 0; j < restaurant[i].length; j++) {
            	if(restaurant[i][j].getGroup()=="customer"){
            		heuristicScore+=restaurant[i][j].getPower();	
            	}
            	else if(restaurant[i][j].getGroup()=="dish"){
            		heuristicScore-=restaurant[i][j].getPower();	
            	}
            }
    	}
    	return heuristicScore;
    	
    }
    
    public void rotateTable() {
    	int row=restaurant.length;
    	int col=restaurant[0].length;
    	//RevolvingRestaurantItems upRight=restaurant[2][col-3];
    	//RevolvingRestaurantItems lowRight=restaurant[row-3][col-3];
    	RevolvingRestaurantItems upLeft=restaurant[3][2];
    	
    	for(int i=3;i<=row-4;i++){
    		restaurant[i][2]=restaurant[i+1][2];
    	}
    	
    	for(int i=2;i<=col-4;i++){
    		restaurant[row-3][i]=
    				restaurant[row-3][i+1];
    	}
    	for(int i=row-3;i>=3;i--){
    		restaurant[i][col-3]=
    				restaurant[i-1][col-3];
    	}

    	for(int i=col-3;i>=2;i--){
    		restaurant[2][i]=restaurant[2][i-1];
    	}
    	restaurant[2][1]=new RevolvingRestaurantTable('#');//bet that [2][1] is only a table, otherwise, game over


    	
    	//restaurant[3][col-3]=upRight;

    	
    	//restaurant[row-3][col-4]=lowRight;
    	
    	if( upLeft instanceof RevolvingRestaurantDish){
    		restaurant[2][2]=upLeft;
    	}
    	
    	//restaurant[row-4][2]=lowLeft;


    }

    public void setLastTimeTableRotated(long lastTimeTableRotated) {
		this.lastTimeTableRotated = lastTimeTableRotated;
	}

	public void print() {
        System.out.println("There are " + getNumberOfCustomers() + " customers");
        System.out.println("There are " + getNumberOfDishes()+ " dishes");
        System.out.println("dishes available: "+this.getAvailableDishes().size());
        System.out.println("customers to be added: "+this.getAvailableCustomers().size());
        for (int i = 0; i < restaurant.length; i++) {
            for (int j = 0; j < restaurant[i].length; j++) {
                System.out.print(restaurant[i][j].symbol);
            }
            System.out.println();
        }
    }
    
    public RevolvingRestaurantState copy(){
    	RevolvingRestaurantItems [][] newRestaurant= 
    			new RevolvingRestaurantItems [restaurant.length][restaurant[1].length];
    	
    	for (int i = 0; i < restaurant.length; i++) {
            for (int j = 0; j < restaurant[i].length; j++) {
            	newRestaurant[i][j]=restaurant[i][j].copy();
            }
            }
    	List<RevolvingRestaurantCustomer> newCustomers=new ArrayList<RevolvingRestaurantCustomer>();
    	List<RevolvingRestaurantDish> newDishes=new ArrayList<RevolvingRestaurantDish>();
    	for(int i=0;i<this.availableCustomers.size();i++){
    		newCustomers.add(this.availableCustomers.get(i));    		  		
    	}
    	for(int j=0;j<this.availableDishes.size();j++){
    		newDishes.add(this.availableDishes.get(j));
    	}
    	RevolvingRestaurantState state=new RevolvingRestaurantState(newRestaurant,newCustomers,newDishes);

    	state.accelerator=this.accelerator;
    	state.dishAddFrequency=this.dishAddFrequency;
    	state.infinity=this.infinity;
    	return state;
    }
    
    
	public static RevolvingRestaurantState loadFromStream(InputStream fileInputStream) throws FileNotFoundException {
		        
		        Scanner scanner = new Scanner(fileInputStream);
		        
		        List<RevolvingRestaurantCustomer> customerList=new ArrayList<RevolvingRestaurantCustomer>();
		        List<RevolvingRestaurantDish> dishList=new ArrayList<RevolvingRestaurantDish>();
		        //the first line of the leveli.txt represents customers' symbol, rate, appetite, number//
		        RevolvingRestaurantCustomer fastEater=new RevolvingRestaurantCustomer('A',
		        		1,1);//the first two numbers
		        RevolvingRestaurantCustomer bigGuy=new RevolvingRestaurantCustomer('B',
		        		10,8);
		        RevolvingRestaurantCustomer ordinaryPeople=new RevolvingRestaurantCustomer('C',
		        		3,3);
//		        int numberOfCustomer=Integer.parseInt(scanner.next()); //the 3 rd number
//		        for(int i=0;i<numberOfCustomer;i++){
//		        	customerList.add(dinner);
//		        }
		        int dishSize=Integer.parseInt(scanner.next());
		        RevolvingRestaurantDish deli=new RevolvingRestaurantDish('@',dishSize);
		        int numberOfDishes=Integer.parseInt(scanner.next()); 
		        for(int i=0;i<numberOfDishes;i++){
		        	dishList.add(deli);
		        }
		        
		        RevolvingRestaurantTable table=new RevolvingRestaurantTable('#');
		        RevolvingRestaurantEmptySpace space=new RevolvingRestaurantEmptySpace(' ');
		        
		        int row=Integer.parseInt(scanner.next());
		        int column=Integer.parseInt(scanner.next()); 
		        RevolvingRestaurantItems [][] newRestaurant=new RevolvingRestaurantItems [row][column];
		        int dishAddFrequncy=Integer.parseInt(scanner.next());
		        int accelerator=Integer.parseInt(scanner.next());
		        for(int i=0;i<row;i++){
		        	for(int j=0;j<column;j++){
		        		if(i==2||i==row-3||j==2||j==column-3){
		        			newRestaurant[i][j]=table;
		        		}
		        		else{
		        			newRestaurant[i][j]=space;
		        		}
		        	}
		        }
		        newRestaurant[0][0]=fastEater;
		        newRestaurant[0][1]=ordinaryPeople;
		        newRestaurant[0][2]=bigGuy;
		        newRestaurant[1][2]=space;
		        newRestaurant[0][column-3]=space;newRestaurant[1][column-3]=space;
		        newRestaurant[2][column-1]=space;newRestaurant[2][column-2]=space;
		        newRestaurant[row-1][column-3]=space;newRestaurant[row-2][column-3]=space;
		        newRestaurant[row-3][column-1]=space; newRestaurant[row-3][column-2]=space;
		        newRestaurant[row-1][2]=space;newRestaurant[row-2][2]=space;
		        newRestaurant[row-3][0]=space; newRestaurant[row-3][1]=space;
		        //this part I hard coded it
		        /**testing TableMove
		        **/
		        //RevolvingRestaurantDish dish=new RevolvingRestaurantDish('@',50);
		       // newRestaurant[2][5]=dish;
		        //RevolvingRestaurantDish anotherDish=new RevolvingRestaurantDish('@',50);
		        RevolvingRestaurantCustomer eater=new RevolvingRestaurantCustomer('&',
		        		1,1);
		        //dishList.add(dish);
		        //dishList.add(dish);
		        //dishList.add(anotherDish);
		        customerList.add(eater);//Though I'm not using this list now, I didn't delete it because I think it might be useful in the future
		        
		        RevolvingRestaurantState state=new RevolvingRestaurantState(newRestaurant,customerList,dishList);
		        state.setDishAddFrequency(dishAddFrequncy);
		        state.setAccelerator(accelerator);
		        return state;
		    }
    
}
