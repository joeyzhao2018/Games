package joeyzhao.game.revolvingRestaurant;



/**
 * Implementation of TicTacToePlayer that handles user input to make
 * a next move selection.
 * 
 * @author jatlas
 */
public class RevolvingRestaurantHumanPlayer implements Actor {
	private int selection_row;
	private int selection_col;

	/**
	 * Creates a HumanPlayer whose next move selection is currently unknown.
	 * 
	 */
	public RevolvingRestaurantHumanPlayer() {

		resetSelection();
	}

	/**
	 * Convenience method to reset the next move selection to unknown.
	 */
	private void resetSelection() {
		selection_row = -1;
		selection_col = -1;
	}

	/**
	 * Handles user input by storing it as the selection that should be returned
	 * when this player's nextMove is asked for.
	 */
	public void handleSquareSelection(int row, int col) {
		selection_row = row;
		selection_col = col;
	}

	@Override
	/**
	 * Checks the current selection row,col to determine this player's nextMove.
	 * If the current selection is unknown, returns a null move.
	 * If the selection is known, it returns a new TicTacToe5x5Move with the selection row,col.
	 */
	public RevolvingRestaurantMove getNextMove(RevolvingRestaurantState state) {
		
		if (selection_row==0 && (selection_col==0||selection_col==1||selection_col==2)){
			state.setSelected((RevolvingRestaurantCustomer) state.getRestaurant()[selection_row][selection_col]);
			return null;
		}
		else if (selection_col > 0 && selection_row >= 0) {
			if(state.getSelected()!=null){
				RevolvingRestaurantCustomer dinner=state.getSelected().copy();
				AddCustomerMove move=new AddCustomerMove(dinner,selection_row, selection_col);       	
				// reset between moves
				resetSelection();

				return move;
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}
}