package joeyzhao.game.revolvingRestaurant;
import java.util.ArrayList;
import java.util.List;


public class RevolvingRestaurantAI implements Actor {
	private List<RevolvingRestaurantMove> possibleMoves;
	
	public RevolvingRestaurantAI(){
		this.possibleMoves=new ArrayList<RevolvingRestaurantMove>();
		
	}
	public  List<RevolvingRestaurantMove> getPossibleMoves(){
		return this.possibleMoves;
	}
	@Override
	public RevolvingRestaurantMove getNextMove(RevolvingRestaurantState state) {
		int maxScore=state.getHeuristicScore();
		if(state.getAvailableCustomers().size()<=0){
			return null;
		}
		else{
			for(int i=0;i<state.getAvailableCustomers().size();i++){
				for(int j=0;j<state.getRestaurant().length;j++){
					for(int k=0;k<state.getRestaurant()[i].length;k++){
						RevolvingRestaurantCustomer dinner=state.getAvailableCustomers().get(i);
						AddCustomerMove move=new AddCustomerMove(dinner,j,k);//seperate
						if(move.isValid(state)){
							RevolvingRestaurantState copy=state.copy();
							move.make(copy);
							int newScore=copy.getHeuristicScore();
							if(newScore>maxScore){
								this.possibleMoves.clear();
								}
							if(newScore>=maxScore){
								this.possibleMoves.add(move);
							}
						}
					}
				}
			}
		}
		
		int index=(int) (Math.random()*possibleMoves.size());
		return possibleMoves.get(index);
	}
	

}
