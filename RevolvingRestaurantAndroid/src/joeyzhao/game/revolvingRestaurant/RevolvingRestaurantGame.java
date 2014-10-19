package joeyzhao.game.revolvingRestaurant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RevolvingRestaurantGame {
	//copy from MagnetoGame
	private List<Actor> actors;
	private List<StateChangeListener> listeners;

	private RevolvingRestaurantState currentState;
	private List<RevolvingRestaurantState> levels;
	private boolean ended;
	public RevolvingRestaurantGame() {
		actors = new ArrayList<Actor>();
		levels = new ArrayList<RevolvingRestaurantState>();
		listeners = new ArrayList<StateChangeListener>();
	}
	

	public void addLevel(RevolvingRestaurantState level) {
		level.setLevel(levels.size());
		levels.add(level);

		if (currentState == null) {
			restart();
		}
	}

	public void addActor(Actor actor) {
		actors.add(actor);
	}

	public void removeActor(Actor actor) {
		actors.remove(actor);
	}
	
	public void addListener(StateChangeListener listener) {
		listeners.add(listener);
	}

	public RevolvingRestaurantState getCurrentState() {
		return currentState;
	}

	public boolean isEnded() {
		return currentState.isEnd();
	}

	public void restart() {
		ended = false;
		currentState = levels.get(0).copy();
		
	}

	public void nextLevel() {
		// are there more levels?
		if (currentState.getLevel()+1 >= levels.size()) {
			ended = true;
		}
		else {
			RevolvingRestaurantState oldState = currentState;
			currentState = levels.get(oldState.getLevel()+1).copy();
			currentState.setTimer(oldState.getTimer());
		}
	}

	public boolean update() {
		boolean moveMade = false;
		if (!ended) {
			// go through each actor and see if they have an action to take
			int s = actors.size();
			for (int i = 0; i < s; i++) {
				Actor a = actors.get(i);
				RevolvingRestaurantState state = getCurrentState();
				RevolvingRestaurantMove move = a.getNextMove(state);
				if (move != null && move.isValid(state)) {
					move.make(state);
					moveMade = true;

				}
			}
			for (StateChangeListener listener : listeners) {
				listener.onStateChange(getCurrentState());
			}
			//end of copy from MagnetoGame
		}
		return moveMade;
	}

	public RevolvingRestaurantHumanPlayer getHumanPlayer() {
		if(!(actors.get(0) instanceof TimeActor)){
			return (RevolvingRestaurantHumanPlayer) actors.get(0) ;
		}
		else{
			return (RevolvingRestaurantHumanPlayer) actors.get(1);
		}
	}

	public void onePlayerGame() {

		this.addActor(new TimeActor(this));
		this.addActor(new RevolvingRestaurantHumanPlayer());
	}
	
}