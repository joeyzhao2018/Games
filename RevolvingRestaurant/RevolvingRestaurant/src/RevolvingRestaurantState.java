
public class RevolvingRestaurantState {
    public RevolvingRestaurantItems [][] restaurant;
    public  RevolvingRestaurantState (RevolvingRestaurantItems [][] restaurant){
    	this.restaurant=restaurant;
    }
    public RevolvingRestaurantItems [][] getRestaurant() {
        return restaurant;
    }
    public boolean isEnd() {
    	if(restaurant[1][0].getGroup()=="dish" && restaurant[2][1].getGroup()=="dish"){
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
    
    public void print() {
        System.out.println("There are " + getNumberOfCustomers() + " customers");
        System.out.println("There are " + getNumberOfDishes()+ " dishes");
        for (int i = 0; i < restaurant.length; i++) {
            for (int j = 0; j < restaurant[i].length; j++) {
                System.out.print(restaurant[i][j].symbol);
            }
            System.out.println();
        }
    }

}
