package joeyzhao.game.revolvingRestaurant;

import android.os.Handler;


public class TimeActor implements Actor, Runnable{
/**copy from Magneto.TimeActor**/
	private long lastTimeMove;
    private int lastLevel;
    private TimeMove timeMove;
    private Handler handler;
    private RevolvingRestaurantGame game;
    
    public TimeActor(RevolvingRestaurantGame game) {
        this.timeMove = new TimeMove(0);
        this.game=game;
        /**I forgot this line!!!!!**/this.handler= new Handler();
        
        run();
    }
    
    
	@Override
	public RevolvingRestaurantMove getNextMove(RevolvingRestaurantState state) {
		if (state.getLevel() != lastLevel) {
            lastLevel = state.getLevel();
            lastTimeMove = 0;
        }
		RevolvingRestaurantMove move = null;
        if (lastTimeMove != 0) {
            timeMove.setTime((long)(System.currentTimeMillis() - lastTimeMove));
            move = timeMove;
        }
        lastTimeMove = System.currentTimeMillis();

        return move;
    }
	/**end of copy**/


	@Override
	public void run() {
		 game.update();
	        if (!game.isEnded()) {	        	
	            handler.postDelayed(this,20l);
	        }
	        
		
	}

}
